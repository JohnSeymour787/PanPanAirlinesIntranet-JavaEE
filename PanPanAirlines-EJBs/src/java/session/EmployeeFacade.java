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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author John
 */
@Stateless
public class EmployeeFacade implements EmployeeFacadeLocal
{
    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;
    
    private void newEmployee(Employee employee) throws Exception
    {
        em.persist(employee);
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
            "Encrypted password", 
            employeeDTO.getActive()
        );

        return result;
    }
    
        
    @Override
    public Employee find(int id)
    {
        return em.find(Employee.class, id);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean createEmployee(EmployeeDTO employee)
    {
        if (employee == null)
            return false;
        
        //Check that employee does not already exist with this ID
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
            //employeeDAO.getPasswordencrypted(),
            employeeDAO.getActive()
        );

        return result;
    }
    
    private EmployeeDTO daoToDto(Employee employee)
    {
        if (employee == null) return null;

        EmployeeDTO result = new EmployeeDTO
        (
            employee.getEmployeeid(), 
            employee.getFirstname(), 
            employee.getLastname(), 
            employee.getAddress(), 
            employee.getPhone(), 
            employee.getRolegroup(), 
            employee.getEmail(), 
            employee.getUsername(),
            employee.getActive()
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

    @Override
    public boolean employeeExists(int id)
    {
        return find(id) != null;
    }

    /*
        Converts an array of ints to an ArrayList of EmployeeDTOs
    */
    @Override
    public ArrayList<EmployeeDTO> employeeIDsToDTOs(int[] employeeIDs)
    {
        if (employeeIDs == null)
            return null;
        
        ArrayList<EmployeeDTO> result = new ArrayList<>();
        EmployeeDTO DTOElement = null;
        
        for (int employeeID : employeeIDs)
        {
            //Finding the EmployeeDTO from the DB for each empID
            DTOElement = daoToDto(find(employeeID));
            if (DTOElement != null)
                result.add(DTOElement);
        }
        
        return result;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees()
    {
        //Finding all employee DAOs in the DB
        Query query = em.createNamedQuery("Employee.findAll");
        List<Employee> daoArray = new ArrayList<>(query.getResultList());
        
        List<EmployeeDTO> result = new ArrayList<>();
        
        for (Employee employee : daoArray)
            result.add(daoToDto(employee));
        
        return result;
    }
}
