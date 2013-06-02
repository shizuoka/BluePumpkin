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
    private String event;
    private int totalEmp;
    private int empRe;
    private int empNotRe;

    public Statistic() {
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
