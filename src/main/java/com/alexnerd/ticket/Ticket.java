/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.ticket;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 *   @Created on : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static AtomicLong id = new AtomicLong();
    
    private long ticketId;
    private int number;  // номер талона
    private String name;    // название талона
    private TicketPriority priority;    // приоритет талона (перечисления)
    private TicketStatus status;      // статус талона (перечисления)
    private LocalDateTime pickTime;     //время взятия талона
    private LocalDateTime actionTime;   //время обработки талона оператором
    
    public Ticket(int number, String name, TicketPriority priority, TicketStatus status){
        this.number = number;
        this.name = name;
        this.priority = priority;
        this.status = status;
        ticketId = id.incrementAndGet();
        /*this.number = 1;
        this.priority = TicketPriority.NORMAL;
        this.status = TicketStatus.INQUEUE;*/
    }
    
    /*public Ticket(int number, String name, TicketPriority priority, 
            TicketStatus status){
        this.number = number;
        this.priority = priority;
        this.status = status;
    }*/
    
    public long getTicketId(){
        return ticketId;
    }
    
    public synchronized void setNumber(int number){
        this.number = number;
    }
    
    public synchronized int getNumber(){
        return this.number;
    }
    
    public synchronized void setPriority(TicketPriority priority){
        this.priority = priority;
    }
    
    public synchronized TicketPriority getPriority(){
        return this.priority;
    }
    
    public synchronized void setStatus(TicketStatus status){
        this.status = status;
    }
    
    public synchronized TicketStatus getStatus(){
        return this.status;
    }
    
    public synchronized void setName(String name){
        this.name = name;
    }
    
    public synchronized String getName(){
        return this.name;
    }
    
    public synchronized void setActionDate(LocalDateTime actionTime){
        this.actionTime = actionTime;
    }
    
    public synchronized LocalDateTime getActionDate(){
        return actionTime;
    }
    
    public synchronized void setPickDate(LocalDateTime pickTime){
        this.pickTime = pickTime;
    }
    
    public synchronized LocalDateTime getPickDate(){
        return pickTime;
    }
    
    /*@Override
    public String toString(){
        return getName() + getNumber();
    }*/

    @Override
    public String toString() {
        return name + number;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.priority != other.priority) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.number;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.priority);
        hash = 89 * hash + Objects.hashCode(this.status);
        return hash;
    }
    
    
}

