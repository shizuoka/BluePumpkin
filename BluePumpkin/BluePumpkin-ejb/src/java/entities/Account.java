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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TrungThanh
 */
@Entity
@Table(name = "Account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountID", query = "SELECT a FROM Account a WHERE a.accountID = :accountID"),
    @NamedQuery(name = "Account.findByPassWord", query = "SELECT a FROM Account a WHERE a.passWord = :passWord")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AccountID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountID;
    @Size(max = 50)
    @Column(name = "PassWord")
    private String passWord;
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID")
    @ManyToOne
    private Roles roleID;
    @JoinColumn(name = "UserName", referencedColumnName = "EmployeeID")
    @ManyToOne
    private Employee userName;

    public Account() {
    }
    
    public boolean isLoged() {
        if(userName.equals("") || passWord.equals("")) {
            return false;
        }
        return true;
    }

    public Account(Integer accountID) {
        this.accountID = accountID;
    }

    public Account(String passWord, Roles roleID, Employee userName) {        
        this.passWord = passWord;
        this.roleID = roleID;
        this.userName = userName;
    }        

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Roles getRoleID() {
        return roleID;
    }

    public void setRoleID(Roles roleID) {
        this.roleID = roleID;
    }

    public Employee getUserName() {
        return userName;
    }

    public void setUserName(Employee userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountID != null ? accountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountID == null && other.accountID != null) || (this.accountID != null && !this.accountID.equals(other.accountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Account[ accountID=" + accountID + " ]";
    }
    
}
