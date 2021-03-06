/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Employee;
import entity.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author John
 */
@Local
public interface EmployeeFacadeLocal
{
    Employee find(int id);
    
    EmployeeDTO getEmployeeDetails(int id);

    boolean createEmployee(EmployeeDTO employee);

    boolean updateEmployee(EmployeeDTO employee);

    boolean deleteEmployee(int id);
    
    boolean employeeExists(int id);

    ArrayList<EmployeeDTO> employeeIDsToDTOs(int[] employeeIDs);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getLimitedEmployeeDTO(Integer employeeID);

    boolean isAdmin(Integer employeeID);
}
