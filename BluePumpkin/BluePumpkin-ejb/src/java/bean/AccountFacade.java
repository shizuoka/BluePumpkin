/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
import entities.Employee;
import entities.Roles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungThanh
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    public Account login(String username, String password) {
        return (Account) ((em.createQuery("SELECT a FROM Account a WHERE a.userName.employeeID = :username and a.passWord = :password")
                .setParameter("username", username).setParameter("password", password)).getSingleResult());
    }

    public boolean createAccount(String password, int roleID, String customerID) {
        boolean flag = false;
        try {
            Roles role = em.find(Roles.class, roleID);
            Employee emp = em.find(Employee.class, customerID);
            Account acc = new Account(password, role, emp);
            emp.getAccountList().add(acc);
            role.getAccountList().add(acc);
            em.persist(acc);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean deleteAccount(Account acc) {
        boolean flag = false;
        try {
            em.remove(em.merge(acc));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
