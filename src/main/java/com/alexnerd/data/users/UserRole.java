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

public enum UserRole {
    ADMIN{
    @Override
    public String getString(){return "ADMIN";}
    }, 
    OPERATOR{
    @Override
    public String getString(){return "OPERATOR";}
    }, 
    USER{
    @Override
    public String getString(){return "USER";}
    };
    
    public abstract String getString();
}
