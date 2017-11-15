/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.ticket;

/**
 *
 * @author Popov Aleksey 2017
 */

//Класс перечисления используется для установки приоритета талона
//низкий, стандартный и высокий приоритеты

public enum TicketPriority {
    LOW {
    @Override
    public String getString(){return "LOW";}
    }, NORMAL {
    @Override
    public String getString(){return "NORMAL";}
    }, HIGH {
    @Override
    public String getString(){return "HIGH";}
    };
    
    //Абстрактный метод для JSONAdapter
    //JavaScript не поддерживает перечисления
    public abstract String getString();
}
