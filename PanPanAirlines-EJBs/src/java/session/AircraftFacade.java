/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Aircraft;
import entity.AircraftDTO;
/**
 *
 * @author John
 */
@Stateless
public class AircraftFacade implements AircraftFacadeRemote
{

    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;

    /*
    public void persist(Object object)
    {
        em.persist(object);
    }
*/
    private void create(Aircraft aircraft)
    {
        em.persist(aircraft);
    }
    
    private void edit(Aircraft aircraft)
    {
        em.merge(aircraft);
    }
    
    private void delete(Aircraft aircraft)
    {
        em.remove(aircraft);
    }
    
    private Aircraft find(int id)
    {
        return em.find(Aircraft.class, id);
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean addAircraft(AircraftDTO aircraft)
    {
        return false;
    }

    @Override
    public AircraftDTO getAircraft(int id)
    {
        return null;
    }

    @Override
    public boolean updateAircraftDetails(AircraftDTO aircraft)
    {
        return false;
    }

    @Override
    public boolean deleteAircraft(int id)
    {
        return false;
    }
}
