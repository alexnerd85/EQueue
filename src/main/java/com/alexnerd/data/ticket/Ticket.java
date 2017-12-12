/*
 *   Created on : 19.11.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data.ticket;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //private static AtomicLong id = new AtomicLong();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TICKETID")
    private long ticketId;
    
    //время обработки талона оператором
    @Column(name = "ACTIONTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime actionTime;   
    
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "NUMBER")
    private int number;
    
    //время взятия талона
    @Column(name = "PICKTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime pickTime;
    
    // приоритет талона (перечисления)
    @Column(name = "PRIORITY")
    @Enumerated(EnumType.ORDINAL)
    private TicketPriority priority;    
    
    // статус талона (перечисления)
    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status;      
    
    /*
    @JoinTable(name = "EQUEUE_TICKET", joinColumns = {
        @JoinColumn(name = "QUEUE_TICKETID", referencedColumnName = "TICKETID")}, inverseJoinColumns = {
        @JoinColumn(name = "EQUEUE_ID", referencedColumnName = "ID")})
    @ManyToOne
    private EQueue eQueue;
    */
    
    public Ticket(){
        this.number = 0;
        this.name = null;
        this.priority = null;
        this.status = null;
    }
    
    public Ticket(int number, String name, TicketPriority priority, TicketStatus status){
        this.number = number;
        this.name = name;
        this.priority = priority;
        this.status = status;
        
        //ticketId = id.incrementAndGet();
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
    
    /*
    @XmlTransient
    public EQueue getEQueue() {
        return eQueue;
    }

    public void setEQueue(EQueue eQueue) {
        this.eQueue = eQueue;
    }
    */
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

