/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author John
 */
@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByEmployeeid", query = "SELECT e FROM Employee e WHERE e.employeeid = :employeeid")
    , @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname")
    , @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname")
    , @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address")
    , @NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone")
    , @NamedQuery(name = "Employee.findByRolegroup", query = "SELECT e FROM Employee e WHERE e.rolegroup = :rolegroup")
    , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
    , @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username")
    , @NamedQuery(name = "Employee.findByPasswordplain", query = "SELECT e FROM Employee e WHERE e.passwordplain = :passwordplain")
    , @NamedQuery(name = "Employee.findByPasswordencrypted", query = "SELECT e FROM Employee e WHERE e.passwordencrypted = :passwordencrypted")
    , @NamedQuery(name = "Employee.findByActive", query = "SELECT e FROM Employee e WHERE e.active = :active")
})
public class Employee implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPLOYEEID")
    private Integer employeeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ADDRESS")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ROLEGROUP")
    private String rolegroup;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "PASSWORDPLAIN")
    private String passwordplain;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PASSWORDENCRYPTED")
    private String passwordencrypted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVE")
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeid")
    private List<Flightcrew> flightcrewList;

    public Employee()
    {
    }

    public Employee(Integer employeeid)
    {
        this.employeeid = employeeid;
    }

    public Employee(Integer employeeid, String firstname, String lastname, String address, String phone, String rolegroup, String email, String username, String passwordplain, String passwordencrypted, Boolean active)
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
        return passwordencrypted;
    }

    public void setPasswordencrypted(String passwordencrypted)
    {
        this.passwordencrypted = passwordencrypted;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    @XmlTransient
    public List<Flightcrew> getFlightcrewList()
    {
        return flightcrewList;
    }

    public void setFlightcrewList(List<Flightcrew> flightcrewList)
    {
        this.flightcrewList = flightcrewList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (employeeid != null ? employeeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee))
        {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeid == null && other.employeeid != null) || (this.employeeid != null && !this.employeeid.equals(other.employeeid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Employee[ employeeid=" + employeeid + " ]";
    }
    
}
