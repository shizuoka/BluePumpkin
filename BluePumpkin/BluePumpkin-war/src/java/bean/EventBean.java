/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Event;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

    @EJB
    private PrizesFacade prizesFacade;
    @EJB
    private EventFacade eventFacade;

    /**
     * Creates a new instance of EventBean
     */
    public EventBean() {
    }
    private String eventID;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
    private String eventTitle;

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String eventTypeID;

    public String getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private String choiceEventType;

    public String getChoiceEventType() {
        return choiceEventType;
    }

    public void setChoiceEventType(String choiceEventType) {
        this.choiceEventType = choiceEventType;
    }
    private String prizeName;

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public List<Event> showAll() {
        return eventFacade.findAll();
    }
    //Primitives
    private static final int BUFFER_SIZE = 6124;

    public void handleFileUpload(FileUploadEvent event) {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();

        File result = new File(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));
        System.out.println(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            FacesMessage msg = new FacesMessage("File Description", "file name: " + event.getFile().getFileName() + " File size: " + event.getFile().getSize() / 1024 + " Kb Content type: " + event.getFile().getContentType() + " the file was uploaded.");
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, " the files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }

    public String addNewEvent() {
        boolean add = eventFacade.addNewEvent(eventID, eventTitle, description, startDate, endDate, status, this.choiceEventType);
        if (add) {
            String eID = eventFacade.getMaxEventID().getEventID();
            boolean prize = prizesFacade.insertPrizes(eID, prizeName);
            if (prize) {
                message = "Add New Event Successfull";
                return "event.xhtml?result=" + message + "&faces-redirect=true";
            }
            return "";
        } else {
            message = "Unsuccessfull !!!";
            return "event.xhtml?result=" + message + "&faces-redirect=true";
        }
    }
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String editEvent(Event e) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//        session.setAttribute("editEvent", e);
        event = e;
        return "editEvent.xhtml?eventID=" + e.getEventID() + "&faces-redirect=true";
    }

    public String updateEvent() {
        boolean update = eventFacade.updateEvent(event.getEventID(), event.getEventTitle(), event.getDescription(), event.getStartDate(), event.getEndDate(), event.getStatus(), this.choiceEventType);
        if (update) {
            message = "Update " + event.getEventID() + " Successfull";
            return "editEvent.xhtml?result=" + message + "&faces-redirect=true";
        } else {
            message = "Update " + event.getEventID() + " Fail";
            return "editEvent.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public String deleteEvent(Event e) {
        boolean del = eventFacade.deleteEvent(e);
        if (del) {
            message = "Delete Event Successfull";
            return "event.xhtml?result=" + message + "&faces-redirect=true";
        }else{
            message = "Delete Event Unsuccessful";
            return "event.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public void reset() {
        eventID = "";
        eventTitle = "";
        description = "";
        startDate = null;
        endDate = null;
        status = "";
    }

    public String cancel() {
        return "event.xhtml?faces-redirect=true";
    }
}
