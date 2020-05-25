/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author John
 */
@Named(value = "flightCrewManagedBean")
@RequestScoped
public class FlightCrewManagedBean
{

    /**
     * Creates a new instance of FlightCrewManagedBean
     */
    public FlightCrewManagedBean()
    {
        
    }
    
}
