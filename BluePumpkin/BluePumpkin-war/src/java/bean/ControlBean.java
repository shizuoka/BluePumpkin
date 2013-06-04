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
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author TrungThanh
 */
@ManagedBean
@SessionScoped
public class ControlBean implements Serializable{
    @EJB
    private EventFacade eventFacade;
    //Primitives
    private static final int BUFFER_SIZE = 6124;    
    private String folderToUpload;
    
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
    
    private List<Event> listEventByDate;

    public List<Event> getListEventByDate() {
        return listEventByDate;
    }

    public void setListEventByDate(List<Event> listEventByDate) {
        this.listEventByDate = listEventByDate;
    }    
    
    public void findByDate(){
        listEventByDate=eventFacade.findByDate();        
    }
    
    public void handleFileUpload(FileUploadEvent event) {
       
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();            
            File result = new File(extContext.getRealPath("//web//images//" + event.getFile().getFileName()));
            System.out.println(extContext.getRealPath("//web//images//" + event.getFile().getFileName()));

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

                FacesMessage msg = new FacesMessage("File Description", "file name: " + event.getFile().getFileName() + "<br/>file size: " + event.getFile().getSize() / 1024 + " Kb<br/>content type: " + event.getFile().getContentType() + "<br/><br/>The file was uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } catch (IOException e) {
                e.printStackTrace();

                FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The files were not uploaded!", "");
                FacesContext.getCurrentInstance().addMessage(null, error);
            }       
    }    
}
