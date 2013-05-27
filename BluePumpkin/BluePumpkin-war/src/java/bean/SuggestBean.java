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
public class SuggestBean implements Serializable {

    @EJB
    private RegisterEventFacade registerEventFacade;

    /**
     * Creates a new instance of SuggestBean
     */
    public SuggestBean() {
    }
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RegisterEvent> showNotAcceptRegister() {
        List<RegisterEvent> regist = registerEventFacade.findByIsAccept();
//        for (RegisterEvent registerEvent : regist) {
//            if (registerEvent.getIsAccept() == false) {
//                state = "Not Yet Accept";
//            } else {
//                state = "Accepted";
//            }
//        }
        return regist;
    }

    public String acceptRegist(int registID) {
        boolean accept = registerEventFacade.acceptRegist(registID);
        if (accept) {
            msg = "Register ID " + registID + " accepted";
            return "suggest.xhtml?result=" + msg + "&faces-redirect=true";
        } else {
            msg = "Register ID " + registID + " not yet accepted";
            return "suggest.xhtml?result=" + msg + "&faces-redirect=true";
        }
    }
}
