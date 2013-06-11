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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class SuggestBean implements Serializable {

    @EJB
    private RegisterEventFacade registerEventFacade;
    private HttpServletRequest rq;

    /**
     * Creates a new instance of SuggestBean
     */
    public SuggestBean() {
    }
    private List<RegisterEvent> filteredRegister;

    public List<RegisterEvent> getFilteredRegister() {
        return filteredRegister;
    }

    public void setFilteredRegister(List<RegisterEvent> filteredRegister) {
        this.filteredRegister = filteredRegister;
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
        return regist;
    }

    public String acceptRegist(int registID) {
        boolean accept = registerEventFacade.acceptRegist(registID);
        if (accept) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Register ID " + registID + " accepted");
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Register ID " + registID + " not accepted");
        }
        return "suggest.xhtml";
    }
}
