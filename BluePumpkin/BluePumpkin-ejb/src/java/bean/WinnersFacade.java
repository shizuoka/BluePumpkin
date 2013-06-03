/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Winners;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SONPV90
 */
@Stateless
public class WinnersFacade extends AbstractFacade<Winners> {
    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WinnersFacade() {
        super(Winners.class);
    }
    
    public String getWinner(String eventID){
        return (String)em.createQuery("select w.employeesName FROM Winners w where w.isWin = 1 and w.prizeID.eventID.eventID = :eventID")
                .setParameter("eventID", eventID).getSingleResult();
    }
}
