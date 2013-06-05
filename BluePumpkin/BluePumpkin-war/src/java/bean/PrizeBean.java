/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

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

    public List<RegisterEvent> showAllRegister() {
        return registerEventFacade.findAll();
    }
    private Prizes pz;

    public Prizes getPz() {
        return pz;
    }

    public void setPz(Prizes pz) {
        this.pz = pz;
    }
    private String prizeName;
    private int weight;
    private String description;
    private String numberOfPrize;

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberOfPrize() {
        return numberOfPrize;
    }

    public void setNumberOfPrize(String numberOfPrize) {
        this.numberOfPrize = numberOfPrize;
    }

    public void insertPrize(String eventID) {
        boolean result = prizesFacade.addPrize(prizeName, weight, description, Integer.parseInt(numberOfPrize), eventID);
        if (result) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Add New Successfull");
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Add New UnSuccessfull");
        }
    }

    public void reset() {
        prizeName = "";
        description = "";
        numberOfPrize="";
    }
}
