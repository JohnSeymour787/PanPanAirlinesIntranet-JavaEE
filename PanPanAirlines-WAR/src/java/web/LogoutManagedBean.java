/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John
 */
@Named(value = "logoutManagedBean")
@SessionScoped
public class LogoutManagedBean implements Serializable
{
    public LogoutManagedBean()
    {
    }
    
    public boolean logout()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        
        try
        {
            request.logout();
        }
        catch (Exception ex)
        {    
            return false;
        }
        
        //Terminating the session now that the user is logged out
        HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
        session.invalidate();
        
        return true;
    }
}
