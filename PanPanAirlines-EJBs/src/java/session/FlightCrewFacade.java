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
/**
 *
 * @author John
 */
@Stateful
public class FlightCrewFacade implements FlightCrewFacadeRemote
{

    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;

    private void createCrew(Flightcrew flight)
    {
        em.persist(flight);
    }
    
    private Flightcrew findCrew(int id)
    {
        return em.find(Flightcrew.class, id);
    }
    
    private void updateCrew(Flightcrew flightCrew)
    {
        em.merge(flightCrew);
    }
    
    private void deleteCrew(int id)
    {
        em.remove(id);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean createFlightCrew(FlightCrewDTO flightCrew)
    {
        return false;
    }

    @Override
    public FlightCrewDTO findFlightCrew(int id)
    {
        return null;
    }

    @Override
    public boolean updateFlightCrew(FlightCrewDTO flightCrew)
    {
        return false;
    }

    @Override
    public boolean deleteFlightCrew(int id)
    {
        return false;
    }
    
    
}
