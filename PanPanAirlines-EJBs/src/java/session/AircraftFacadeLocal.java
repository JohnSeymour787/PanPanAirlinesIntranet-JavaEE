/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Aircraft;
import entity.AircraftDTO;
import javax.ejb.Local;

/**
 *
 * @author John
 */
@Local
public interface AircraftFacadeLocal
{
    Aircraft find(int id);
    
    boolean addAircraft(Aircraft aircraft);

    boolean aircraftExists(int id);
    
    boolean updateAircraftDetails(Aircraft aircraft);
    
    boolean deleteAircraft(int id);

    //AircraftDTO daoToDTO(Aircraft aircraft);
    
    Aircraft dtoToDAO(AircraftDTO aircraft);
}
