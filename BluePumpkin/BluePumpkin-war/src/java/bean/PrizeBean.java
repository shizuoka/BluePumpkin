/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Prizes;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class PrizeBean implements Serializable {

    @EJB
    private PrizesFacade prizesFacade;

    /**
     * Creates a new instance of PrizeBean
     */
    public PrizeBean() {
    }

    public List<Prizes> showAllPrize() {
        return prizesFacade.findAll();
    }
}
