/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import entities.Event;
import entities.RegisterEvent;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SONPV90
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

    public List<RegisterEvent> findByIsAccept() {
        return em.createNamedQuery("RegisterEvent.findByIsAccept").setParameter("isAccept", Boolean.FALSE).getResultList();
    }

    public boolean acceptRegist(int registID) {
        RegisterEvent r = em.find(RegisterEvent.class, registID);
        r.setIsAccept(Boolean.TRUE);
        em.merge(r);
        return true;
    }

    public List<RegisterEvent> getRegisterEventByEmployee(String employeeID) {
        return em.createQuery("select r from RegisterEvent r where r.employeeID.employeeID = :employeeID")
                .setParameter("employeeID", employeeID).getResultList();
    }

    public int countRegisterByStatus(String eventId) {
        return em.createQuery("select r from RegisterEvent r where r.eventID.eventID = :eventId and r.isAccept = :isAccept")
                .setParameter("eventId", eventId).setParameter("isAccept", Boolean.TRUE).getResultList().size();
    }

    public int count(String evenID) {
        Query x = em.createQuery("SELECT r FROM RegisterEvent r WHERE r.eventID.eventID = :eventID");
        x.setParameter("eventID", evenID);
        int result = x.getResultList().size();
        return result;
    }
    
    public List<Employee> findEmployeeByEventId(String eventId) {
        return em.createQuery("SELECT r.employeeID FROM RegisterEvent r WHERE r.eventID.eventID = :eventId AND r.isAccept = :isAccept")
                .setParameter("eventId", eventId)
                .setParameter("isAccept", true)
                .getResultList();
    }
    
}
