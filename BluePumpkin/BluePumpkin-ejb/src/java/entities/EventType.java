/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TrungThanh
 */
@Entity
@Table(name = "EventType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventType.findAll", query = "SELECT e FROM EventType e"),
    @NamedQuery(name = "EventType.findByEventTypeID", query = "SELECT e FROM EventType e WHERE e.eventTypeID = :eventTypeID"),
    @NamedQuery(name = "EventType.findByEventTypeName", query = "SELECT e FROM EventType e WHERE e.eventTypeName = :eventTypeName"),
    @NamedQuery(name = "EventType.findByDescriptionType", query = "SELECT e FROM EventType e WHERE e.descriptionType = :descriptionType")})
public class EventType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "EventTypeID")
    private String eventTypeID;
    @Size(max = 50)
    @Column(name = "EventTypeName")
    private String eventTypeName;
    @Size(max = 1073741823)
    @Column(name = "DescriptionType")
    private String descriptionType;
    @OneToMany(mappedBy = "eventTypeID")
    private List<Event> eventList;

    public EventType() {
    }

    public EventType(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public EventType(String eventTypeID, String eventTypeName, String descriptionType) {
        this.eventTypeID = eventTypeID;
        this.eventTypeName = eventTypeName;
        this.descriptionType = descriptionType;
    }        

    public String getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }

    @XmlTransient
    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventTypeID != null ? eventTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventType)) {
            return false;
        }
        EventType other = (EventType) object;
        if ((this.eventTypeID == null && other.eventTypeID != null) || (this.eventTypeID != null && !this.eventTypeID.equals(other.eventTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EventType[ eventTypeID=" + eventTypeID + " ]";
    }
    
}
