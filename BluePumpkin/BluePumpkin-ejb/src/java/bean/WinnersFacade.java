/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import entities.Prizes;
import entities.Winners;
import java.util.List;
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

    public String getWinner(String eventID) {
        return (String) em.createQuery("select w.employeesName FROM Winners w where w.isWin = 1 and w.prizeID.eventID.eventID = :eventID")
                .setParameter("eventID", eventID).getSingleResult();
    }

    public boolean addWinner(String empName, String empID, Boolean isWin, int prizeID) {
        boolean flag = false;
        try {
            Prizes p = em.find(Prizes.class, prizeID);
            Winners winner = new Winners(empName, empID, isWin, p);
            em.persist(winner);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean deleteWinnerDetail(int id) {
        boolean flag = false;
        try {
            Winners w = em.find(Winners.class, id);
            em.remove(em.merge(w));
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
    
    public List<Winners> showWinnersByPrize(int prizeID) {
        return em.createQuery("select w from Winners w where w.prizeID.prizeID = :prizeID")
                .setParameter("prizeID", prizeID).getResultList();
    }
    
}
