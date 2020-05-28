/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author John
 */
public class FlightCrewDTO
{
    private final int crewid;
    private final Integer id;
    private final List<EmployeeDTO> employees;

    public FlightCrewDTO(int crewid, Integer id, List<EmployeeDTO> employees)
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

    public List<EmployeeDTO> getEmployees()
    {
        return employees;
    }
}
