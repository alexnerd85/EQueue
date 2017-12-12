/*
 *   Created on : 12.06.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data;

import com.alexnerd.data.users.UserRole;
import com.alexnerd.listeners.InitEQueueListener;
import com.alexnerd.data.ticket.Ticket;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.alexnerd.data.users.EQueueUser;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EQUEUE")
public class EQueue implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //private static volatile EQueue instance;
    
    public static int i = 0;
    
    @Id
    @Column(name = "ID")
    private final long id = 1;
    
    //Название организации
    @Size(max = 255)
    @Column(name = "COMPANYNAME")
    private String companyName;

    @Lob
    @Column(name = "PROPERTIES")
    private Properties properties;
    
    @Size(max = 255)
    @Column(name = "RESOURCENAME")
    private final String resourceName;
    
    //Бегущая строка
    @Size(max = 255)
    @Column(name = "RUNNINGSTRING")
    private String runningString;
    
    //@JoinTable(name = "EQUEUE_TICKET", joinColumns={
    //    @JoinColumn(name = "EQUEUE_ID", referencedColumnName = "ID")}, inverseJoinColumns={
     //   @JoinColumn(name = "QUEUE_TICKEID", referencedColumnName ="TICKETID")})
    
    @OneToMany(fetch=EAGER, cascade=CascadeType.ALL)
    private List<Ticket> queue;
    
    
    //Список пользователей
    /*
    @JoinTable(name = "EQUEUE_EQUEUEUSER", joinColumns={
        @JoinColumn(name = "EQUEUE_ID", referencedColumnName = "ID")}, inverseJoinColumns={
        @JoinColumn(name = "USERS_USERID", referencedColumnName ="TICKETID")})
    */
    @OneToMany(fetch=EAGER, cascade=CascadeType.ALL)
    private final List<EQueueUser> users;
    
    //Список кнопок в терминале
    /*@JoinTable(name = "EQUEUE_TERMINALBUTTON", joinColumns = {
        @JoinColumn(name = "EQUEUE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "TERMINALBUTTONS_BUTTONID", referencedColumnName = "BUTTONID")})*/
    @OneToMany(fetch=EAGER, cascade=CascadeType.ALL)
    private List<TerminalButton> terminalButtons;
    
    /*
    public static EQueue getInstance(){
        if(instance == null){
            synchronized(EQueue.class){
                if(instance == null){
                    instance = new EQueue();
                }
            }
        }
        return instance;
    }*/
        
    
    public EQueue(){
        //queue = new LinkedList();
        queue = new  CopyOnWriteArrayList();
        //queue = new ConcurrentLinkedQueue();
        users = new CopyOnWriteArrayList();
        terminalButtons = new CopyOnWriteArrayList();        
        this.resourceName = "config.properties";
        //this.properties = initProperties();
        System.out.println("Constructor " + ++i);
    }
        
    public synchronized String getRunningString() {
        return runningString;
    }

    public synchronized void setRunningString(String runningString) {
        this.runningString = runningString;
    }
    
    public synchronized String getCompanyName(){
        return companyName;
    }
    
    public synchronized void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    
    // Working with terminal buttons block
    
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
        return terminalButtons.stream().filter(s -> s.getButtonId() == buttonId).findFirst().orElse(null);
        /*
        System.out.println("****************************************** button " + b.getName() + b.getButtonId());
        for(TerminalButton button : terminalButtons){
            if(button.getButtonId() == buttonId){
                System.out.println("****************************************** button " + button.getName() + button.getButtonId());
                return button;
            }           
        }
        return null;*/
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
    
    //End block
    
    // Working with queueu block
    
     public synchronized void addTicket(Ticket ticket){
        queue.add(0, ticket);
        System.out.println("****************************** QUEUE ADD TICKET " + queue);
        //boolean offer = ((ArrayDeque) queue).offer(ticket);
        //EQueueDB.addTicket(ticket);
        //boolean offer = queue.offer(ticket);
        //return offer;
        //return true;
    }
    
    public synchronized Ticket getFirstTicket(){
        return queue.remove(0);
        //return (Ticket) ((ArrayDeque)queue).poll();
        //return null;
    }
    
    public synchronized int getQueueLength(){
        return queue.size();
        //return (int) queue.stream().map(Ticket::getStatus).filter(TicketStatus.INQUEUE::equals).count();
        /*
        int i = 0;
        for(Ticket t : queue){
            if(t.getStatus() == TicketStatus.INQUEUE)
                i++;
        }*/
        //return i;
    }
    
    
    public synchronized String[] getQueueTickets(){
        return (String[])queue.toArray();
        //return (String[])queue.toArray();
        //return null;
    }
    
    //End block
    
    //Working with users block
    
    public synchronized List<EQueueUser> getUsers(){
        //users = EQueueDB.getUsers();
        //System.out.println("*************** USERS LIST BEGIN ");
        //System.out.println("*************** USERS LIST GET " + EQueueDB.getAdmins());
        //users.clear();
        //users.addAll(EQueueDB.getUsers());
        return users;
    }
    
    public synchronized void addUser(EQueueUser user){
        users.add(user);
        //EQueueDB.addUser(user);
        /*if(users.add(user))
            EQueueDB.addUser(user);
        System.out.println("*************** USERS addUser LIST GET " + EQueueDB.getUsers());
        */
    }
    
    public synchronized EQueueUser getUser(int number){        
        return users.get(number);
    }
    
    public synchronized EQueueUser getUser(EQueueUser user){
        int i = users.indexOf(user);
        if(i != -1)
            return users.get(i);
        else
            return null;
    }
    
    public synchronized EQueueUser findUserById(long userId){
        //return EQueueDB.findUserById(userId);
        for(EQueueUser user : users){
            if(user.getUserId() == userId){
                return user;
            }           
        }
        return null;
    }
    
    public synchronized EQueueUser getUserByIdAndRole(long userId, UserRole userRole){        
        for(EQueueUser user : users){
            if(user.getUserId() == userId && user.getUserRole() == userRole){
                return user;
            }           
        }
        return null;
    }
    
    public synchronized EQueueUser getUserByLogin(String login){
        return users.stream().filter(s -> s.getLogin().equals(login)).findFirst().orElse(null);
    }
    
    public synchronized boolean deleteUser(EQueueUser user){
        //EQueueDB.deleteUser(user);
        return users.remove(user);
    }
    
    public synchronized boolean deleteUser(long userId){
        //EQueueDB.deleteUser(EQueueDB.findUserById(userId));
        boolean isDelete = false;
        for(EQueueUser user : users){
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
    
    public synchronized boolean isUniqueLogin(String login){
        return users.stream().map(s -> s.getLogin()).noneMatch(login::equals);
    }
    
    //End block
    
    //Working with properties block
    
    public synchronized Properties initProperties(){         
        /*
        Properties props = new Properties();        
        try(BufferedReader fr = new BufferedReader(new FileReader(resourceName))){
            props.load(fr);            
        } catch (IOException ex) {
            Logger.getLogger(InitEQueueListener.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return props;
        */
        
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException ex) {
            Logger.getLogger(InitEQueueListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.properties = props;
        return props;
        
    }
        
    public synchronized void setProperties(Properties properties){
        this.properties = properties;
        
        /*
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        
        try(OutputStream out = new FileOutputStream(new File(loader.getResource(resourceName).getFile()))){
            this.properties.store(out, "Equeue app config"); 
            System.out.println("************************************************ SAVE");
        } catch (IOException ex) {
            Logger.getLogger(EQueue.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("************************************************ EXCEPTION");
        }
        */
        /*this.properties = properties;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(resourceName))){
            this.properties.store(bw, "EQueue properties file");            
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(EQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EQueue.class.getName()).log(Level.SEVERE, null, ex);
        }    */    
       
    }
        
    public synchronized Properties getProperties(){
        return properties;
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
