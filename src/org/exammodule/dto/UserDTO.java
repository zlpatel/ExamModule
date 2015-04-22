/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.exammodule.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "ExamModuleDB", schema = "",name="users")
@XmlRootElement
public class UserDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
	@Column(nullable = false, length = 40,name="userName")
    private String userName;
	
    @Basic(optional = false)
    @Column(nullable = false, length = 40,name="fullName")
    private String name;
        
    @Basic(optional = false)
    @Column(nullable = false, length = 32,name="passkey")
    private String password;
    
    @Column(nullable = false, length = 10)
    private String access;
    
    @Column(name="account_not_blocked")
    private boolean accountNotBlocked;
    /**
	 * Access level of the user. 
	 * 1 = Admin user
	 * 2 = Regular user (Video)
	 * 3 = Regular user (Image)
	 * 4 = Regular user (Nothing)
	 */
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public UserDTO() {
    }

    public UserDTO(String userName) {
        this.userName = userName;
    }

    public UserDTO(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Test_QB_ORM.Students[ userName=" + userName + " ]";
    }

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public boolean isAccountNotBlocked() {
		return accountNotBlocked;
	}

	public void setAccountNotBlocked(boolean accountNotBlocked) {
		this.accountNotBlocked = accountNotBlocked;
	}
	
    
}
