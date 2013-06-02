/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
import entities.Prizes;
import entities.PrizesDetail;
import entities.RegisterEvent;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import util.sessionTool;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class PrizeBean implements Serializable {

    @EJB
    private RegisterEventFacade registerEventFacade;
    @EJB
    private PrizesDetailFacade prizesDetailFacade;
    @EJB
    private PrizesFacade prizesFacade;
    private HttpServletRequest rq;

    /**
     * Creates a new instance of PrizeBean
     */
    public PrizeBean() {
    }
    private String inputWinner;

    public String getInputWinner() {
        return inputWinner;
    }

    public void setInputWinner(String inputWinner) {
        this.inputWinner = inputWinner;
    }
    private int prize_id;

    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }    

    public List<Prizes> showAllPrize() {
        return prizesFacade.findAll();
    }

    public List<PrizesDetail> showWinnerDetail() {
        return prizesDetailFacade.findAll();
    }

    public List<RegisterEvent> showAllRegister() {
        return registerEventFacade.findAll();
    }

    public String inputWinner(int prizeID) {
        boolean result = prizesDetailFacade.inputWinner(prizeID, inputWinner);
        if (result) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("info", "Pick the Winner Successful !!!");
            return "prizes.xhtml";
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("info", "Error,please try again!!!");
            return "prizes.xhtml";
        }
    }

    public String deleteWinnerDetail(int detailID) {
        if (prizesDetailFacade.deleteWinnerDetail(detailID)) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("del", "Delete Information Successfull!!!");
            return "prizes.xhtml";
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("del", "Delete Unsuccessfull!!!");
            return "prizes.xhtml";
        }
    }
    private Prizes pz;

    public Prizes getPz() {
        return pz;
    }

    public void setPz(Prizes pz) {
        this.pz = pz;
    }
    
    public void pickWinner(Prizes p){
        pz=p;
    }
}
