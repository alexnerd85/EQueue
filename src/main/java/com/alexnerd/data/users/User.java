/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.data.users;

import com.alexnerd.data.Available;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 *   @Created    : 24.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

public class User implements EQueueUser, Serializable, Available {
    private static final long serialVersionUID = 1L;    
    
    private static AtomicLong id = new AtomicLong();
        
    private boolean available;
    private long userId;
    private final UserRole userRole;
    private String login;
    private String password;
    private String name;
    private String sirname;
    private String middlename;
    
    public User(String login, String password, String sirname, String name, 
            String middlename){
        this.userRole = UserRole.USER;
        this.login = login;
        this.password = password;
        this.name = name;
        this.sirname = sirname;
        this.middlename = middlename;
        
        this.userId = id.incrementAndGet();
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
