/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.mail.internet.MailDateFormat;
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

    public List<Employee> findEmployeeByBirthDate() {
//        int date = new Date().getDate();
//        int month = new Date().getMonth();
        return (List<Employee>) em.createNativeQuery("select * from Employee where MONTH(DateOfBirth) = MONTH(GETDATE()) and DAY(DateOfBirth) = DAY(GETDATE())").getResultList();
    }

    public boolean checkEmployeeExisted(String employeeID) {
        return (Long) em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.employeeID = :employeeID")
                .setParameter("employeeID", employeeID)
                .getSingleResult() == 1;
    }

    public boolean checkEmailExisted(String email) {
        return (Long) em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.email = :email")
                .setParameter("email", email)
                .getSingleResult() == 1;
    }
}
