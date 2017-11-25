/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.ticket;

/**
 *
 *   @Created    : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

//Класс перечисления для статуса талона
//COMPLETE - Талон обработан оператором
//INQUEUE - Талон в очереди
//MISSED - Талон пропущен оператором
//CANCELLED - Талон отменен оператором

public enum TicketStatus {
    COMPLETE, INQUEUE, INWORK, REPEAT, MISSED    
}
