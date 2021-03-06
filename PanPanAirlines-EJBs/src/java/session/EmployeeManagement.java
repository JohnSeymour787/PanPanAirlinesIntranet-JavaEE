/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EmployeeDTO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author John
 */
@Stateless
public class EmployeeManagement implements EmployeeManagementRemote
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

    @Override
    public boolean validatePassword(String passwordPlain, String passwordConfirm)
    {
        return false;
    }

    @Override
    public boolean isAdmin(Integer employeeID)
    {
        return employeeFacade.isAdmin(employeeID);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees()
    {
        return employeeFacade.getAllEmployees();
    }

    @Override
    public EmployeeDTO getLimitedEmployeeDetails(Integer employeeID)
    {
        return employeeFacade.getLimitedEmployeeDTO(employeeID);
    }
}
