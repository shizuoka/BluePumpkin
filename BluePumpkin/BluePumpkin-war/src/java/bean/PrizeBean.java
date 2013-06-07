/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Prizes;
import entities.PrizesDetail;
import entities.RegisterEvent;
import entities.Winners;
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
    private WinnersFacade winnersFacade;
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
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    private String employeeID;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
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

    public List<Winners> showWinnerDetail() {
        return winnersFacade.findAll();
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

    public void pickWinner(Prizes p) {
        setPz(p);
    }

    public void reset() {
        prizeName = "";
        description = "";
        numberOfPrize = "";
    }
    private Boolean select1st;

    public Boolean getSelect1st() {
        return select1st;
    }

    public void setSelect1st(Boolean select1st) {
        this.select1st = select1st;
    }

    public void addWinner(int prizeID) {
        boolean addWinner = winnersFacade.addWinner(employeeName, employeeID, select1st, prizeID);
        if (addWinner) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("info", "Pick the winner Successfull");
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("info", "Add New UnSuccessfull");
        }
    }

    public void deleteWinnerDetail(int id) {
        if (winnersFacade.deleteWinnerDetail(id)) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("del", "Delete detail Successful");
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("del", "Delete detail UnSuccessful");
        }
    }
}
