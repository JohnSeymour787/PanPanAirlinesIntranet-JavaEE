/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.FlightCrewDTO;
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
    public boolean createFlightCrew(FlightCrewDTO flightCrew)
    {
        return flightCrewFacade.createFlightCrew(flightCrew);
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
}
