/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pesistence;

import bean.EventFacade;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author SONPV90
 */
@Stateless
@LocalBean
public class EventTimer {
    @EJB
    private EventFacade eventFacade;

    @Schedule(minute = "0", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "0")
    public void myTimer() {
        eventFacade.changeStatus();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
