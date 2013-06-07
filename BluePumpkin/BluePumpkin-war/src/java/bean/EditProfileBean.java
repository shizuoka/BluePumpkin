/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import util.control;

/**
 *
 * @author shizuoka
 */
@ManagedBean
@RequestScoped
public class EditProfileBean implements Serializable {

    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private AccountFacade accountFacade;
    private static final String EMAIL_PATTERN = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
    private Pattern pattern;
    private Matcher matcher;
    
    private Account acc;
    private Account accAdmin;
    private String pass = "";
    private String newpass1;
    private String newpass2;
    private int error;
    private int check;
    private Boolean gender;

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNewpass1() {
        return newpass1;
    }

    public void setNewpass1(String newpass1) {
        this.newpass1 = newpass1;
    }

    public String getNewpass2() {
        return newpass2;
    }

    public void setNewpass2(String newpass2) {
        this.newpass2 = newpass2;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Account getAccAdmin() {
        return accAdmin;
    }

    public void setAccAdmin(Account accAdmin) {
        this.accAdmin = accAdmin;
    }

    /**
     * Creates a new instance of EditProfileBean
     */
    public EditProfileBean() {
        acc = (Account) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("employee");
        accAdmin = (Account) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin");
        pattern = Pattern.compile(EMAIL_PATTERN);
        pass = "";
        error = 0;
        check = 0;
    }

    public void editInfoEmployee() {
        try {
            if (acc.getUserName().getFullName().length() == 0) {
                error = 1;
            } else {
                if (pass.length() != 0 && !control.generateMD5(pass).equalsIgnoreCase(acc.getPassWord())) {
                    error = 2;
                } else {
                    if (newpass2.length() < 6) {
                        error = 4;

                    } else {
                        if (!newpass1.equalsIgnoreCase(newpass2)) {
                            error = 3;
                        } else {
                            if (acc.getUserName().getDateOfBirth().after(new Date())) {
                                error = 5;
                                System.out.print(new Date());
                            } else {
                                error = 0;
                            }
                        }

                    }
                }

                if (pass.length() == 0) {
                    if (acc.getUserName().getDateOfBirth().after(new Date())) {
                        error = 5;
                        System.out.print(new Date());
                    } else {
                        error = 0;
                    }
                }
            }

            if (error == 0) {
                if (pass.length() != 0) {
                    acc.setPassWord(control.generateMD5(newpass2));
                }
                acc.getUserName().setGender(true);
                accountFacade.edit(acc);
                employeeFacade.edit(acc.getUserName());
                check = 1;
                System.out.println(acc.getUserName().getGender());
            }
        } catch (Exception e) {
            check = 2;
        }
    }

    public void editInfoAdmin() {
        try {
            if (accAdmin.getUserName().getFullName().length() == 0) {
                error = 1;
            } else {
                if (pass.length() != 0 && !control.generateMD5(pass).equalsIgnoreCase(accAdmin.getPassWord())) {
                    error = 2;
                } else {
                    if (newpass2.length() < 6) {
                        error = 4;

                    } else {
                        if (!newpass1.equalsIgnoreCase(newpass2)) {
                            error = 3;
                        } else {
                            if (accAdmin.getUserName().getDateOfBirth().after(new Date())) {
                                error = 5;
                                System.out.print(new Date());
                            } else {
                                error = 0;
                            }
                        }

                    }
                }

                if (pass.length() == 0) {
                    if (accAdmin.getUserName().getDateOfBirth().after(new Date())) {
                        error = 5;
                        System.out.print(new Date());
                    } else {
                        error = 0;
                    }
                }
            }

            if (error == 0) {
                if (pass.length() != 0) {
                    accAdmin.setPassWord(control.generateMD5(newpass2));
                }
                accAdmin.getUserName().setGender(true);
                accountFacade.edit(accAdmin);
                employeeFacade.edit(accAdmin.getUserName());
                check = 1;
                System.out.println(accAdmin.getUserName().getGender());
            }
        } catch (Exception e) {
            check = 2;
        }
    }
}
