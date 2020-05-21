/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.EmployeeDTO;
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
    
}
