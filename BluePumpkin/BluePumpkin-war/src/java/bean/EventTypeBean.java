/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.EventType;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
        if (eventTypeID.equals("") || eventTypeName.equals("") || descriptionType.equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Please input field");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            EventType et = new EventType(eventTypeID, eventTypeName, descriptionType);
            boolean result = eventTypeFacade.addEventType(et);
            if (result) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Add EventType Successfull !!!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAIL", "Add EventType Unsuccessfull !!!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
        return "";
    }

    public String deleteEventType(EventType et) {
        try {
            long countEvent = eventTypeFacade.countEventOfType(et.getEventTypeID());
            if (countEvent < 1) {
                boolean delete = eventTypeFacade.deleteEventType(et);
                if (delete) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Delete EventType Successfull !!!");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Delete EventType UnSuccessfull !!!");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Can't delete event type!!!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Delete Fail!!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "";
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
