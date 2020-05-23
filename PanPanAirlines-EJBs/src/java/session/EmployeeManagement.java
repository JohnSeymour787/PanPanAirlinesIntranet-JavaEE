/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EmployeeDTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author John
 */
@Stateless
@LocalBean
public class EmployeeManagement implements EmployeeFacadeRemote
{
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    
    @Override
    public boolean createEmployee(EmployeeDTO employee)
    {
        return employeeFacade.createEmployee(employee);
    }

    @Override
    public EmployeeDTO getEmployeeDetails(int id)
    {
        return employeeFacade.getEmployeeDetails(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee)
    {
        return employeeFacade.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int id)
    {
        return employeeFacade.deleteEmployee(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}