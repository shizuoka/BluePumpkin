/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Account;
import entities.Comments;
import entities.Employee;
import entities.Roles;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class EmployeeBean implements Serializable {

    @EJB
    private EventFacade eventFacade;
    @EJB
    private CommentsFacade commentsFacade;
    @EJB
    private RolesFacade rolesFacade;
    @EJB
    private AccountFacade accountFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    private HttpServletRequest rq;

    /**
     * Creates a new instance of EmployeeBean
     */
    public EmployeeBean() {
        comment = new Comments();
    }
    private List<Account> filteredAccounts;

    public List<Account> getFilteredAccounts() {
        return filteredAccounts;
    }

    public void setFilteredAccounts(List<Account> filteredAccounts) {
        this.filteredAccounts = filteredAccounts;
    }
    private String notification;

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public void createComment(String eventId) {
        Employee employeeId = ((Account) sessionTool.getDownSession("employee")).getUserName();
        comment.setCreateDate(new Date());
        comment.setEmployeeID(employeeId);
        comment.setEventID(eventFacade.findByEventID(eventId));
        commentsFacade.create(comment);
    }

    public List<Employee> findEmployeeByBirthDate() {
        return employeeFacade.findEmployeeByBirthDate();
    }
    private String employeeID;
    private String fullName;
    private Boolean gender;
    private String address;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private Comments comment;

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return comment.getTitle();
    }

    public void setTitle(String title) {
        comment.setTitle(title);
    }

    public String getContent() {
        return comment.getContent();
    }

    public void setContent(String content) {
        comment.setContent(content);
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    private String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    private int chooseRole;

    public int getChooseRole() {
        return chooseRole;
    }

    public void setChooseRole(int chooseRole) {
        this.chooseRole = chooseRole;
    }
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String createEmpAccount() {
        if (employeeID.equals("") || fullName.equals("") || address.equals("") || email.equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Please input field");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
//        else if (control.validEmail(email)) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Email invalid");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        } 
        else if (dateOfBirth.getTime() > new Date().getTime()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "DateOfBirth must less than current time");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            Employee e = new Employee(new Date(), employeeID, fullName, gender, address, email, phone, dateOfBirth);
            boolean result_1 = employeeFacade.createEmployee(e);
            if (result_1) {
                String password = "123456";
                try {
                    boolean result_2 = accountFacade.createAccount(control.generateMD5(password), chooseRole, e.getEmployeeID());
                    if (result_2) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Create New Employee Successfull !!!");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Create Fail !!!");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                } catch (Exception ex) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Create Fail !!!");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }

            }
        }
        return "";
    }

    public List<Account> showAllAccount() {
        return accountFacade.findAll();
    }

    public List<Roles> showAllRole() {
        return rolesFacade.findAll();
    }

    public List<Employee> showAllEmployee() {
        return employeeFacade.findAll();
    }

    public void deleteEmployee(Employee emp) {
        boolean del = employeeFacade.deleteEmployee(emp);
        if (del) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Delete EmpID " + emp.getEmployeeID() + " Successful !!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Delete EmpID " + emp.getEmployeeID() + " UnSuccessful !!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void reset() {
        employeeID = "";
        fullName = "";
        address = "";
        email = "";
        dateOfBirth = null;
        phone = "";
    }
}
