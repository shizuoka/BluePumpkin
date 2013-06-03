/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Faq;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SONPV90
 */
@Stateless
public class FaqFacade extends AbstractFacade<Faq> {

    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaqFacade() {
        super(Faq.class);
    }

    public boolean addFaq(Faq faq) {
        boolean flag = false;
        try {
            em.persist(faq);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean editFaq(int fid, String question, String answer) {
        boolean flag = false;
        Faq faq = em.find(Faq.class, fid);
        faq.setQuestion(question);
        faq.setAnswer(answer);
        try {
            em.merge(faq);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
    public List<Faq> getListFaqs(){
        return em.createNamedQuery("Faq.findAll").getResultList();
    }
}
