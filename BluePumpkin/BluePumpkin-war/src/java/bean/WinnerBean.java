/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import entities.Winners;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

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
    
    private List<Winners> listWinner = new ArrayList<Winners>();

    public List<Winners> getListWinner() {
        return listWinner;
    }

    public void setListWinner(List<Winners> listWinner) {
        this.listWinner = listWinner;
    }
    
    public List<Winners> showWinnersByPrize(int prize){
            listWinner = winnersFacade.showWinnersByPrize(prize);
            return listWinner;
    }
    
    private List<String> seletedEmID;

    public List<String> getSeletedEmID() {
        return seletedEmID;
    }

    public void setSeletedEmID(List<String> seletedEmID) {
        this.seletedEmID = seletedEmID;
    }
    
    public List<Winners> showWinnerDetail() {
        return winnersFacade.findAll();
    }
    
    private List<Winners> filteredWinners;

    public List<Winners> getFilteredWinners() {
        return filteredWinners;
    }

    public void setFilteredWinners(List<Winners> filteredWinners) {
        this.filteredWinners = filteredWinners;
    }
        
    
}
