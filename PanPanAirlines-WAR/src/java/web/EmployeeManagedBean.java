/*
    ToDo:
    -Delete encrypted password code
    -Also need to determine whether to include plain password as well
*/
package web;

import entity.EmployeeDTO;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import session.EmployeeFacadeRemote;

/**
 *
 * @author John
 */
@Named(value = "employee")
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
    private String passwordplain = null;
    //private String passwordencrypted;
    private Boolean active = null;
    
    /**
     * Creates a new instance of Employee
     */
    public EmployeeManagedBean()
    {
    }

    public boolean createEmployee()
    {
        EmployeeDTO toAdd = new EmployeeDTO(employeeid, firstname, lastname, address, phone, rolegroup, email, username, passwordplain, active);
        
        return employeeManagement.createEmployee(toAdd);
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
        passwordplain = details.getPasswordplain();
        active = details.getActive();
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

    public String getPasswordplain()
    {
        return passwordplain;
    }

    public void setPasswordplain(String passwordplain)
    {
        this.passwordplain = passwordplain;
    }

    public String getPasswordencrypted()
    {
        return "";
    }

    public void setPasswordencrypted(String passwordencrypted)
    {
        //this.passwordencrypted = passwordencrypted;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }
    
    
}
