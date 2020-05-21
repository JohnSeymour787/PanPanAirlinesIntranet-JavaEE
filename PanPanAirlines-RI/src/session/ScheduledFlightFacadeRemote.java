/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.ScheduledFlightDTO;
/**
 *
 * @author John
 */
@Remote
public interface ScheduledFlightFacadeRemote
{

    boolean createScheduledFlight(ScheduledFlightDTO flight);

    ScheduledFlightDTO findScheduledFlight(int id);

    boolean updateScheduledFlight(ScheduledFlightDTO flight);

    boolean deleteScheduledFlight(int id);
    
}
