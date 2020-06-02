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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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

    private Integer crewid;
    private Integer id;
    private Integer employeeID;
    private List<EmployeeDTO> employees;
    
    
    public FlightCrewManagedBean()
    {
    }

    public Integer getCrewid()
    {
        return crewid;
    }

    public void setCrewid(Integer crewid)
    {
        this.crewid = crewid;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID)
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

    public String findCrewDetails()
    {
        FlightCrewDTO flightCrew = flightCrewManagement.findFlightCrew(crewid);

        if (flightCrew == null)
            return "Error";

        employees = flightCrew.getEmployees();
        crewid = flightCrew.getCrewid();
        
        return isAdmin() ? "Admin" : "NoAdmin";
    }
    
    private boolean isAdmin()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        Integer employeeid = Integer.parseInt(request.getUserPrincipal().getName());
        
        return flightCrewManagement.isAdmin(employeeid);
    }
    
    public boolean addEmployeeToCrew()
    {
        employees = flightCrewManagement.addEmployeeToCrew(employeeID, id);
        employeeID = null;
        id = null;
        return employees != null;
    }
    
    public boolean saveListToDB()
    {
        return flightCrewManagement.saveListToDB();
    }
}
