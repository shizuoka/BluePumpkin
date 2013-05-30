/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
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
    
    public Account loginEmployee(String username, String password) {
        return (Account) ((em.createQuery("SELECT a FROM Account a WHERE a.userName.employeeID = :username and a.passWord = :password")
                .setParameter("username", username).setParameter("password", password)).getSingleResult());
    }
    
}
