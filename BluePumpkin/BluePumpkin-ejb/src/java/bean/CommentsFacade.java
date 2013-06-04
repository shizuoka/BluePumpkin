/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Comments;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PHATVQ
 */
@Stateless
public class CommentsFacade extends AbstractFacade<Comments> {

    @PersistenceContext(unitName = "BluePumpkin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentsFacade() {
        super(Comments.class);
    }

    public List<Comments> findCommentByEventId(String eventId) {
        return em.createQuery("SELECT c FROM Comments c WHERE c.eventID.eventID = '" + eventId + "'").getResultList();
    }
}
