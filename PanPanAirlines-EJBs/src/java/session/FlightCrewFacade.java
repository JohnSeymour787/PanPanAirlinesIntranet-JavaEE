/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EmployeeDTO;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Flightcrew;
import entity.FlightCrewDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.Query;
/**
 *
 * @author John
 */
@Stateful
public class FlightCrewFacade implements FlightCrewFacadeLocal
{
    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    
    private void createCrew(Flightcrew flight) throws Exception
    {
        System.out.println("Flight DAO details");
        System.out.println("ID" + flight.getId());
        System.out.println("Crew ID " + flight.getCrewid());
        System.out.println("Emp ID" + flight.getEmployeeid());
        
        
        em.persist(flight);
    }
    
    @Override
    public Flightcrew findCrewDAO(Integer id)
    {
        return em.find(Flightcrew.class, id);
    }
    
    private void updateCrew(Flightcrew flightCrew) throws Exception
    {
        if (em.merge(flightCrew) == null)
            throw new Exception("Nothing updated.");
    }
    
    private void deleteCrew(int id) throws Exception
    {
        Flightcrew toRemove = findCrewDAO(id);
        if (toRemove == null)
            throw new Exception("Cannot find record to remove.");
        else
            em.remove(toRemove);
    }
    
    
    //Converts the externally-available DTO to a DAO for use with the EntityManager
    private Flightcrew dtoToDAO(FlightCrewDTO flightCrew)
    {
        /*
        Flightcrew result;
        
        result = new Flightcrew
        (
            flightCrew.getCrewid(), 
            flightCrew.getId(),
            flightCrew.getEmployees()
        );
        */
        return null;
        //return result;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    
    @Override
    public boolean createFlightCrew(int id, int crewID, int firstEmployeeID)
    {
        //Getting the employee from the Employee table and making sure it exists
        EmployeeDTO employee = employeeFacade.getEmployeeDetails(firstEmployeeID);
        if (employee == null)
            return false;

        //Unique DB record already exists
        if (crewExists(id))
            return false;
        
        ArrayList<Flightcrew> crewIDArray = getAllForCrewID(crewID);
        
        //Shared crewID already exists
        if (!crewIDArray.isEmpty())
            return false;
        
        Flightcrew flightCrew = new Flightcrew(id, crewID, firstEmployeeID);

        try
        {
            createCrew(flightCrew);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    private ArrayList<Flightcrew> getAllForCrewID(Integer crewID)
    {
        System.out.println("session.FlightCrewFacade.getAllForCrewID()");
        //Finding all Flightcrew DAOs with the same, shared CrewID field
        //Query query = em.createNamedQuery("Flightcrew.findAll");//.setParameter("crewid", crewID);
        Query query = em.createNamedQuery("Flightcrew.findByCrewid").setParameter("crewid", crewID);
        System.out.println("query string is: " + query.toString());
        ArrayList<Flightcrew> result;
        try
        {
            result = new ArrayList<>(query.getResultList());
            
        }
        catch (Exception e)
        {
            System.out.println("Error in query get result list");
            e.printStackTrace();
            return null;
        }
        
        return result;
    }

    @Override
    public FlightCrewDTO findFlightCrew(int crewID)
    {
        System.out.println("session.FlightCrewFacade.findFlightCrew()");
        try
        {
            //Getting all DB records with the crew 
            ArrayList<Flightcrew> daoArray = getAllForCrewID(crewID);
            
            System.out.println("Got DAO array. Is array empty:" + daoArray.isEmpty());
            if (daoArray.isEmpty())
                return null;
            System.out.println("1.");
            //Extracting all employeeIDs from the Flightcrew DAOs.
            int[] employeeIDs = new int[daoArray.size()];
            
            for (int i = 0; i < employeeIDs.length; i++)
                employeeIDs[i] = daoArray.get(i).getEmployeeid();

            System.out.println("2.");
            //EmployeeFacade will convert an array of empIDs to an ArrayList of DTOs.
            ArrayList<EmployeeDTO> employeesDTOList = employeeFacade.employeeIDsToDTOs(employeeIDs);

            System.out.println("3.");
            //Can now create the FlightCrewDTO with the list of employeeDTOs
            FlightCrewDTO result = new FlightCrewDTO
            (
                crewID, 
                daoArray.get(0).getId(),    //Know that the list is not empty, so just get the unique DB record ID of the first element
                employeesDTOList
            );

            return result;
        }
        catch (Exception e)
        {
            return null;
        }

    }

    @Override
    public boolean updateFlightCrew(FlightCrewDTO flightCrew)
    {
        if (flightCrew == null)
            return false;
        
        //Record must exist
        if (!crewExists(flightCrew.getCrewid()))
            return false;
        
        try
        {
            updateCrew(dtoToDAO(flightCrew));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean deleteFlightCrew(int id)
    {
        try
        {
            deleteCrew(id);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean crewExists(int id)
    {
        return findCrewDAO(id) != null;
    }
}
