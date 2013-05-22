/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import entities.Event;
import entities.RegisterEvent;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungThanh
 */
@Stateless
public class RegisterEventFacade extends AbstractFacade<RegisterEvent> {

    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegisterEventFacade() {
        super(RegisterEvent.class);
    }

    public boolean registerEvent(String eventID, String employeeID) {
        Event e = em.find(Event.class, eventID);
        Employee emp = em.find(Employee.class, employeeID);
        Date d = new Date();
        RegisterEvent r = new RegisterEvent(d, Boolean.FALSE, e, emp);
        em.persist(r);
        return true;
    }
}
