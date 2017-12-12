/*
 *   Created on : 12.06.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data.users;

import com.alexnerd.data.Available;
import com.alexnerd.data.ticket.Ticket;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "OperatorApp")
public class Operator extends EQueueUser implements Serializable, Available {
    private static final long serialVersionUID = 1L;    
    
    //private static AtomicLong id = new AtomicLong();
    private static AtomicInteger window = new AtomicInteger();

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private long userId;
    
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;
    
    private Ticket ticket;
    
    private boolean available;
    private int numWindow;
    private String login;
    private String password;
    private String name;
    private String sirname;
    private String middlename;
    
    /*public Operator(int numWindow){        
        this(false, numWindow);
    }
    
    public Operator(boolean available, int numWindow){
        this.available = available;
        this.numWindow = numWindow;
        this.userRole = UserRole.OPERATOR;
    }*/
    
    public Operator(){
        this.userRole = UserRole.OPERATOR;
        
        this.login = null;
        this.password = null;
        this.name = null;
        this.sirname = null;
        this.middlename = null;
        this.available = false;
        this.numWindow = 0;
        
        
        //this.userId = id.incrementAndGet();
        //this.numWindow = window.incrementAndGet();
    }
    
    public Operator(String login, String password, boolean available, int numWindow){
         this(login, password, "", "", "", available);
    }
    
    public Operator(String login, String password, String sirname, String name, 
            String middlename, boolean available){
        this.login = login;
        this.password = password;
        this.name = name;
        this.sirname = sirname;
        this.middlename = middlename;
        this.available = available;
        this.userRole = UserRole.OPERATOR;
        
        //this.userId = id.incrementAndGet();
        this.numWindow = window.incrementAndGet();
    }
    
    /*
    @Override
    public synchronized int getUserId() {
        return userId;
    }

    @Override
    public synchronized void setUserId(int userId) {
        this.userId = userId;
    }
*/
    @Override
    public synchronized long getUserId() {
        return userId;
    }

    
    @Override
    public synchronized boolean isAvailable(){
        return available;
    }
    
    public synchronized void setAvailable(boolean available){
        this.available = available;
    }
    
    public synchronized int getNumWindow(){
        return numWindow;
    }
    
    public synchronized void setNumWindow(int numWindow){
        this.numWindow = numWindow;
    }
    
    public synchronized Ticket getTicket(){
        return ticket;
    }
    
    public synchronized void setTicket(Ticket ticket){
        this.ticket = ticket;
    }
    
    public synchronized void cancelTiket(){
        this.ticket = null;
    }
     
    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    @Override
    public String getSirname() {
        return sirname;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public String getMiddlename() {
        return middlename;
    }
    
    @Override
    public UserRole getUserRole() {
        return this.userRole;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.available ? 1 : 0);
        hash = 67 * hash + this.numWindow;
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
        final Operator other = (Operator) obj;
        if (this.available != other.available) {
            return false;
        }
        if (this.numWindow != other.numWindow) {
            return false;
        }
        if (this.userId != other.userId){
            return false;
        }
        return true;
    }
}
