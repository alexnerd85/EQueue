/*
 *   Created on : 12.06.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data.users;

import com.alexnerd.data.Available;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "USERAPP")
public class User extends EQueueUser implements Serializable, Available {
    private static final long serialVersionUID = 1L;    
    
    //private static AtomicLong id = new AtomicLong();
      
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private long userId;
    
    @Enumerated(EnumType.ORDINAL)
    private final UserRole userRole;
    
    private boolean available;
    private String login;
    private String password;
    private String name;
    private String sirname;
    private String middlename;
    
    public User(){
        this.userRole = UserRole.USER;
        
        this.login = null;
        this.password = null;
        this.name = null;
        this.sirname = null;
        this.middlename = null;
        
        //this.userId = id.incrementAndGet();
    }
    
    public User(String login, String password, String sirname, String name, 
            String middlename){
        this.userRole = UserRole.USER;
        this.login = login;
        this.password = password;
        this.name = name;
        this.sirname = sirname;
        this.middlename = middlename;
        
        //this.userId = id.incrementAndGet();
    }

    @Override
    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    @Override
    public String getSirname() {
        return sirname;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public String getMiddlename() {
        return middlename;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }
    
}
