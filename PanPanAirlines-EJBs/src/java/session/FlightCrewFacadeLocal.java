/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Flightcrew;
import entity.FlightCrewDTO;
import javax.ejb.Local;

/**
 *
 * @author John
 */
@Local
public interface FlightCrewFacadeLocal
{
    Flightcrew findCrewDAO(Integer id);
    
    FlightCrewDTO findFlightCrew(int crewID);
    
    boolean createFlightCrew(int id, int crewID, int firstEmployeeID);
    
    boolean updateFlightCrew(FlightCrewDTO flightCrew);
    
    boolean deleteFlightCrew(int id);
    
    boolean crewExists(int id);
    
    //Flightcrew dtoToDAO(FlightCrewDTO flightCrew);
}
