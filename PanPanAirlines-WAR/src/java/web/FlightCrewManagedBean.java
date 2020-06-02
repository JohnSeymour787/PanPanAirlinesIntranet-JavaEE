/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.EmployeeDTO;
import entity.FlightCrewDTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import session.FlightCrewManagementRemote;

/**
 *
 * @author John
 */
@Named(value = "flightCrewManagedBean")
@SessionScoped
public class FlightCrewManagedBean implements Serializable
{

    @EJB
    private FlightCrewManagementRemote flightCrewManagement;

    private int crewid;
    private int id;
    private int employeeID;
    private List<EmployeeDTO> employees;
    
    
    public FlightCrewManagedBean()
    {
    }

    public int getCrewid()
    {
        return crewid;
    }

    public void setCrewid(int crewid)
    {
        this.crewid = crewid;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
    }

    public List<EmployeeDTO> getEmployees()
    {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees)
    {
        this.employees = employees;
    }

    
    public boolean createCrew()
    {      
        return flightCrewManagement.createFlightCrew(id, crewid, employeeID);
    }
    
    /*
        TODO:
        -Need to do something with returned flightCrew local variable
    */
    
    public boolean findCrewDetails()
    {
        FlightCrewDTO flightCrew = flightCrewManagement.findFlightCrew(crewid);

        if (flightCrew == null)
            return false;

        employees = flightCrew.getEmployees();
        crewid = flightCrew.getCrewid();
        id = flightCrew.getId();
        
        return true;
    }
    
    public boolean addEmployeeToCrew()
    {
        return false;
    }
}
