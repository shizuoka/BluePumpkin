/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enties;

/**
 *
 * @author SONPV90
 */
public class Statistic {
    private String eventId;
    private String event;
    private int totalEmp;
    private int empRe;
    private int empNotRe;

    public Statistic() {
    }

    public Statistic(String eventId, String event, int totalEmp, int empRe, int empNotRe) {
        this.eventId = eventId;
        this.event = event;
        this.totalEmp = totalEmp;
        this.empRe = empRe;
        this.empNotRe = empNotRe;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public int getEmpNotRe() {
        return empNotRe;
    }

    public void setEmpNotRe(int empNotRe) {
        this.empNotRe = empNotRe;
    }    
    
}
