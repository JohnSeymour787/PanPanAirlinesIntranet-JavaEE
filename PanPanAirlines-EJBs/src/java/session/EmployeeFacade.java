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
    
    private void newEmployee(Employee employee)
    {
        em.persist(employee);
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean createEmployee(EmployeeDTO employee)
    {
        return false;
    }

    @Override
    public EmployeeDTO getEmployeeDetails(int id)
    {
        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee)
    {
        return false;
    }

    @Override
    public boolean deleteEmployee(int id)
    {
        return false;
    }   
}
