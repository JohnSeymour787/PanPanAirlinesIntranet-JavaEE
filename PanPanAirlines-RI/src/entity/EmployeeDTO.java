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
    private final String passwordencrypted;
    private final Boolean active;
    
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeid")
    //private Collection<Flightcrew> flightcrewCollection;

    public EmployeeDTO(Integer employeeid, String firstname, String lastname, String address, String phone, String rolegroup, String email, String username, String passwordplain, String passwordencrypted, Boolean active)
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
        this.passwordencrypted = passwordencrypted;
        this.active = active;
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

    public String getPasswordencrypted()
    {
        return passwordencrypted;
    }

    public Boolean getActive()
    {
        return active;
    }
}
