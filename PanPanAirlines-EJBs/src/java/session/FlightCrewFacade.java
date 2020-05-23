/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Flightcrew;
import entity.FlightCrewDTO;
import javax.ejb.EJB;
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
    public Flightcrew findCrewDAO(int id)
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
        Flightcrew result;
        
        result = new Flightcrew
        (
            flightCrew.getCrewid(), 
            flightCrew.getId()
        );
        
        return result;
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
        
        //Needs to work with the employee facade to get the employee DTO
        
        FlightCrewDTO result = new FlightCrewDTO
        (
            crewDAO.getCrewid(), 
            crewDAO.getId(),
            //Getting the corresponding EmployeeDTO
            employeeFacade.getEmployeeDetails(crewDAO.getEmployeeid().getEmployeeid())
        );
        
        
        //return result;
        return null;
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
