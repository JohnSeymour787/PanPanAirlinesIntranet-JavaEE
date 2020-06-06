/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EmployeeDTO;
import javax.ejb.Remote;
import entity.FlightCrewDTO;
import java.util.List;
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

    boolean isAdmin(Integer employeeID);

    List<EmployeeDTO> addEmployeeToCrew(Integer employeeID, Integer id);

    boolean saveListToDB();
}
