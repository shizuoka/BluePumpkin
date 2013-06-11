/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.EventType;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class EventTypeBean implements Serializable {

    @EJB
    private EventTypeFacade eventTypeFacade;
    private HttpServletRequest rq;

    /**
     * Creates a new instance of EventTypeBean
     */
    public EventTypeBean() {
    }
    private List<EventType> filteredType;

    public List<EventType> getFilteredType() {
        return filteredType;
    }

    public void setFilteredType(List<EventType> filteredType) {
        this.filteredType = filteredType;
    }
    private String eventTypeID;

    public String getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }
    private String eventTypeName;

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
    private String descriptionType;

    public String getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String addEventType() {
        EventType et = new EventType(eventTypeID, eventTypeName, descriptionType);
        boolean result = eventTypeFacade.addEventType(et);
        if (result) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("add", "Add EventType Successfull !!!");
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("add", "Add EventType Unsuccessfull !!!");
        }
        return "addEventType.xhtml";
    }

    public String deleteEventType(EventType et) {
        try {
            long countEvent = eventTypeFacade.countEventOfType(et.getEventTypeID());
            if (countEvent < 1) {
                boolean delete = eventTypeFacade.deleteEventType(et);
                if (delete) {
                    rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    rq.setAttribute("del", "Delete EventType Successfull !!!");
                } else {
                    rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    rq.setAttribute("del", "Delete EventType UnSuccessfull !!!");
                }
            } else {
                rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                rq.setAttribute("error", "Can't delete event type!!!");
            }
        } catch (Exception e) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("error", "Delete Fail!!!");
        }
        return "addEventType.xhtml";
    }

    public List<EventType> showAllEventType() {
        return eventTypeFacade.findAll();
    }

    public void reset() {
        eventTypeID = "";
        eventTypeName = "";
        descriptionType = "";
    }
}
