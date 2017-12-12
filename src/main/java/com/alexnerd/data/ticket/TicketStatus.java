/*
 *   Created on : 19.11.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data.ticket;

//Класс перечисления для статуса талона
//COMPLETE - Талон обработан оператором
//INQUEUE - Талон в очереди
//MISSED - Талон пропущен оператором
//CANCELLED - Талон отменен оператором

public enum TicketStatus {
    COMPLETE, INQUEUE, INWORK, REPEAT, MISSED    
}
