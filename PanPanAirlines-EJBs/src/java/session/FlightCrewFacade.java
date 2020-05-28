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
    public boolean createFlightCrew(FlightCrewDTO flightCrew)
    {
        if (flightCrew == null)
            return false;
        
        //Record already exists
        if (!crewExists(flightCrew.getCrewid()))
            return false;
        
        try
        {
            createCrew(dtoToDAO(flightCrew));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public FlightCrewDTO findFlightCrew(int id)
    {
        Flightcrew crewDAO = findCrewDAO(id);
        
        if (crewDAO == null)
            return null;
        try
        {
            //Finding all Flightcrew DAOs with the same, shared CrewID field
            Query query = em.createNamedQuery("Flightcrew.findByCrewid").setParameter("crewid", crewDAO.getCrewid());
            ArrayList<Flightcrew> daoArray = new ArrayList<>(query.getResultList());

            //Extracting all employeeIDs from the Flightcrew DAOs.
            int[] employeeIDs = new int[daoArray.size()];
            for (int i = 0; i < employeeIDs.length; i++)
                employeeIDs[i] = daoArray.get(i).getEmployeeid();

            //EmployeeFacade will convert an array of empIDs to an ArrayList of DTOs.
            ArrayList<EmployeeDTO> employeesDTOList = employeeFacade.employeeIDsToDTOs(employeeIDs);

            //Can now create the FlightCrewDTO with the list of employeeDTOs
            FlightCrewDTO result = new FlightCrewDTO
            (
                crewDAO.getCrewid(), 
                crewDAO.getId(),
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
