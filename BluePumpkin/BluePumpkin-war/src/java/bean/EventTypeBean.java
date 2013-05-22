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
            message = "Add EventType Successfull !!!";
            return "addEventType.xhtml?result=" + message + "&faces-redirect=true";
        } else {
            message = "Unsuccessfull !!!";
            return "addEventType.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public String deleteEventType(EventType et) {
        boolean delete = eventTypeFacade.deleteEventType(et);
        if (delete) {
            message = "Delete EventType Successfull";
            return "addEventType.xhtml?result=" + message + "&faces-redirect=true";
        } else {
            message = "Delete UnSuccessfull";
            return "addEventType.xhtml?result=" + message + "&faces-redirect=true";
        }
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
