/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TrungThanh
 */
@Entity
@Table(name = "RegisterEvent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegisterEvent.findAll", query = "SELECT r FROM RegisterEvent r"),
    @NamedQuery(name = "RegisterEvent.findByRegisterID", query = "SELECT r FROM RegisterEvent r WHERE r.registerID = :registerID"),
    @NamedQuery(name = "RegisterEvent.findByRegisterDate", query = "SELECT r FROM RegisterEvent r WHERE r.registerDate = :registerDate"),
    @NamedQuery(name = "RegisterEvent.findByIsAccept", query = "SELECT r FROM RegisterEvent r WHERE r.isAccept = :isAccept")})
public class RegisterEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RegisterID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registerID;
    @Column(name = "RegisterDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Column(name = "IsAccept")
    private Boolean isAccept;
    @JoinColumn(name = "EventID", referencedColumnName = "EventID")
    @ManyToOne
    private Event eventID;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @ManyToOne
    private Employee employeeID;

    public RegisterEvent() {
    }

    public RegisterEvent(Integer registerID) {
        this.registerID = registerID;
    }

    public Integer getRegisterID() {
        return registerID;
    }

    public void setRegisterID(Integer registerID) {
        this.registerID = registerID;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(Boolean isAccept) {
        this.isAccept = isAccept;
    }

    public Event getEventID() {
        return eventID;
    }

    public void setEventID(Event eventID) {
        this.eventID = eventID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registerID != null ? registerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegisterEvent)) {
            return false;
        }
        RegisterEvent other = (RegisterEvent) object;
        if ((this.registerID == null && other.registerID != null) || (this.registerID != null && !this.registerID.equals(other.registerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RegisterEvent[ registerID=" + registerID + " ]";
    }
    
}
