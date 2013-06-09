/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Winners;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author SONPV90
 */
@ManagedBean
@SessionScoped
public class WinnerBean implements Serializable{
    @EJB
    private WinnersFacade winnersFacade;
    
    /**
     * Creates a new instance of WinnerBean
     */
    public WinnerBean() {
    }
    
    private List<Winners> listWinner;

    public List<Winners> getListWinner() {
        return listWinner;
    }

    public void setListWinner(List<Winners> listWinner) {
        this.listWinner = listWinner;
    }
    
    public List<Winners> showWinnersByPrize(String prize){
            listWinner = new ArrayList<Winners>();
            listWinner = winnersFacade.showWinnersByPrize(prize);
            return listWinner;
    }
}
