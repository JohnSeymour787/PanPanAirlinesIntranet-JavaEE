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
/**
 *
 * @author John
 */
@Stateless
public class ScheduledFlightFacade implements ScheduledFlightFacadeRemote
{

    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;
   
    private void createFlight(Scheduledflight flight)
    {
        em.persist(flight);
    }
    
    private Scheduledflight findFlight(int id)
    {
        return em.find(Scheduledflight.class, id);
    }
    
    private void updateFlight(Scheduledflight flight)
    {
        em.merge(flight);
    }
    
    private void deleteFlight(int id)
    {
        em.remove(id);
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
