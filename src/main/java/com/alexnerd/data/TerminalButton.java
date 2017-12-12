/*
 *   Created on : 12.06.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data;

import com.alexnerd.data.ticket.TicketStatus;
import com.alexnerd.data.ticket.TicketPriority;
import com.alexnerd.data.ticket.Ticket;
import java.time.*;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TERMINALBUTTON")
public class TerminalButton implements Serializable, Available{
    private static final long serialVersionUID = 1L;

    //private static AtomicLong id = new AtomicLong();
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BUTTONID")
     private long buttonId;
    
    // активна ли кнопка
    @Column(name = "AVAILABLE")
    private boolean available; 
    
    // дата начала действия кнопки
    @Column(name = "BEGINDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime beginDate;
    
    // текущий номер талона
    @Column(name = "CURRENTNUMBER")
    private int currentNumber; 
    
    // дата окончания действия кнопки
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate; 
    
    // название кнопки
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;            
    
    // количество доступных толнов в кнопке
    @Column(name = "NUMTICKETS")
    private int numTickets;         
    
    // префикс талона
    @Size(max = 255)
    @Column(name = "PREFIX")
    private String prefix; 
    
    /*
    @ManyToOne
    private EQueue eQueue;
    */
    
    public TerminalButton(){
        this.name = null;
        this.prefix = null;
        this.numTickets = 0;
        this.available = false;
        //buttonId = id.incrementAndGet();
    }
        
    public TerminalButton(String name, String prefix, int numTickets, 
            boolean available){
        this.name = name;
        this.prefix = prefix;
        this.numTickets = numTickets;
        this.available = available;
        //buttonId = id.incrementAndGet();
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
    
    public synchronized void addTiket(){
        numTickets++;
    }
    
    public synchronized void removeTicket(){
        if(numTickets <= 1){
            numTickets = 0;
            available = false;
        } else {
            numTickets--;
        }
    }
    
    public synchronized void setNumTickets(int numTickets){
        this.numTickets = numTickets;
    }
    
    public synchronized int getNumTickets(){
        return numTickets;
    }
    
    public synchronized void setAvailable(boolean available){
        this.available = available;
    }
            
    public synchronized Ticket getTicket(){
        if(available){
            currentNumber++;
            System.out.println("********************************* CurrentNumber " + currentNumber);
            System.out.println("********************************* numTickets " + numTickets);
            return new Ticket(currentNumber, prefix, TicketPriority.NORMAL, TicketStatus.INQUEUE);            
        }        
        else throw new NullPointerException("Tickets not available");        
    }
    
    public synchronized int getCurrentNumber(){
        return currentNumber;
    } 
       
    public String getPrefix(){
        return prefix;
    }
    
    public synchronized void setPrefix(String prefix){
        this.prefix = prefix;
    }
    
    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public synchronized void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    } 

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public synchronized void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
   
    /*
    @XmlTransient
    public EQueue getEQueue() {
        return eQueue;
    }

    public void setEQueue(EQueue eQueue) {
        this.eQueue = eQueue;
    }
    */
    
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
