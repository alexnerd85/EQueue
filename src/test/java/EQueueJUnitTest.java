/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.alexnerd.data.users.Admin;
import com.alexnerd.data.EQueue;
import com.alexnerd.data.users.Operator;
import com.alexnerd.data.TerminalButton;
import com.alexnerd.ticket.Ticket;
import com.alexnerd.ticket.TicketPriority;
import com.alexnerd.ticket.TicketStatus;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Aleksey
 */
public class EQueueJUnitTest {
    EQueue equeue;
    
    @Before
    public void initialize(){
        equeue = new EQueue();
        equeue.addUser(new Operator("pupkin","123", "Пупкин", "Василий", "Васильевич", false));
        equeue.addUser(new Operator("kondr","123", "Кондратенко", "Андрей", "Аркадьевич", false));
        equeue.addUser(new Operator("mefodiy","123", "Мефодий", "Кирилл", "Афанасьевич", false));
        equeue.addUser(new Admin("admin","123","Иванов", "Иван", "Иванович"));
        
        equeue.addTicket(new Ticket(1,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));
        equeue.addTicket(new Ticket(2,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));
        equeue.addTicket(new Ticket(3,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));
        equeue.addTicket(new Ticket(4,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));        
                
        equeue.addTerminalButton(new TerminalButton("Терапевт","Т", 3, true));
        equeue.addTerminalButton(new TerminalButton("Невролог","Н", 4, true));
        equeue.addTerminalButton(new TerminalButton("Хирург","Х", 2, true));
        equeue.addTerminalButton(new TerminalButton("Стоматолог","С", 0, false));
    }
    
    public EQueueJUnitTest() {
    }
    
    @Test
    public void testGetUserByLogin(){        
        assertThat(equeue.getUserByLogin("kondr").getLogin(), is("kondr"));
    }
    
}
