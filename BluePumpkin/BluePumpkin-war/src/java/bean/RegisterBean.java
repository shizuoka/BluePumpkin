/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.RegisterEvent;
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
public class RegisterBean implements Serializable {

    @EJB
    private RegisterEventFacade registerEventFacade;

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
        boolean register = registerEventFacade.registerEvent(eventID, "E02");
        if (register) {
            return "viewRegisterEvent.xhtml?faces-redirect=true";
        } else {
            message = "Register Fail";
            return "detailEvent.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public List<RegisterEvent> showAllRegister() {
        return registerEventFacade.findAll();
    }

    public List<RegisterEvent> getRegisterEventByEmployee() {
        return registerEventFacade.getRegisterEventByEmployee("E02");
    }
}
