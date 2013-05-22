/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TrungThanh
 */
@Entity
@Table(name = "PrizesDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrizesDetail.findAll", query = "SELECT p FROM PrizesDetail p"),
    @NamedQuery(name = "PrizesDetail.findByPrizesDetailID", query = "SELECT p FROM PrizesDetail p WHERE p.prizesDetailID = :prizesDetailID")})
public class PrizesDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PrizesDetailID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prizesDetailID;
    @JoinColumn(name = "PrizeID", referencedColumnName = "PrizeID")
    @ManyToOne
    private Prizes prizeID;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @ManyToOne
    private Employee employeeID;

    public PrizesDetail() {
    }

    public PrizesDetail(Integer prizesDetailID) {
        this.prizesDetailID = prizesDetailID;
    }

    public Integer getPrizesDetailID() {
        return prizesDetailID;
    }

    public void setPrizesDetailID(Integer prizesDetailID) {
        this.prizesDetailID = prizesDetailID;
    }

    public Prizes getPrizeID() {
        return prizeID;
    }

    public void setPrizeID(Prizes prizeID) {
        this.prizeID = prizeID;
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
        hash += (prizesDetailID != null ? prizesDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrizesDetail)) {
            return false;
        }
        PrizesDetail other = (PrizesDetail) object;
        if ((this.prizesDetailID == null && other.prizesDetailID != null) || (this.prizesDetailID != null && !this.prizesDetailID.equals(other.prizesDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrizesDetail[ prizesDetailID=" + prizesDetailID + " ]";
    }
    
}
