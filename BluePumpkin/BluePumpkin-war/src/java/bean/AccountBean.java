/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Delete Account Of EmpID " + acc.getUserName().getEmployeeID() + " Successful !!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Delete Account Of EmpID " + acc.getUserName().getEmployeeID() + " UnSuccessful !!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void changePassword() {
        sessionTool.getDownSession("employee");
    }
}
