/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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
public class LoginBean implements Serializable {

    @EJB
    private AccountFacade accountFacade;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    private boolean isLoggedAdmin;
    private boolean isLoggedEmployee;

    public boolean isIsLoggedAdmin() {
        return isLoggedAdmin;
    }

    public void setIsLoggedAdmin(boolean isLoggedAdmin) {
        this.isLoggedAdmin = isLoggedAdmin;
    }

    public boolean isIsLoggedEmployee() {
        return isLoggedEmployee;
    }

    public void setIsLoggedEmployee(boolean isLoggedEmployee) {
        this.isLoggedEmployee = isLoggedEmployee;
    }
    private Account acc;

    public Account getAcc() {
        if (acc == null) {
            acc = new Account();
        }
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public String loginEmployee() {
        try {
            acc = accountFacade.login(username, control.generateMD5(password));
            if (acc != null) {
                if (acc.getRoleID().getRoleName().equalsIgnoreCase("employee")) {
                    sessionTool.setUpSession("employee", acc);
                    isLoggedEmployee = true;
                } else {
                    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    rq.setAttribute("error", "Access denied !!! You don't have permission");
                }
            }
        } catch (EJBException e) {
            HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("error", "Username or Password isn't correct....");
        }
        return "index.xhtml";
    }

    public String loginAdmin() {
        try {
            Account acc = accountFacade.login(username, control.generateMD5(password));
            if (acc != null) {
                if (acc.getRoleID().getRoleName().equalsIgnoreCase("admin")) {
                    sessionTool.setUpSession("admin", acc);
                    isLoggedAdmin = true;
                    return "index.xhtml?faces-redirect=true";
                } else {
                    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    rq.setAttribute("error", "Access denied !!! You don't have permission");
                }
            }
        } catch (Exception e) {
            HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("error", "Username or Password isn't correct....");
        }
        return "login.xhtml";
    }

    public String logOutEmployee() {
        sessionTool.removeSession("employee");
        isLoggedEmployee = false;
        return "index.xhtml?faces-redirect=true";
    }

    public String logOutAdmin() {
        sessionTool.removeSession("admin");
        isLoggedAdmin = false;
        return "login.xhtml?faces-redirect=true";
    }
}
