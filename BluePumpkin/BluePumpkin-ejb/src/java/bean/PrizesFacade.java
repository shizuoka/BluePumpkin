/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Event;
import entities.Prizes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SONPV90
 */
@Stateless
public class PrizesFacade extends AbstractFacade<Prizes> {

    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrizesFacade() {
        super(Prizes.class);
    }

    public boolean addPrize(String prizeName, String description, int numberOfPrize, String eventID,boolean isWin) {
        boolean flag = false;
        try {
            Event ev = em.find(Event.class, eventID);
            Prizes p = new Prizes(isWin, description, numberOfPrize, prizeName, ev);
            em.persist(p);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<Prizes> getPrize(String eventID) {
        return em.createQuery("select p from Prizes p where p.eventID.eventID = :eventID")
                .setParameter("eventID", eventID).getResultList();
    }
    
    public boolean isFirstPrizeExisted(String eventId) {
        return (Long)em.createQuery("SELECT COUNT(p) FROM Prizes p WHERE p.eventID.eventID = '" + eventId + "' AND p.isWin = 1").getSingleResult() == 1;
    }
    
    
}
