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

    
    
    @Override
    public boolean addAircraft(AircraftDTO aircraft)
    {   
        return aircraftFacade.addAircraft(aircraft);
    }

    @Override
    public AircraftDTO getAircraft(int id)
    {
        return aircraftFacade.findDTO(id);
    }

    @Override
    public boolean updateAircraftDetails(AircraftDTO aircraft)
    {       
        return aircraftFacade.updateAircraftDetails(aircraft);
    }

    @Override
    public boolean deleteAircraft(int id)
    {
        return aircraftFacade.deleteAircraft(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
