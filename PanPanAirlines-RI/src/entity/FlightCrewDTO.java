/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author John
 */
public class FlightCrewDTO
{
    private final int crewid;
    private final Integer id;
    private final EmployeeDTO employeeid;
    //private Collection<Scheduledflight> scheduledflightCollection;

    public FlightCrewDTO(int crewid, Integer id, EmployeeDTO employeeid)
    {
        this.crewid = crewid;
        this.id = id;
        this.employeeid = employeeid;
    }

    public int getCrewid()
    {
        return crewid;
    }

    public Integer getId()
    {
        return id;
    }

    public EmployeeDTO getEmployeeid()
    {
        return employeeid;
    }
    
    
}
