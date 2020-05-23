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
    private final EmployeeDTO employees;
    //private Collection<Scheduledflight> scheduledflightCollection;

    public FlightCrewDTO(int crewid, Integer id, EmployeeDTO employees)
    {
        this.crewid = crewid;
        this.id = id;
        this.employees = employees;
    }

    public int getCrewid()
    {
        return crewid;
    }

    public Integer getId()
    {
        return id;
    }

    public EmployeeDTO getEmployees()
    {
        return employees;
    }
    
    
}
