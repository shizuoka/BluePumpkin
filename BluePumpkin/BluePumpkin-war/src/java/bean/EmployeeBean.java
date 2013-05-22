/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Employee;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
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
public class EmployeeBean implements Serializable {

    @EJB
    private EmployeeFacade employeeFacade;

    /**
     * Creates a new instance of EmployeeBean
     */
    public EmployeeBean() {
    }
    private String notification;

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String notificationDOB() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        Date today = new Date();
        int date = today.getDate();
        int month = (today.getMonth()) + 1;
        List<Employee> lstEmployee = employeeFacade.findAll();
        for (Employee employee : lstEmployee) {
            String empDOB = dateFormat.format(employee.getDateOfBirth());
            String[] detailDate = empDOB.split("/");
            if (Integer.parseInt(detailDate[0]) == month && Integer.parseInt(detailDate[1]) == date) {
                notification = "Happy Birthday To:" + employee.getFullName();
                return "index.xhtml";
            }
        }
        return "index.xhtml";
    }
}
