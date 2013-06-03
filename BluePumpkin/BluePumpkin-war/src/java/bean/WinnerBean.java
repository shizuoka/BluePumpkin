/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author SONPV90
 */
@ManagedBean
@SessionScoped
public class WinnerBean implements Serializable{
    @EJB
    private WinnersFacade winnersFacade;
    
    /**
     * Creates a new instance of WinnerBean
     */
    public WinnerBean() {
    }
}
