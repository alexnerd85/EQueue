/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Aleksey
 */
public class Admin implements Serializable, User, Available{
    private static final long serialVersionUID = 1L;
    
    private static AtomicLong id = new AtomicLong();        
    
    private final long userId;
    private final UserRole userRole;
    private String login;
    private String password;
    private String name;
    private String sirname;
    private String middlename;
    
    public Admin(String login, String password, String sirname, String name, 
            String middlename){
        this.userRole = UserRole.ADMIN;
        this.login = login;
        this.password = password;
        this.name = name;
        this.sirname = sirname;
        this.middlename = middlename;
        
        this.userId = id.incrementAndGet();
    }

    @Override
    public synchronized UserRole getUserRole() {
        return userRole;
    }

    @Override
    public synchronized long getUserId() {
        return userId;
    }

    @Override
    public synchronized void setLogin(String login) {
        this.login = login;
    }

    @Override
    public synchronized String getLogin() {
        return login;
    }

    @Override
    public synchronized void setPassword(String password) {
        this.password = password;
    }

    @Override
    public synchronized String getPassword() {
        return password;
    }

    @Override
    public synchronized void setSirname(String sirname) {
        this.sirname = sirname;
    }

    @Override
    public synchronized String getSirname() {
        return sirname;
    }

    @Override
    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String getName() {
        return name;
    }

    @Override
    public synchronized void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public synchronized String getMiddlename() {
        return middlename;
    }

    @Override
    public boolean isAvailable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
