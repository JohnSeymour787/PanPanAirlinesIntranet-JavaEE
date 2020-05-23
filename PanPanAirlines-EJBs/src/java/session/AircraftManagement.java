/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;
import entity.Aircraft;
import entity.AircraftDTO;
import javax.ejb.EJB;

import javax.ejb.Stateless;

/**
 *
 * @author John
 */
@Stateless
public class AircraftManagement implements AircraftManagementRemote
{
    @EJB
    private AircraftFacadeLocal aircraftFacade;
    //Converts the externally-available DTO to a DAO for use with the EntityManager
    private Aircraft dtoToDAO(AircraftDTO aircraft)
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
    
    
    @Override
    public boolean addAircraft(AircraftDTO aircraft)
    {   
        return aircraftFacade.addAircraft(dtoToDAO(aircraft));
    }

    @Override
    public AircraftDTO getAircraft(int id)
    {
        Aircraft tableRecord = aircraftFacade.find(id);
        
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
        return aircraftFacade.updateAircraftDetails(dtoToDAO(aircraft));
    }

    @Override
    public boolean deleteAircraft(int id)
    {
        return aircraftFacade.deleteAircraft(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
