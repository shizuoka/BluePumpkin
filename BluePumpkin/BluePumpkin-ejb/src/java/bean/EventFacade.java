/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Event;
import entities.EventType;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungThanh
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> {

    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }

    public boolean addNewEvent(String eventID, String eventTitle, String des, Date startDate, Date endDate, String status, String eventTypeID) {
        EventType et = em.find(EventType.class, eventTypeID);
        Event e = new Event();
        e.setEventID(eventID);
        e.setEventTitle(eventTitle);
        e.setDescription(des);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        e.setStatus(status);
        e.setEventTypeID(et);

        et.getEventList().add(e);
        em.merge(et);
        return true;
    }

    public Event getMaxEventID() {
        return (Event) em.createQuery("select e from Event e where e.eventID = (select MAX(e2.eventID) from Event e2)").getSingleResult();
    }

    public boolean updateEvent(String eventID, String eventTitle, String des, Date startDate, Date endDate, String status, String eventTypeID) {
        Event e = em.find(Event.class, eventID);
        EventType et = em.find(EventType.class, eventTypeID);
        e.setEventTitle(eventTitle);
        e.setDescription(des);
        e.setDescription(des);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        e.setStatus(status);
        e.setEventTypeID(et);
        em.merge(e);
        return true;
    }

    public boolean deleteEvent(Event e) {
        em.remove(em.merge(e));
        return true;
    }
}
