/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
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
public class RegisterBean implements Serializable {

    @EJB
    private RegisterEventFacade registerEventFacade;
    private HttpServletRequest rq;

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String registerEvent(String eventID) {
        Account account = (Account) sessionTool.getDownSession("employee");
        if (account == null) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Please to login for Register Event !!!");
            return "detailEvent.xhtml";
        }
        boolean register = registerEventFacade.registerEvent(eventID, account.getUserName().getEmployeeID());
        if (register) {
            return "viewRegisterEvent.xhtml?faces-redirect=true";
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Register Fail !!!");
            return "detailEvent.xhtml";
//            message = "Register Fail";
//            return "detailEvent.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public List<RegisterEvent> showAllRegister() {
        return registerEventFacade.findAll();
    }

    public List<RegisterEvent> getRegisterEventByEmployee() {
        Account account = (Account) sessionTool.getDownSession("employee");
        return registerEventFacade.getRegisterEventByEmployee(account.getUserName().getEmployeeID());
    }
}
