/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import entities.Prizes;
import entities.PrizesDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TrungThanh
 */
@Stateless
public class PrizesDetailFacade extends AbstractFacade<PrizesDetail> {
    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrizesDetailFacade() {
        super(PrizesDetail.class);
    }
    
    public boolean inputWinner(int prizeID, String employeeID) {
        boolean flag = false;
        try {
            Prizes p = em.find(Prizes.class, prizeID);
            Employee emp = em.find(Employee.class, employeeID);
            PrizesDetail pd = new PrizesDetail(p, emp);
            em.persist(pd);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean deleteWinnerDetail(int detailID) {
        boolean flag = false;
        try {
            PrizesDetail pd = em.find(PrizesDetail.class, detailID);
            em.remove(em.merge(pd));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
