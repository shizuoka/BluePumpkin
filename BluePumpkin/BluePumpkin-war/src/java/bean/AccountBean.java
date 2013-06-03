/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import util.control;
import util.sessionTool;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class AccountBean implements Serializable {

    @EJB
    private AccountFacade accountFacade;
    private HttpServletRequest rq;

    /**
     * Creates a new instance of AccountBean
     */
    public AccountBean() {
    }
    private String oldPassword;
    private String newPassword;
    private String retypePassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public void deleteAccountEmp(Account acc) {
        boolean del = accountFacade.deleteAccountEmp(acc);
        if (del) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Delete Account Of EmpID " + acc.getUserName().getEmployeeID() + " Successful !!!");
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("msg", "Delete Account Of EmpID " + acc.getUserName().getEmployeeID() + " UnSuccessful !!!");
        }
    }

    public void changePassword() {
        sessionTool.getDownSession("employee");
    }
}
