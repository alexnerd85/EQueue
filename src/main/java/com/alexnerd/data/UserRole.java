/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.data;

/**
 *
 * @author Popov Aleksey 2017
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
