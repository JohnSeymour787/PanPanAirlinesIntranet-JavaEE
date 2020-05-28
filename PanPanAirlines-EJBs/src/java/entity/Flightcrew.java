/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "FLIGHTCREW")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Flightcrew.findAll", query = "SELECT f FROM Flightcrew f")
    , @NamedQuery(name = "Flightcrew.findByCrewid", query = "SELECT f FROM Flightcrew f WHERE f.crewid = :crewid")
    , @NamedQuery(name = "Flightcrew.findByEmployeeid", query = "SELECT f FROM Flightcrew f WHERE f.employeeid = :employeeid")
    , @NamedQuery(name = "Flightcrew.findById", query = "SELECT f FROM Flightcrew f WHERE f.id = :id")
})
public class Flightcrew implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREWID")
    private int crewid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPLOYEEID")
    private int employeeid;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;

    public Flightcrew()
    {
    }

    public Flightcrew(Integer id)
    {
        this.id = id;
    }

    public Flightcrew(Integer id, int crewid, int employeeid)
    {
        this.id = id;
        this.crewid = crewid;
        this.employeeid = employeeid;
    }

    public int getCrewid()
    {
        return crewid;
    }

    public void setCrewid(int crewid)
    {
        this.crewid = crewid;
    }

    public int getEmployeeid()
    {
        return employeeid;
    }

    public void setEmployeeid(int employeeid)
    {
        this.employeeid = employeeid;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flightcrew))
        {
            return false;
        }
        Flightcrew other = (Flightcrew) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Flightcrew[ id=" + id + " ]";
    }
    
}
