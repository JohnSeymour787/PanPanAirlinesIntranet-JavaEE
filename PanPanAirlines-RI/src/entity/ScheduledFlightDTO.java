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
public class ScheduledFlightDTO
{
    private final Integer flightnumber;
    private final String originatingairport;
    private final String destinationairport;
    private final AircraftDTO aircraft;
    private final FlightCrewDTO crew;

    public ScheduledFlightDTO(Integer flightnumber, String originatingairport, String destinationairport, AircraftDTO aircraft, FlightCrewDTO crew)
    {
        this.flightnumber = flightnumber;
        this.originatingairport = originatingairport;
        this.destinationairport = destinationairport;
        this.aircraft = aircraft;
        this.crew = crew;
    }

    public Integer getFlightnumber()
    {
        return flightnumber;
    }

    public String getOriginatingairport()
    {
        return originatingairport;
    }

    public String getDestinationairport()
    {
        return destinationairport;
    }

    public AircraftDTO getAircraft()
    {
        return aircraft;
    }

    public FlightCrewDTO getCrew()
    {
        return crew;
    }
}
