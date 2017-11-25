/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.data;

import com.alexnerd.ticket.*;
import java.time.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 *   @Created    : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

public class TerminalButton implements Serializable, Available{
    private static final long serialVersionUID = 1L;
    
    private static AtomicLong id = new AtomicLong();
    
    private long buttonId;
    private String name;            // название кнопки
    private String prefix;          // префикс талона
    private int numTickets;         // количество доступных толнов в кнопке
    private int currentNumber;      // текущий номер талона
    private boolean available;      // активна ли кнопка 
    private LocalDateTime beginDate;// дата начала действия кнопки
    private LocalDateTime endDate;  // дата окончания действия кнопки
        
    public TerminalButton(String name, String prefix, int numTickets, 
            boolean available){
        this.name = name;
        this.prefix = prefix;
        this.numTickets = numTickets;
        this.available = available;
        buttonId = id.incrementAndGet();
        currentNumber = 0;
    }
    
    /*
    public void setName(String name){
        this.name = name;
    }
    */
    
    public long getButtonId(){
        return buttonId;
    }
    
    public String getName(){
        return name;
    }
    
    public synchronized void setName(String name){
        this.name = name;
    }
    
    public void addTiket(){
        numTickets++;
    }
    
    public void removeTicket(){
        if(numTickets <= 1){
            numTickets = 0;
            available = false;
        } else {
            numTickets--;
        }
        System.out.println("REMOVE TICKET");
    }
    
    public void setNumTickets(int numTickets){
        this.numTickets = numTickets;
    }
    
    public int getNumTickets(){
        return numTickets;
    }
    
    public void setAvailable(boolean available){
        this.available = available;
    }
            
    public Ticket getTicket(){
        if(available){
            currentNumber++;
            return new Ticket(currentNumber, prefix, TicketPriority.NORMAL, TicketStatus.INQUEUE);            
        }        
        else throw new NullPointerException("Tickets not available");        
    }
    
    public int getCurrentNumber(){
        return currentNumber;
    } 
       
    public String getPrefix(){
        return prefix;
    }
    
    public synchronized void setPrefix(String prefix){
        this.prefix = prefix;
    }
    
    @Override
    public boolean isAvailable(){
        return available;
    }
    
    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.buttonId ^ (this.buttonId >>> 32));
        return hash;
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
        final TerminalButton other = (TerminalButton) obj;
        if (this.buttonId != other.buttonId) {
            return false;
        }
        return true;
    }
    
}
