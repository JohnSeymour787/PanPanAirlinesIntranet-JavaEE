/*
    ToDo:
    -Delete password encrypted code
    -
*/
package entity;

/**
 *
 * @author John
 */
public class EmployeeDTO
{
    private final Integer employeeid;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String phone;
    private final String rolegroup;
    private final String email;
    private final String username;
    private final String passwordplain;
    private final Boolean active;

    public EmployeeDTO(Integer employeeid, String firstname, String lastname, String address, String phone, String rolegroup, String email, String username, String passwordplain, Boolean active)
    {
        this.employeeid = employeeid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.rolegroup = rolegroup;
        this.email = email;
        this.username = username;
        this.passwordplain = passwordplain;
        //this.passwordencrypted = passwordencrypted;
        this.active = active;
    }

    //Constructor without setting password field
    public EmployeeDTO(Integer employeeid, String firstname, String lastname, String address, String phone, String rolegroup, String email, String username, Boolean active)
    {
        this.employeeid = employeeid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.rolegroup = rolegroup;
        this.email = email;
        this.username = username;
        this.active = active;
        this.passwordplain = null;
    }

    public Integer getEmployeeid()
    {
        return employeeid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getRolegroup()
    {
        return rolegroup;
    }

    public String getEmail()
    {
        return email;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPasswordplain()
    {
        return passwordplain;
    }



    public Boolean getActive()
    {
        return active;
    }
    
    
    //****************************************************************** TO REMOVE****************************//
    //****************************************************************** TO REMOVE****************************//
    //****************************************************************** TO REMOVE****************************//
    public String getPasswordencrypted()
    {
        return "";
    }
}
