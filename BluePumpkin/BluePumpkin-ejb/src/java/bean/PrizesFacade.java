/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Event;
import entities.Prizes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungThanh
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

    public boolean insertPrizes(String eventID, String prizeName) {
        Event e = em.find(Event.class, eventID);
        Prizes p = new Prizes();
        p.setPrizeName(prizeName);
        p.setEventID(e);
        e.getPrizesList().add(p);
        em.persist(p);
        return true;
    }
}
