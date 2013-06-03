/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SONPV90
 */
@Entity
@Table(name = "Winners")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Winners.findAll", query = "SELECT w FROM Winners w"),
    @NamedQuery(name = "Winners.findById", query = "SELECT w FROM Winners w WHERE w.id = :id"),
    @NamedQuery(name = "Winners.findByEmployeesName", query = "SELECT w FROM Winners w WHERE w.employeesName = :employeesName"),
    @NamedQuery(name = "Winners.findByEmployeesId", query = "SELECT w FROM Winners w WHERE w.employeesId = :employeesId"),
    @NamedQuery(name = "Winners.findByIsWin", query = "SELECT w FROM Winners w WHERE w.isWin = :isWin")})
public class Winners implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "EmployeesName")
    private String employeesName;
    @Size(max = 30)
    @Column(name = "EmployeesId")
    private String employeesId;
    @Column(name = "IsWin")
    private Boolean isWin;
    @JoinColumn(name = "PrizeID", referencedColumnName = "PrizeID")
    @ManyToOne
    private Prizes prizeID;

    public Winners() {
    }

    public Winners(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
    }

    public String getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(String employeesId) {
        this.employeesId = employeesId;
    }

    public Boolean getIsWin() {
        return isWin;
    }

    public void setIsWin(Boolean isWin) {
        this.isWin = isWin;
    }

    public Prizes getPrizeID() {
        return prizeID;
    }

    public void setPrizeID(Prizes prizeID) {
        this.prizeID = prizeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Winners)) {
            return false;
        }
        Winners other = (Winners) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Winners[ id=" + id + " ]";
    }
    
}
