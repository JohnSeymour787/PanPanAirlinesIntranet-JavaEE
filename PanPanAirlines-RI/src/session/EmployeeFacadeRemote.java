/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.EmployeeDTO;
import java.util.List;
/**
 *
 * @author John
 */
@Remote
public interface EmployeeFacadeRemote
{
    boolean createEmployee(EmployeeDTO employee);

    EmployeeDTO getEmployeeDetails(int id);

    boolean updateEmployee(EmployeeDTO employee);

    boolean deleteEmployee(int id);

    boolean validatePassword(String passwordPlain, String passwordConfirm);

    boolean isAdmin(Integer employeeID);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getLimitedEmployeeDetails(Integer employeeID);
}
