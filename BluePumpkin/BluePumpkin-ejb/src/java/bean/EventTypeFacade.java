/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.EventType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SONPV90
 */
@Stateless
public class EventTypeFacade extends AbstractFacade<EventType> {
    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventTypeFacade() {
        super(EventType.class);
    }
    
     public boolean addEventType(EventType et) {
        try {
            em.persist(et);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteEventType(EventType et) {
        try {
            em.remove(em.merge(et));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public long countEventOfType(String eventTypeID) {
        return (Long) em.createQuery("SELECT COUNT(e.eventID) FROM Event e where e.eventTypeID.eventTypeID = :eventTypeID")
                .setParameter("eventTypeID", eventTypeID).getSingleResult();
    }
}
