/*
    ToDo:
    -Delete encrypted password code
    -Also need to determine whether to include plain password as well
*/
package web;

import entity.EmployeeDTO;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import session.EmployeeFacadeRemote;

/**
 *
 * @author John
 */
@Named(value = "employeeManagedBean")
@RequestScoped
public class EmployeeManagedBean
{
    @EJB
    private EmployeeFacadeRemote employeeManagement;
    
    private Integer employeeid = null;
    private String firstname = null;
    private String lastname = null;
    private String address = null;
    private String phone = null;
    private String rolegroup = null;
    private String email = null;
    private String username = null;
    private String newPassword = null;
    //private String confirmedPassword = null;
    private Boolean active = null;
    private List<EmployeeDTO> employees = null;
 
    public EmployeeManagedBean()
    {
    }

    public List<EmployeeDTO> getEmployees()
    {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees)
    {
        this.employees = employees;
    }

    public EmployeeFacadeRemote getEmployeeManagement()
    {
        return employeeManagement;
    }

    public void setEmployeeManagement(EmployeeFacadeRemote employeeManagement)
    {
        this.employeeManagement = employeeManagement;
    }

    public Integer getEmployeeid()
    {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid)
    {
        this.employeeid = employeeid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getRolegroup()
    {
        return rolegroup;
    }

    public void setRolegroup(String rolegroup)
    {
        this.rolegroup = rolegroup;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }
/*
    public String getConfirmedPassword()
    {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword)
    {
        this.confirmedPassword = confirmedPassword;
    }
*/
    //Need this 4 different methods all doing the same thing for Employee/mainmenu.xhtml buttons to send the client
    //to the correct page, through faces-config naviagation cases.
    public String prepareForEmployeeAdd()
    {
        return isAdmin();
    }
    public String prepareForEmployeeView()
    {        
        boolean admin = isAdmin().equals("Admin");
        employeeid = 1;
        
        //Only admins will be able to view all employees, so only now update the list with values
        if (admin)
            employees = employeeManagement.getAllEmployees();
        
        //If not admin, then get the details only needed for standard users (ie, not role group, active, and others)
        //Also, if this method returns false, then a critical error occurred (should not technically be possible).
        else if (!getLimitedEmployeeDetails())
            return "Error";
        
        return isAdmin();
    }
    public String prepareForEmployeeUpdate()
    {
        return isAdmin();
    } 
    public String prepareForEmployeeDelete()
    {
        return isAdmin();
    }
    
    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }
    
    public boolean addEmployee()
    {
        if (employeeid == null)
            return false;
        
        //Assuming new employee to be added is immediately active
        active = true;
        
        EmployeeDTO dtoToAdd = new EmployeeDTO
        (
            employeeid, 
            firstname, 
            lastname, 
            address, 
            phone, 
            rolegroup, 
            email, 
            username, 
            newPassword, 
            active
        );
        
        return employeeManagement.createEmployee(dtoToAdd);
    }
    
    public boolean getEmployeeDetails()
    {
        if (employeeid == null)
            return false;

        EmployeeDTO employee = employeeManagement.getEmployeeDetails(employeeid);
        
        if (employee == null)
            return false;

        updateClientFields(employee);
        
        return true;
    }
    
    //Asks the EmployeeFacadeRemote bean to return a DTO with restricted fields left unset.
    //These include: active, employeeID, password, and rolegroup.
    private boolean getLimitedEmployeeDetails()
    {
        if (employeeid == null)
            return false;

        EmployeeDTO employee = employeeManagement.getLimitedEmployeeDetails(employeeid);
        
        if (employee == null)
            return false;

        updateClientFields(employee);
        
        return true;
    }
    
    //Sets the fields of this bean with a DTO's details, ready for use by a JSF page
    private void updateClientFields(EmployeeDTO details)
    {
        firstname = details.getFirstname();
        lastname = details.getLastname();
        address = details.getAddress();
        phone = details.getPhone();
        email = details.getEmail();
        username = details.getUsername();
        
        //These details will not be set if getLimitedEmployeeDetails is called
        employeeid = details.getEmployeeid();
        rolegroup = details.getRolegroup();
        active = details.getActive();
    }
    
    public boolean updateEmployeeDetails()
    {
        if (employeeid == null)
            return false;
        
        EmployeeDTO dtoToUpdate = new EmployeeDTO(employeeid, firstname, lastname, address, phone, rolegroup, email, username, newPassword, active);
        
        return employeeManagement.updateEmployee(dtoToUpdate);
    }
    
    public void validateMatchingPasswords(FacesContext context, UIComponent component, Object value)
    {
        String confirmedPassword = (String)value;
        
        String passwordField = (String)((UIInput)component.findComponent("password")).getLocalValue();
        
        if (!confirmedPassword.equals(passwordField))
        {
            ((UIInput)component).setValid(false);
            FacesMessage message = new FacesMessage("Passwords do not match!");
            context.addMessage(component.getClientId(context), message);
        }
    }
    
    //Update employee password
    
    
    public boolean removeEmployee()
    {
        if (employeeid == null)
            return false;
        
        return employeeManagement.deleteEmployee(employeeid);
    }
    
    //Sets the employeeID field based on the UserPrinciple of the currently logged in user
    private void setLoggedInID()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        try
        {            
            employeeid = Integer.parseInt(request.getUserPrincipal().getName());
        }
        catch (NumberFormatException e)
        {
            employeeid = null;
        }
    }
    
    private String isAdmin()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        return true ? "Admin" : "NoAdmin";
        //employeeid = Integer.parseInt(request.getUserPrincipal().getName());
        
        //return employeeManagement.isAdmin(employeeid);
    }
}
