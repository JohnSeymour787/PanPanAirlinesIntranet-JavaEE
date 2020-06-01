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
        
        //Only admins will be able to view all employees, so only now update the list with values
        if (admin)
            employees = employeeManagement.getAllEmployees();
        
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
    
    
    public boolean findEmployee()
    {
        if (employeeid == null)
            return false;
        
        EmployeeDTO employee = employeeManagement.getEmployeeDetails(employeeid);
        
        if (employee == null)
            return false;
        
        displayDetails(employee);
        
        return true;
    }
    
    private void displayDetails(EmployeeDTO details)
    {
        employeeid = details.getEmployeeid();
        firstname = details.getFirstname();
        lastname = details.getLastname();
        address = details.getAddress();
        phone = details.getPhone();
        rolegroup = details.getRolegroup();
        email = details.getEmail();
        username = details.getUsername();
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
    
    private void setLoggedInID()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        employeeid = Integer.parseInt(request.getUserPrincipal().getName());
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
