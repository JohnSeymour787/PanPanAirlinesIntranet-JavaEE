/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.AircraftDTO;
/**
 *
 * @author John
 */
@Remote
public interface AircraftManagementRemote
{
    boolean addAircraft(AircraftDTO aircraft);

    AircraftDTO getAircraft(int id);

    boolean updateAircraftDetails(AircraftDTO aircraft);

    boolean deleteAircraft(int id);
}
