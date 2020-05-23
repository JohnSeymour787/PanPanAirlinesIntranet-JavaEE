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
    private void create(Aircraft aircraft) throws Exception
    {
        em.persist(aircraft);
    }
    
    private void edit(Aircraft aircraft) throws Exception
    {
        if (em.merge(aircraft) == null)
            throw new Exception("Nothing updated.");
    }
    
    private void delete(int id) throws Exception
    {
        Aircraft toRemove = find(id);
        if (toRemove == null)
            throw new Exception("Cannot find record.");
        else
            em.remove(toRemove);
    }
    
    private Aircraft find(int id)
    {
        return em.find(Aircraft.class, id);
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    //Converts the externally-available DTO to a DAO for use with the EntityManager
    private Aircraft dtoToDAO(AircraftDTO aircraft)
    {
        Aircraft result;
        
        result = new Aircraft
        (
            aircraft.getAircraftID(), 
            aircraft.getAircraftType(), 
            aircraft.getSeats(), 
            aircraft.getManufacturer()
        );
        
        return result;
    }
    
    
    @Override
    public boolean addAircraft(AircraftDTO aircraft)
    {
        if (aircraft == null) 
            return false;
        
        //If aircraft already exists in the DB
        if (find(aircraft.getAircraftID()) != null) 
            return false;
        
        try
        {
            create(dtoToDAO(aircraft));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public AircraftDTO getAircraft(int id)
    {
        Aircraft tableRecord = find(id);
        
        if (tableRecord == null)
            return null;
        
        //Convert to DTO before returning
        AircraftDTO result = new AircraftDTO
        (
            tableRecord.getAircraftid(), 
            tableRecord.getAircrafttype(), 
            tableRecord.getSeats(), 
            tableRecord.getManufacturer()
        );
        
        return result;
    }

    @Override
    public boolean updateAircraftDetails(AircraftDTO aircraft)
    {
        if (aircraft == null)
            return false;
        
        //Cannot edit record that doesn't exist
        if (find(aircraft.getAircraftID()) == null)
            return false;
        
        try
        {
            edit(dtoToDAO(aircraft));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean deleteAircraft(int id)
    {
        try
        {
            delete(id);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
}
