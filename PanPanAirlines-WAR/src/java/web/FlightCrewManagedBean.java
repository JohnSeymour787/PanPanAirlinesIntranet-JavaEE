/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.FlightCrewDTO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    private String employees;
    //private EmployeeDTO employees;
    
    
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

    public String getEmployees()
    {
        return employees;
    }

    public void setEmployees(String employees)
    {
        this.employees = employees;
    }
    
    public boolean AddFlight()
    {
        //FlightCrewDTO toAdd;// = new FlightCrewDTO(crewid, id, employees);
        //toAdd = new FlightCrewDTO(crewid, id, employees);
        
        
        
        //flightCrewManagement.createFlightCrew(toAdd);
        return false;
    }
    /*
        TODO:
        -Need to do something with returned flightCrew local variable
    */
    
    public boolean findCrewDetails()
    {
        FlightCrewDTO flightCrew = flightCrewManagement.findFlightCrew(id);
        
        if (flightCrew == null)
            return false;
        else
            return true;
    }
}
