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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "SCHEDULEDFLIGHT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Scheduledflight.findAll", query = "SELECT s FROM Scheduledflight s")
    , @NamedQuery(name = "Scheduledflight.findByFlightnumber", query = "SELECT s FROM Scheduledflight s WHERE s.flightnumber = :flightnumber")
    , @NamedQuery(name = "Scheduledflight.findByCrewid", query = "SELECT s FROM Scheduledflight s WHERE s.crewid = :crewid")
    , @NamedQuery(name = "Scheduledflight.findByAircraftid", query = "SELECT s FROM Scheduledflight s WHERE s.aircraftid = :aircraftid")
    , @NamedQuery(name = "Scheduledflight.findByOriginatingairport", query = "SELECT s FROM Scheduledflight s WHERE s.originatingairport = :originatingairport")
    , @NamedQuery(name = "Scheduledflight.findByDestinationairport", query = "SELECT s FROM Scheduledflight s WHERE s.destinationairport = :destinationairport")
})
public class Scheduledflight implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FLIGHTNUMBER")
    private Integer flightnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREWID")
    private int crewid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AIRCRAFTID")
    private int aircraftid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ORIGINATINGAIRPORT")
    private String originatingairport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESTINATIONAIRPORT")
    private String destinationairport;

    public Scheduledflight()
    {
    }

    public Scheduledflight(Integer flightnumber)
    {
        this.flightnumber = flightnumber;
    }

    public Scheduledflight(Integer flightnumber, int crewid, int aircraftid, String originatingairport, String destinationairport)
    {
        this.flightnumber = flightnumber;
        this.crewid = crewid;
        this.aircraftid = aircraftid;
        this.originatingairport = originatingairport;
        this.destinationairport = destinationairport;
    }

    public Integer getFlightnumber()
    {
        return flightnumber;
    }

    public void setFlightnumber(Integer flightnumber)
    {
        this.flightnumber = flightnumber;
    }

    public int getCrewid()
    {
        return crewid;
    }

    public void setCrewid(int crewid)
    {
        this.crewid = crewid;
    }

    public int getAircraftid()
    {
        return aircraftid;
    }

    public void setAircraftid(int aircraftid)
    {
        this.aircraftid = aircraftid;
    }

    public String getOriginatingairport()
    {
        return originatingairport;
    }

    public void setOriginatingairport(String originatingairport)
    {
        this.originatingairport = originatingairport;
    }

    public String getDestinationairport()
    {
        return destinationairport;
    }

    public void setDestinationairport(String destinationairport)
    {
        this.destinationairport = destinationairport;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (flightnumber != null ? flightnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scheduledflight))
        {
            return false;
        }
        Scheduledflight other = (Scheduledflight) object;
        if ((this.flightnumber == null && other.flightnumber != null) || (this.flightnumber != null && !this.flightnumber.equals(other.flightnumber)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Scheduledflight[ flightnumber=" + flightnumber + " ]";
    }
    
}
