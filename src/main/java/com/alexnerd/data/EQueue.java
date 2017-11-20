/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.data;

import com.alexnerd.listeners.InitEQueueListener;
import com.alexnerd.ticket.Ticket;
import com.alexnerd.ticket.TicketStatus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Popov Aleksey 2017
 */
public class EQueue implements Serializable{
    private static final long serialVersionUID = 1L; 
    private final String resourceName = "config.properties";

    private final Queue<Ticket> queue;
    private final List<User> users;
    
    //Название организации
    private String companyName;
    
    //Бегущая строка
    private String runningString;
    
    //Список кнопок в терминале
    private List<TerminalButton> terminalButtons;
    
    private Properties properties;
    
        
    
    public EQueue(){
        queue = new LinkedList();
        users = new ArrayList();
        terminalButtons = new ArrayList();
        this.properties = initProperties();
    }
    
    public synchronized String getRunningString() {
        return runningString;
    }

    public synchronized void setRunningString(String runningString) {
        this.runningString = runningString;
    }
    
    
    public synchronized boolean addTicket(Ticket ticket){
        boolean offer = queue.offer(ticket);
        return offer;
    }
    
    public synchronized Ticket getFirstTicket(){
        return queue.poll();
    } 
    

    
    public synchronized int getQueueLength(){
        int i = 0;
        for(Ticket t : queue){
            if(t.getStatus() == TicketStatus.INQUEUE)
                i++;
        }
        return i;
    }
    
    public synchronized String[] getQueueTickets(){
        
        return null;
    }
    
    public synchronized String getCompanyName(){
        return companyName;
    }
    
    public synchronized void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    public synchronized void setTerminalButtons(List<TerminalButton> terminalButtons){
        this.terminalButtons = terminalButtons;
    }
    
    public synchronized List<TerminalButton> getTerminalButtons(){
        return terminalButtons;
    }    
    public synchronized void addTerminalButton(TerminalButton button){
        terminalButtons.add(button);
    }
    
    public synchronized TerminalButton getTerminalButton(TerminalButton button){
        int i = terminalButtons.indexOf(button);
        if(i != -1)
            return terminalButtons.get(i);
        else
            return null;
    }
    
    public synchronized TerminalButton getTerminalButton(int number){        
        return terminalButtons.get(number);
    }
    
    public synchronized TerminalButton getTerminalButtonById(long buttonId){        
        for(TerminalButton button : terminalButtons){
            if(button.getButtonId() == buttonId){
                return button;
            }           
        }
        return null;
    }
    
    public synchronized boolean deleteTerminalButton(long buttonId){
        boolean isDelete = false;
        for(TerminalButton button : terminalButtons){
            if(button.getButtonId() == buttonId){
                isDelete = terminalButtons.remove(button);
                break;
            }           
        }
        return isDelete;
    }
    
    public synchronized boolean deleteTerminalButton(TerminalButton button){        
        return terminalButtons.remove(button);
    }
    
    
    
    //Working with users block
    
    public synchronized List getUsers(){        
        return users;
    }
    
    public synchronized void addUser(User user){
        users.add(user);
    }
    
    public synchronized User getUser(int number){        
        return users.get(number);
    }
    
    public synchronized User getUser(User user){
        int i = users.indexOf(user);
        if(i != -1)
            return users.get(i);
        else
            return null;
    }
    
    public synchronized User getUserById(long userId){        
        for(User user : users){
            if(user.getUserId() == userId){
                return user;
            }           
        }
        return null;
    }
    
    public synchronized User getUserByIdAndRole(long userId, UserRole userRole){        
        for(User user : users){
            if(user.getUserId() == userId && user.getUserRole() == userRole){
                return user;
            }           
        }
        return null;
    }
    
    public synchronized User getUserByLogin(String login){
        return users.stream().filter(s -> s.getLogin().equals(login)).findAny().orElse(null);
    }
    
    public synchronized boolean deleteUser(User user){
        return users.remove(user);
    }
    
    public synchronized boolean deleteUser(long userId){
        boolean isDelete = false;
        for(User user : users){
            if(user.getUserId() == userId){
                isDelete = users.remove(user);
                break;
            }           
        }
        return isDelete;
    }    
    
    public synchronized UserRole[] getUserRoles(){
        return UserRole.values();
    }
    
    //End block
    
    //Working with properties block
    
    public synchronized void setProperties(Properties properties){
        this.properties = properties;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(resourceName))){
            this.properties.store(bw, "EQueue properties file");            
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(EQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EQueue.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public synchronized Properties getProperties(){
        return properties;
    }
    
    private Properties initProperties(){        
        Properties props = new Properties();        
        try(BufferedReader fr = new BufferedReader(new FileReader(resourceName))){
            props.load(fr);            
        } catch (IOException ex) {
            Logger.getLogger(InitEQueueListener.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return props;
    }
    
    //End block
    
        
    
    /*
    public synchronized String checkNullRequest(String str){
        return str == null ? "hello" : str;
    }
    */
    
    /*private void setButtonsName(){
        List<TerminalButton> terminalButtons = configuration.getTerminalButtons();
        for(TerminalButton t : terminalButtons){
            for(String name;;){
                
            }
        }
    }*/
    
    
}
