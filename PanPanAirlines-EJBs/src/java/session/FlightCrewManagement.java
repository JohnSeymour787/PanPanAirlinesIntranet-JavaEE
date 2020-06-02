/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EmployeeDTO;
import entity.FlightCrewDTO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author John
 */
@Stateful
public class FlightCrewManagement implements FlightCrewManagementRemote
{
    @EJB
    private FlightCrewFacadeLocal flightCrewFacade;
    
    @Override
    public boolean createFlightCrew(int id, int crewID, int firstEmployeeID)
    {
        return flightCrewFacade.createFlightCrew(id, crewID, firstEmployeeID);
    }

    @Override
    public FlightCrewDTO findFlightCrew(int id)
    {
        return flightCrewFacade.findFlightCrew(id);
    }

    @Override
    public boolean updateFlightCrew(FlightCrewDTO flightCrew)
    {
        return flightCrewFacade.updateFlightCrew(flightCrew);
    }

    @Override
    public boolean deleteFlightCrew(int id)
    {
        return flightCrewFacade.deleteFlightCrew(id);
    } 

    @Override
    public boolean isAdmin(Integer employeeID)
    {
        return flightCrewFacade.isAdmin(employeeID);
    }

    @Override
    public List<EmployeeDTO> addEmployeeToCrew(Integer employeeID, Integer id)
    {
        return flightCrewFacade.addEmployeeToCrew(employeeID, id);
    }

    @Override
    public boolean saveListToDB()
    {
        return flightCrewFacade.saveListToDB();
    }
}
