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
public class AircraftFacade implements AircraftFacadeLocal
{

    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;

    
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
    
    @Override
    public Aircraft find(int id)
    {
        return em.find(Aircraft.class, id);
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")


    
    
    @Override
    public boolean addAircraft(Aircraft aircraft)
    {
        if (aircraft == null) 
            return false;
        
        //If aircraft already exists in the DB
        if (!aircraftExists(aircraft.getAircraftid())) 
            return false;
        
        try
        {
            create(aircraft);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }



    @Override
    public boolean updateAircraftDetails(Aircraft aircraft)
    {
        if (aircraft == null)
            return false;
        
        //Cannot edit record that doesn't exist
        if (!aircraftExists(aircraft.getAircraftid()))
            return false;
        
        try
        {
            edit(aircraft);
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

    @Override
    public boolean aircraftExists(int id)
    {
        return find(id) != null;
    }

    //@Override
    public AircraftDTO daoToDTO(Aircraft aircraft)
    {
        if (aircraft == null)
            return null;
        
        AircraftDTO result = new AircraftDTO
        (
            aircraft.getAircraftid(), 
            aircraft.getAircrafttype(), 
            aircraft.getSeats(), 
            aircraft.getManufacturer()
        );
        
        return result;
    }
    
    //Converts the externally-available DTO to a DAO for use with the EntityManager
    @Override
    public Aircraft dtoToDAO(AircraftDTO aircraft)
    {
        if (aircraft == null)
            return null;
        
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
}
