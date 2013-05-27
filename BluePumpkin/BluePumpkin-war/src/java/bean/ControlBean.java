/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class ControlBean implements Serializable{

    /**
     * Creates a new instance of ControlBean
     */
    public ControlBean() {
    }
    
    private Date FromDate;

    public Date getFromDate() {
        return FromDate;
    }

    public void setFromDate(Date FromDate) {
        this.FromDate = FromDate;
    }
    private Date ToDate;

    public Date getToDate() {
        return ToDate;
    }

    public void setToDate(Date ToDate) {
        this.ToDate = ToDate;
    }
    
}
