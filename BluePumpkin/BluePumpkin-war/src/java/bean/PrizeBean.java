/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import entities.Prizes;
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
    private List<Prizes> listPrizes;

    public List<Prizes> getListPrizes() {
        return listPrizes;
    }

    public void setListPrizes(List<Prizes> listPrizes) {
        this.listPrizes = listPrizes;
    }

    private List<Employee> listEmp;

    public List<Employee> getListEmp() {
        return listEmp;
    }

    public void setListEmp(List<Employee> listEmp) {
        this.listEmp = listEmp;
    }
    
        
    public String redirectWinner(String eventID) {
        setListEmp(registerEventFacade.findEmployeeByEventId(eventID));
        setListPrizes(prizesFacade.getPrize(eventID));
        return "winner.xhtml?faces-redirect=true";
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
    private String description;
    private String numberOfPrize;

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
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

    public List<Prizes> showAllPrize() {
        return prizesFacade.findAll();
    }

    public void insertPrize(String eventID) {
        boolean result = prizesFacade.addPrize(prizeName, description, Integer.parseInt(numberOfPrize), eventID);
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
