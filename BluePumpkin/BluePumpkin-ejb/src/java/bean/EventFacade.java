/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Event;
import entities.EventType;
import entities.Prizes;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author SONPV90
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

    public Event findByEventID(String eventID) {
        return (Event) em.createNamedQuery("Event.findByEventID").setParameter("eventID", eventID).getSingleResult();
    }

    public boolean addNewEvent(String eventID, String eventTitle, String des, Date startDate, Date endDate, String status, String eventTypeID, int noAccount, Date createDate, String image) {
        boolean flag = false;
        try {
            EventType et = em.find(EventType.class, eventTypeID);
            Event e = new Event();
            e.setEventID(eventID);
            e.setEventTitle(eventTitle);
            e.setDescription(des);
            e.setStartDate(startDate);
            e.setEndDate(endDate);
            e.setStatus(status);
            e.setEventTypeID(et);
            e.setNumberEmployee(noAccount);
            e.setCreateDate(createDate);
            e.setImage(image);

            et.getEventList().add(e);
            em.merge(et);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public Event getMaxEventID() {
        return (Event) em.createQuery("select e from Event e where e.eventID = (select MAX(e2.eventID) from Event e2)").getSingleResult();
    }

    public boolean updateEvent(String eventID, String eventTitle, String des, Date startDate, Date endDate, String status, String eventTypeID) {
        boolean flag = false;
        try {
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
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean deleteEvent(Event e) {
        boolean flag = false;
        try {
            Prizes p = (Prizes) em.createQuery("select r from Prizes r where r.eventID.eventID = :eventID").setParameter("eventID", e.getEventID()).getSingleResult();
            em.remove(em.merge(p));
            em.remove(em.merge(e));
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<Event> findByDate() {
        Date start = new Date();
        Date end = new Date();
        return em.createQuery("select e from Event e where e.startDate BETWEEN :startDate and :endDate")
                .setParameter("startDate", start, TemporalType.DATE).setParameter("endDate", end, TemporalType.DATE).getResultList();
    }

//    public int searchStatistic(String status){
//        return (Integer)em.createQuery("select count(e.eventID) from Event e where e.status = :status").setParameter("status", status).s;
//    }
    public List<Event> findByStatus(String status) {
        return em.createNamedQuery("Event.findByStatus").setParameter("status", status).getResultList();
    }

    public List<Event> showEventsTop(int limit) {
        return em.createQuery("select e from Event e WHERE e.status = 'Oncoming' OR e.status='Ended' order by e.eventID DESC").setMaxResults(limit).getResultList();
        //return em.createQuery("select e from Event e ORDER BY e.eventID DESC").g;
    }

    public List<Event> findByDate(Date fromDate, Date toDate) {
        return em.createNamedQuery("Event.findByDate").setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
    }

    public void changeStatus() {
        em.createNativeQuery("update Event set Status = 'Oncoming' where StartDate <= GETDATE() and EndDate > GETDATE() and Status != 'Oncoming'").executeUpdate();
        em.createNativeQuery("update Event set Status = 'Ended' where EndDate < GETDATE() and Status != 'Ended'").executeUpdate();
    }
    public List<Event> showNewerEvents(Event evt){
        return em.createQuery("select e from Event e where e.eventTypeID.eventTypeName =:evtTypeName and e.endDate >:evtEndDate and e.eventID !=:evtID").
                setParameter("evtTypeName", evt.getEventTypeID().getEventTypeName()).setParameter("evtEndDate", evt.getEndDate()).
                setParameter("evtID", evt.getEventID()).setMaxResults(4).getResultList();
    }
    public List<Event> showOlderEvents(Event evt){
        return em.createQuery("select e from Event e where e.eventTypeID.eventTypeName =:evtTypeName and e.endDate <:evtEndDate and e.eventID !=:evtID").
                setParameter("evtTypeName", evt.getEventTypeID().getEventTypeName()).setParameter("evtEndDate", evt.getEndDate()).
                setParameter("evtID", evt.getEventID()).setMaxResults(4).getResultList();
    }
    
    public List<Event> showAllEvent(){
        return em.createQuery("SELECT e FROM Event e ORDER BY e.createDate DESC").getResultList();
    }
    public List<Event> showEventsByType(String type){
        return em.createQuery("SELECT e FROM Event e WHERE e.eventTypeID.eventTypeName=:type").
                setParameter("type", type).getResultList();
    }
}
