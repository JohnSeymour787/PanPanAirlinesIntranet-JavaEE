/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.FlightCrewDTO;
/**
 *
 * @author John
 */
@Remote
public interface FlightCrewManagementRemote
{
    boolean createFlightCrew(int id, int crewID, int firstEmployeeID);

    FlightCrewDTO findFlightCrew(int id);

    boolean updateFlightCrew(FlightCrewDTO flightCrew);

    boolean deleteFlightCrew(int id);   
}
