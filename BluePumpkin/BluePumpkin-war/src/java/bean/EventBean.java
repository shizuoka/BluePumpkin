/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import enties.EventDetail;
import enties.Statistic;
import entities.Comments;
import entities.Event;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

    @EJB
    private CommentsFacade commentsFacade;
    @EJB
    private AccountFacade accountFacade;
    @EJB
    private RegisterEventFacade registerEventFacade;
    @EJB
    private PrizesFacade prizesFacade;
    @EJB
    private EventFacade eventFacade;
    @EJB
    private WinnersFacade winnersFacade;
    private HttpServletRequest rq;

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

    public Event findByEventID(String id) {
        return eventFacade.findByEventID(id);
    }
    //Primitives
    private static final int BUFFER_SIZE = 6124;

    public void handleFileUpload(FileUploadEvent event) {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();

        File result = new File(extContext.getRealPath("//images//event//" + event.getFile().getFileName()));
        System.out.println(extContext.getRealPath("//images//event//" + event.getFile().getFileName()));
        setImageName(event.getFile().getFileName());
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
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }    

    public void addNewEvent() {
        int noAccount = numberAccount();
        boolean add = eventFacade.addNewEvent(eventID, eventTitle, description, startDate, endDate, "Incoming", this.choiceEventType, noAccount, new Date(),imageName);
        if (add) {
            String eID = eventFacade.getMaxEventID().getEventID();
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("add", "Add New Event Successfull !!!");            
//                message = "Add New Event Successfull";
//                return "event.xhtml?result=" + message + "&faces-redirect=true";     

        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("add", "Add New Event Unsuccessfull !!!");            
//            message = "Unsuccessfull !!!";
//            return "event.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public int numberAccount() {
        return accountFacade.findAll().size();
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
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("update", "Update " + event.getEventID() + " Successfull");
            return "editEvent.xhtml";
//            message = "Update " + event.getEventID() + " Successfull";
//            return "editEvent.xhtml?result=" + message + "&faces-redirect=true";
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("update", "Update " + event.getEventID() + " Fail !!!");
            return "editEvent.xhtml";
//            message = "Update " + event.getEventID() + " Fail";
//            return "editEvent.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public String deleteEvent(Event e) {
        boolean del = eventFacade.deleteEvent(e);
        if (del) {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("del", "Delete Event Successfull !!!");
            return "event.xhtml";
//            message = "Delete Event Successfull";
//            return "event.xhtml?result=" + message + "&faces-redirect=true";
        } else {
            rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            rq.setAttribute("del", "Delete Event Unsuccessful !!!");
            return "event.xhtml";
//            message = "Delete Event Unsuccessful";
//            return "event.xhtml?result=" + message + "&faces-redirect=true";
        }
    }

    public void reset() {
        eventID = "";
        eventTitle = "";
        description = "";
        startDate = null;
        endDate = null;
        status = "";
        prizeName = "";
    }

    public String cancel() {
        return "event.xhtml?faces-redirect=true";
    }

    public String detailEvent(String eventID) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//        session.setAttribute("detailEvent", e);        
        event = eventFacade.findByEventID(eventID);
        return "detailEvent.xhtml?EventID=" + eventID + "&faces-redirect=true";
    }
    private String evID;

    public String getEvID() {
        return evID;
    }

    public void setEvID(String evID) {
        this.evID = evID;
    }

    public String redirectInsertPrize(String eventID) {
        setEvID(eventID);
        return "addPrize.xhtml?eventID=" + eventID + "&faces-redirect=true";
    }
    
    public List<Event> findEventEnded(){
        return eventFacade.findByStatus("Ended");
    }
//    
//    public int totalEventByStatus(String status){
//        return eventFacade.findByStatus(status).size();
//    }
    private int total;
    private int incoming;
    private int oncoming;
    private int ended;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIncoming() {
        return incoming;
    }

    public void setIncoming(int incoming) {
        this.incoming = incoming;
    }

    public int getOncoming() {
        return oncoming;
    }

    public void setOncoming(int oncoming) {
        this.oncoming = oncoming;
    }

    public int getEnded() {
        return ended;
    }

    public void setEnded(int ended) {
        this.ended = ended;
    }

    public void statistic() {

        List<Event> listEvent = eventFacade.findAll();
        int totalEvent = listEvent.size();
        int incoming = 0, oncoming = 0, ended = 0;
        for (int i = 0; i < totalEvent; i++) {
            if (listEvent.get(i).getStatus().equals("Incoming")) {
                incoming++;
            } else if (listEvent.get(i).getStatus().equals("Ended")) {
                ended++;
            } else {
                oncoming++;
            }
        }

        setTotal(totalEvent);
        setIncoming(incoming);
        setOncoming(oncoming);
        setEnded(ended);

    }
    private Date fromDate;
    private Date todate;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public String redirectStatistic() {
        return "customer-statistic.xhtml?fromDate=" + fromDate.toString() + "&toDate=" + todate.toString() + "&faces-redirect=true";
    }
    private EventDetail eventDetail;
    private List<EventDetail> listEventIncoming;
    private List<EventDetail> listEventOncoming;
    private List<EventDetail> listEventEnded;

    public EventDetail getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(EventDetail eventDetail) {
        this.eventDetail = eventDetail;
    }

    public List<EventDetail> getListEventIncoming() {
        return listEventIncoming;
    }

    public void setListEventIncoming(List<EventDetail> listEventIncoming) {
        this.listEventIncoming = listEventIncoming;
    }

    public List<EventDetail> getListEventOncoming() {
        return listEventOncoming;
    }

    public void setListEventOncoming(List<EventDetail> listEventOncoming) {
        this.listEventOncoming = listEventOncoming;
    }

    public List<EventDetail> getListEventEnded() {
        return listEventEnded;
    }

    public void setListEventEnded(List<EventDetail> listEventEnded) {
        this.listEventEnded = listEventEnded;
    }

    public List<Comments> getComments(String eventId) {
        return commentsFacade.findCommentByEventId(eventId);
    }

    public String customerStatistic() {
        List<Event> listEvent = eventFacade.findByDate(fromDate, todate);
        listEventIncoming = new ArrayList<EventDetail>();
        listEventOncoming = new ArrayList<EventDetail>();
        listEventEnded = new ArrayList<EventDetail>();
        int totalEvent = listEvent.size();
        int incoming = 0, oncoming = 0, ended = 0;
        Collections.reverse(listEvent);

        for (int i = 0; i < totalEvent; i++) {
            int numberEm = listEvent.get(i).getNumberEmployee();
            int countRegister = registerEventFacade.countRegisterByStatus(listEvent.get(i).getEventID());
            String eventTitle = listEvent.get(i).getEventTitle();
            String eventId = listEvent.get(i).getEventID();
            Date startDate = listEvent.get(i).getStartDate();
            Date endDate = listEvent.get(i).getEndDate();
            String typeEvent = listEvent.get(i).getEventTypeID().getEventTypeName();

            if (listEvent.get(i).getStatus().equals("Incoming")) {
                EventDetail eventDetail = new EventDetail(eventId, eventTitle, startDate, endDate, typeEvent, numberEm, countRegister);
                listEventIncoming.add(eventDetail);
                incoming++;
            } else if (listEvent.get(i).getStatus().equals("Ended")) {
                String winner = winnersFacade.getWinner(listEvent.get(i).getEventID());
                EventDetail eventDetail = new EventDetail(eventId, eventTitle, startDate, endDate, typeEvent, numberEm, countRegister, winner);
                listEventEnded.add(eventDetail);
                ended++;
            } else {
                EventDetail eventDetail = new EventDetail(eventId, eventTitle, startDate, endDate, typeEvent, numberEm, countRegister);
                listEventOncoming.add(eventDetail);
                oncoming++;
            }
        }
        setListEventIncoming(listEventIncoming);
        setListEventOncoming(listEventOncoming);
        setListEventEnded(listEventEnded);
        setTotal(totalEvent);
        setIncoming(incoming);
        setOncoming(oncoming);
        setEnded(ended);
        return "customer-statistic.xhtml?fromDate=" + fromDate.toString() + "&toDate=" + todate.toString() + "&faces-redirect=true";
    }

    public List<EventDetail> statisticEventByStatus(String status) {
        List<Event> listEvent = eventFacade.findByDate(fromDate, todate);
        List<EventDetail> list = new ArrayList<EventDetail>();
        for (int i = 0; i < listEvent.size(); i++) {
            int numberEm = listEvent.get(i).getNumberEmployee();
            int countRegister = registerEventFacade.countRegisterByStatus(listEvent.get(i).getEventID());
            String eventTitle = listEvent.get(i).getEventTitle();
            String eventId = listEvent.get(i).getEventID();
            Date startDate = listEvent.get(i).getStartDate();
            Date endDate = listEvent.get(i).getEndDate();
            String typeEvent = listEvent.get(i).getEventTypeID().getEventTypeName();
            if (listEvent.get(i).getStatus().equals(status)) {
                EventDetail event = new EventDetail(eventId, eventTitle, startDate, endDate, typeEvent, numberEm, countRegister);
                list.add(event);
            }
        }
        return list;
    }
    private Statistic statistic;

//    public Statistic statistic(){        
//        List<Event> listEvent = eventFacade.findAll();
//        int totalEvent =  listEvent.size();
//        int incoming = 0, oncoming = 0, ended = 0;
//        for(int i = 0; i<totalEvent; i ++){
//            if(listEvent.get(i).getStatus().equals("Incoming")) {
//                incoming++;
//            }else if (listEvent.get(i).getStatus().equals("Ended")){
//                ended ++;
//            }else
//                oncoming ++;            
//        }
//        Statistic statistic = new Statistic(totalEvent, incoming, oncoming, ended);
//        return statistic;
//    }
    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public List<Statistic> showEventsTop() {
        List<Event> list = eventFacade.showEventsTop(6);
        List<Statistic> listSta = new ArrayList<Statistic>();

        for (int i = 0; i < list.size(); i++) {
            int numberEm = list.get(i).getNumberEmployee();
            int countRegister = registerEventFacade.countRegisterByStatus(list.get(i).getEventID());
            Statistic sta = new Statistic(list.get(i).getEventID(), list.get(i).getEventTitle(), numberEm, countRegister, numberEm - countRegister);
            listSta.add(sta);
        }
        return listSta;
    }
}
