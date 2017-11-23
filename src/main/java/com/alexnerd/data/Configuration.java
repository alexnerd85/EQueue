/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.data;

import com.alexnerd.data.users.Operator;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 *   @Created on : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

public class Configuration implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //Название организации
    private String companyName;
    
    //Бегущая строка
    private String runningString;
    
    //Список кнопок в терминале
    private List<TerminalButton> terminalButtons;
    
    //Список операторов
    private List<Operator> operators;
    
    public Configuration(){
        companyName = "Камчатский краевой кардиологический диспансер";
        terminalButtons = Arrays.asList(
                new TerminalButton("Терапевт","Т", 3, true),
                new TerminalButton("Невролог","Н", 4, true),
                new TerminalButton("Хирург","Х", 2, true),
                new TerminalButton("Стоматолог","С", 0, false)
                );
        operators = Arrays.asList(
               /* new Operator(true,1),
                new Operator(false,2),
                new Operator(true,3)*/
                );        
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
    
    public synchronized List<Operator> getOperators(){
        return operators;
    }
    
    public synchronized void addOperator(Operator operator){
        operators.add(operator);
    }
    
    public synchronized boolean deleteOperator(Operator operator){
        return operators.remove(operator);
    }
}
