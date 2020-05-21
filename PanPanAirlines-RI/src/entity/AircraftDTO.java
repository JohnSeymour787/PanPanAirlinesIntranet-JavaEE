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
public class AircraftDTO
{
    private final Integer aircraftID;
    private final String aircraftType;
    private final int seats;
    private final String manufacturer;

    public AircraftDTO(Integer aircraftID, String aircraftType, int seats, String manufacturer)
    {
        this.aircraftID = aircraftID;
        this.aircraftType = aircraftType;
        this.seats = seats;
        this.manufacturer = manufacturer;
    }

    public Integer getAircraftID()
    {
        return aircraftID;
    }

    public String getAircraftType()
    {
        return aircraftType;
    }

    public int getSeats()
    {
        return seats;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }
    
    
}
