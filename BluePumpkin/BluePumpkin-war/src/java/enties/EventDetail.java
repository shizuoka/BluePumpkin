/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enties;

import java.util.Date;

/**
 *
 * @author SONPV90
 */
public class EventDetail {
    private String eventId;
    private String eventTitle;
    private Date startDate;
    private Date endDate;
    private String typeEvent;
    private int totalEmp;
    private int empRe;
    private String empWin;

    public String getEmpWin() {
        return empWin;
    }

    public void setEmpWin(String empWin) {
        this.empWin = empWin;
    }
    

    public EventDetail(String eventId, String eventTitle, Date startDate, Date endDate, String typeEvent, int totalEmp, int empRe) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeEvent = typeEvent;
        this.totalEmp = totalEmp;
        this.empRe = empRe;
    }

    public EventDetail(String eventId, String eventTitle, Date startDate, Date endDate, String typeEvent, int totalEmp, int empRe, String empWin) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeEvent = typeEvent;
        this.totalEmp = totalEmp;
        this.empRe = empRe;
        this.empWin = empWin;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getTotalEmp() {
        return totalEmp;
    }

    public void setTotalEmp(int totalEmp) {
        this.totalEmp = totalEmp;
    }

    public int getEmpRe() {
        return empRe;
    }

    public void setEmpRe(int empRe) {
        this.empRe = empRe;
    }
    
    
}
