/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "AIRCRAFT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Aircraft.findAll", query = "SELECT a FROM Aircraft a")
    , @NamedQuery(name = "Aircraft.findByAircraftid", query = "SELECT a FROM Aircraft a WHERE a.aircraftid = :aircraftid")
    , @NamedQuery(name = "Aircraft.findByAircrafttype", query = "SELECT a FROM Aircraft a WHERE a.aircrafttype = :aircrafttype")
    , @NamedQuery(name = "Aircraft.findBySeats", query = "SELECT a FROM Aircraft a WHERE a.seats = :seats")
    , @NamedQuery(name = "Aircraft.findByManufacturer", query = "SELECT a FROM Aircraft a WHERE a.manufacturer = :manufacturer")
})
public class Aircraft implements Serializable
{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraftid")
    private Collection<Scheduledflight> scheduledflightCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AIRCRAFTID")
    private Integer aircraftid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "AIRCRAFTTYPE")
    private String aircrafttype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEATS")
    private int seats;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MANUFACTURER")
    private String manufacturer;

    public Aircraft()
    {
    }

    public Aircraft(Integer aircraftid)
    {
        this.aircraftid = aircraftid;
    }

    public Aircraft(Integer aircraftid, String aircrafttype, int seats, String manufacturer)
    {
        this.aircraftid = aircraftid;
        this.aircrafttype = aircrafttype;
        this.seats = seats;
        this.manufacturer = manufacturer;
    }

    public Integer getAircraftid()
    {
        return aircraftid;
    }

    public void setAircraftid(Integer aircraftid)
    {
        this.aircraftid = aircraftid;
    }

    public String getAircrafttype()
    {
        return aircrafttype;
    }

    public void setAircrafttype(String aircrafttype)
    {
        this.aircrafttype = aircrafttype;
    }

    public int getSeats()
    {
        return seats;
    }

    public void setSeats(int seats)
    {
        this.seats = seats;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (aircraftid != null ? aircraftid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aircraft))
        {
            return false;
        }
        Aircraft other = (Aircraft) object;
        if ((this.aircraftid == null && other.aircraftid != null) || (this.aircraftid != null && !this.aircraftid.equals(other.aircraftid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Aircraft[ aircraftid=" + aircraftid + " ]";
    }

    @XmlTransient
    public Collection<Scheduledflight> getScheduledflightCollection()
    {
        return scheduledflightCollection;
    }

    public void setScheduledflightCollection(Collection<Scheduledflight> scheduledflightCollection)
    {
        this.scheduledflightCollection = scheduledflightCollection;
    }
    
}
