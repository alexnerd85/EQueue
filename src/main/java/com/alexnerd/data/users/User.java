/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.data.users;


/**
 *
 *   @Created on : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

public interface User {    
    public UserRole getUserRole();
    
    //public void setUserId(int userId);
    public long getUserId();
      
    public void setLogin(String login);
    public String getLogin();
    
    public void setPassword(String password);
    public String getPassword();
    
    public void setSirname(String sirname);
    public String getSirname();
    
    public void setName(String name);
    public String getName();
    
    public void setMiddlename(String middlename);
    public String getMiddlename();
}
