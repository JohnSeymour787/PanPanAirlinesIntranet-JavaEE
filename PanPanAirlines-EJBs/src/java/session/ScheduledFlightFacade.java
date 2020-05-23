/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Scheduledflight;
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
    
    private void createFlight(Scheduledflight flight) throws Exception
    {
        em.persist(flight);
    }
    
    private Scheduledflight findFlight(int id)
    {
        return em.find(Scheduledflight.class, id);
    }
    
    private void updateFlight(Scheduledflight flight) throws Exception
    {
        if (em.merge(flight) == null)
            throw new Exception("Nothing updated.");
    }
    
    private void deleteFlight(int id) throws Exception
    {
        Scheduledflight toRemove = findFlight(id);
        if (toRemove == null)
            throw new Exception("Cannot find record to remove.");
        else
            em.remove(toRemove);
    }
    
    //Converts the externally-available DTO to a DAO for use with the EntityManager
    private Scheduledflight dtoToDAO(ScheduledFlightDTO flight)
    {
        Scheduledflight result;
        
        result = new Scheduledflight
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
        return false;
    }

    @Override
    public ScheduledFlightDTO findScheduledFlight(int id)
    {
        return null;
    }

    @Override
    public boolean updateScheduledFlight(ScheduledFlightDTO flight)
    {
        return false;
    }

    @Override
    public boolean deleteScheduledFlight(int id)
    {
        return false;
    }
}
