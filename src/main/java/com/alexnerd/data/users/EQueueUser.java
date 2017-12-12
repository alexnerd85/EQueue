/*
 *   Created on : 12.06.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data.users;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EQueueUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "USERID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USERID")
    protected long userId;
    
    //protected String login;
    
    public abstract UserRole getUserRole();
    
    //public void setUserId(int userId);
    public abstract long getUserId();
      
    public abstract void setLogin(String login);
    public abstract String getLogin();
    
    public abstract void setPassword(String password);
    public abstract String getPassword();
    
    public abstract void setSirname(String sirname);
    public abstract String getSirname();
    
    public abstract void setName(String name);
    public abstract String getName();
    
    public abstract void setMiddlename(String middlename);
    public abstract String getMiddlename();
}
