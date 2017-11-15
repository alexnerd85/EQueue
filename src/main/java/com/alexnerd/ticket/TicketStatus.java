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

//Класс перечисления для статуса талона
//COMPLETE - Талон обработан оператором
//INQUEUE - Талон в очереди
//MISSED - Талон пропущен оператором
//CANCELLED - Талон отменен оператором

public enum TicketStatus {
    COMPLETE {
        @Override
        public String getString() {
            return "COMPLETE";
        }
    }, INQUEUE {
        @Override
        public String getString() {
            return "INQUEUE";
        }
    }, INWORK {
        @Override
        public String getString() {
            return "INWORK";
        }        
    }, MISSED {
        @Override
        public String getString() {
            return "MISSED";
        }
    };
    
    //Абстрактный метод для JSONAdapter
    //JavaScript не поддерживает перечисления
    public abstract String getString();
}
