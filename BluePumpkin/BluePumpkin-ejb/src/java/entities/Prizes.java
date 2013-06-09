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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Prizes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prizes.findAll", query = "SELECT p FROM Prizes p"),
    @NamedQuery(name = "Prizes.findByPrizeID", query = "SELECT p FROM Prizes p WHERE p.prizeID = :prizeID"),
    @NamedQuery(name = "Prizes.findByPrizeName", query = "SELECT p FROM Prizes p WHERE p.prizeName = :prizeName")})
public class Prizes implements Serializable {
    @Column(name = "Description")
    private String description;
    @Column(name = "numberOfPrize")
    private Integer numberOfPrize;
    @OneToMany(mappedBy = "prizeID")
    private List<Winners> winnersList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PrizeID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prizeID;
    @Size(max = 30)
    @Column(name = "PrizeName")
    private String prizeName;
    @OneToMany(mappedBy = "prizeID")
    private List<PrizesDetail> prizesDetailList;
    @JoinColumn(name = "EventID", referencedColumnName = "EventID")
    @ManyToOne
    private Event eventID;

    public Prizes() {
    }

    public Prizes(Integer prizeID) {
        this.prizeID = prizeID;
    }

    public Prizes(String description, Integer numberOfPrize, String prizeName, Event eventID) {
        this.description = description;
        this.numberOfPrize = numberOfPrize;
        this.prizeName = prizeName;
        this.eventID = eventID;
    }        

    public Integer getPrizeID() {
        return prizeID;
    }

    public void setPrizeID(Integer prizeID) {
        this.prizeID = prizeID;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    @XmlTransient
    public List<PrizesDetail> getPrizesDetailList() {
        return prizesDetailList;
    }

    public void setPrizesDetailList(List<PrizesDetail> prizesDetailList) {
        this.prizesDetailList = prizesDetailList;
    }

    public Event getEventID() {
        return eventID;
    }

    public void setEventID(Event eventID) {
        this.eventID = eventID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prizeID != null ? prizeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prizes)) {
            return false;
        }
        Prizes other = (Prizes) object;
        if ((this.prizeID == null && other.prizeID != null) || (this.prizeID != null && !this.prizeID.equals(other.prizeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prizes[ prizeID=" + prizeID + " ]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfPrize() {
        return numberOfPrize;
    }

    public void setNumberOfPrize(Integer numberOfPrize) {
        this.numberOfPrize = numberOfPrize;
    }

    @XmlTransient
    public List<Winners> getWinnersList() {
        return winnersList;
    }

    public void setWinnersList(List<Winners> winnersList) {
        this.winnersList = winnersList;
    }
    
}
