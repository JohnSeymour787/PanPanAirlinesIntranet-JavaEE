/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.ScheduledflightDeprecated;
import entity.ScheduledFlightDTO;
import javax.ejb.EJB;
/**
 *
 * @author John
 */
@Stateless
public class ScheduledFlightFacade implements ScheduledFlightFacadeRemote
{

    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;
    @EJB
    private AircraftFacadeLocal aircraftFacade;
    @EJB
    private FlightCrewFacadeLocal flightCrewFacade;
    
    private void createFlight(ScheduledflightDeprecated flight) throws Exception
    {
        em.persist(flight);
    }
    
    private ScheduledflightDeprecated findFlight(int id)
    {
        return em.find(ScheduledflightDeprecated.class, id);
    }
    
    private void updateFlight(ScheduledflightDeprecated flight) throws Exception
    {
        if (em.merge(flight) == null)
            throw new Exception("Nothing updated.");
    }
    
    private void deleteFlight(int id) throws Exception
    {
        ScheduledflightDeprecated toRemove = findFlight(id);
        if (toRemove == null)
            throw new Exception("Cannot find record to remove.");
        else
            em.remove(toRemove);
    }
    
    //Converts the externally-available DTO to a DAO for use with the EntityManager
    private ScheduledflightDeprecated dtoToDAO(ScheduledFlightDTO flight)
    {
        ScheduledflightDeprecated result;
        
        result = new ScheduledflightDeprecated
        (
                flight.getFlightnumber(), 
                flight.getOriginatingairport(), 
                flight.getDestinationairport(), 
                //Get the aircraft and flight crew entity classes
                aircraftFacade.find(flight.getAircraft().getAircraftID()),
                flightCrewFacade.findCrewDAO(flight.getFlightnumber())
        );
        
        return result;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean createScheduledFlight(ScheduledFlightDTO flight)
    {
        if (flight == null)
            return false;
        
        //Record already exists
        if (findFlight(flight.getFlightnumber()) != null)
            return false;
        
        try
        {
            createFlight(dtoToDAO(flight));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    private ScheduledFlightDTO doaToDTO(ScheduledflightDeprecated flight)
    {
        if (flight == null)
            return null;
        
        ScheduledFlightDTO result = new ScheduledFlightDTO
        (
            flight.getFlightnumber(), 
            flight.getOriginatingairport(),
            flight.getDestinationairport(), 
            aircraftFacade.findDTO(flight.getAircraft().getAircraftid()), 
            flightCrewFacade.findFlightCrew(flight.getCrew().getId())
        );
        
        return result;
    }

    @Override
    public ScheduledFlightDTO findScheduledFlight(int id)
    {
        ScheduledflightDeprecated flight = findFlight(id);
        
        return doaToDTO(flight);
    }

    @Override
    public boolean updateScheduledFlight(ScheduledFlightDTO flight)
    {
        if (flight == null)
            return false;
        
        if (findFlight(flight.getFlightnumber()) == null)
            return false;
        
        try
        {
            updateFlight(dtoToDAO(flight));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean deleteScheduledFlight(int id)
    {
        try
        {
            deleteFlight(id);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
}
