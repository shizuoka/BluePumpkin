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
    private boolean isLogged;

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String login() {
        Account acc = accountFacade.login(username, control.generateMD5(password));
        if (acc == null) {
            msg = "Username or Password isn't correct...";
        } else {
            if (acc.getRoleID().getRoleName().equalsIgnoreCase("employee")) {
                sessionTool.setUpSession("employee", acc.getUserName().getFullName());
                isLogged = true;
                return "index.xhtml?faces-redirect=true";
            } else if (acc.getRoleID().getRoleName().equalsIgnoreCase("admin")) {
                sessionTool.setUpSession("admin", acc.getUserName().getFullName());
                isLogged = true;
                return "index.xhtml?faces-redirect=true";
            } else {
                msg = "Access denied !!! You don't have permission";                  
            }
        }
        return "";
    }

    public String logOutEmployee() {
        sessionTool.removeSession("employee");
        isLogged = false;
        return "index.xhtml?faces-redirect=true";
    }

    public String logOutAdmin() {
        sessionTool.removeSession("admin");
        isLogged = false;
        return "login.xhtml?faces-redirect=true";
    }
}
