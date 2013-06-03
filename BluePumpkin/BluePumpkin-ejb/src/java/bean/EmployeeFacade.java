/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SONPV90
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> {

    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    public boolean createEmployee(Employee emp) {
        boolean flag = false;
        try {
            em.persist(emp);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public Employee getMaxEmployeeID() {
        return (Employee) em.createQuery("select e from Employee e where e.employeeID = (select MAX(e2.employeeID) from Employee e2)").getSingleResult();
    }

    public boolean deleteEmployee(Employee emp) {
        boolean flag = false;
        try {
            em.remove(em.merge(emp));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
