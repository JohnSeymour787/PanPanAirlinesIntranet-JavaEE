/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Employee;
import entity.EmployeeDTO;
/**
 *
 * @author John
 */
@Stateless
public class EmployeeFacade implements EmployeeFacadeRemote
{

    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;

    /*
    public void persist(Object object)
    {
        em.persist(object);
    }
    */
    
    private void newEmployee(Employee employee) throws Exception
    {
        em.persist(employee);
    }
    
    private Employee find(int id)
    {
        return em.find(Employee.class, id);
    }
    
    private void editAll(Employee employee) throws Exception
    {
        if (em.merge(employee) == null)
            throw new Exception("Nothing updated.");
    }
    
    private void delete(int id) throws Exception
    {
        Employee toRemove = find(id);
        if (toRemove == null)
            throw new Exception("Cannot find record to remove.");
        else
            toRemove.setActive(false);
    }
    
    //Assumes parameter is already checked and not null
    private Employee dtoToDAO(EmployeeDTO employeeDTO)
    {
        Employee result;
        
        result = new Employee
        (
            employeeDTO.getEmployeeid(), 
            employeeDTO.getFirstname(), 
            employeeDTO.getLastname(), 
            employeeDTO.getAddress(), 
            employeeDTO.getPhone(), 
            employeeDTO.getRolegroup(), 
            employeeDTO.getEmail(), 
            employeeDTO.getUsername(), 
            employeeDTO.getPasswordplain(), 
            employeeDTO.getPasswordencrypted(), 
            employeeDTO.getActive()
        );
    
        return result;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean createEmployee(EmployeeDTO employee)
    {
        if (employee == null)
            return false;
        
        if (find(employee.getEmployeeid()) != null)
            return false;
        
        try
        {
            newEmployee(dtoToDAO(employee));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public EmployeeDTO getEmployeeDetails(int id)
    {
        Employee employeeDAO = find(id);
        
        if (employeeDAO == null)
            return null;
        
        EmployeeDTO result = new EmployeeDTO
        (
            employeeDAO.getEmployeeid(), 
            employeeDAO.getFirstname(), 
            employeeDAO.getLastname(), 
            employeeDAO.getAddress(), 
            employeeDAO.getPhone(), 
            employeeDAO.getRolegroup(), 
            employeeDAO.getEmail(), 
            employeeDAO.getUsername(),
            employeeDAO.getPasswordplain(),
            employeeDAO.getPasswordencrypted(),
            employeeDAO.getActive()
        );
        
        return result;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee)
    {
        if (employee == null)
            return false;
        
        if (find(employee.getEmployeeid()) == null)
            return false;
        
        try
        {
            editAll(dtoToDAO(employee));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean deleteEmployee(int id)
    {
        try
        {
            delete(id);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }   
}
