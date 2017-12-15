/*
 *   Created on : 15.12.2017, 20:51:28
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.data;

import com.alexnerd.data.users.EQueueUser;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Aleksey
 */

@Entity
@Table(name="REQUESTLOG")
public class RequestLog implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="REMOTEADDR")
    private String remoteAddr;
    
    @Column(name="TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;
    
    @Column(name="METHODNAME")
    private String methodName;
    
    @Column(name="REQUESTURI")
    private String requestURI;
    
    @Column(name="PROTOCOLNAME")
    private String protocolName;
    
    @Column(name="STATUSCODE")
    private int statusCode;
    
    @Column(name="CONTENTLENGTH")
    private String length;
    
    @Column(name="TIMER")
    private String timer;
    
    @Column(name="EQUEUEUSER")
    private String user;
    
    public RequestLog(){
    }

    public RequestLog(String remoteAddr, LocalDateTime dateTime, 
            String methodName, String requestURI, String protocolName, 
            int statusCode, String length, String timer, EQueueUser user) {
        this.remoteAddr = remoteAddr;
        this.dateTime = dateTime;
        this.methodName = methodName;
        this.requestURI = requestURI;
        this.protocolName = protocolName;
        this.statusCode = statusCode;
        this.length = length;
        this.timer = timer;
        if(user != null)
            this.user = user.getUserRole() + " " + user.getLogin() + " [" + 
            user.getSirname() + " " + user.getName() + " " + 
            user.getMiddlename() + "]";
        else this.user = "unknown";
    }
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    
    public String getRemoteAddr() {
        return remoteAddr;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
    
    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getRequestURI() {
        return requestURI;
    }
    
    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolName() {
        return protocolName;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setLength(String length) {
        this.length = length;
    }
    
    public String getLength() {
        return length;
    }
    
    public void setTimer(String timer){
        this.timer = timer;
    }
    
    public String getTimer(){
        return timer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.remoteAddr);
        hash = 89 * hash + Objects.hashCode(this.dateTime);
        hash = 89 * hash + Objects.hashCode(this.methodName);
        hash = 89 * hash + Objects.hashCode(this.requestURI);
        hash = 89 * hash + Objects.hashCode(this.protocolName);
        hash = 89 * hash + this.statusCode;
        hash = 89 * hash + Objects.hashCode(this.length);
        hash = 89 * hash + Objects.hashCode(this.timer);
        hash = 89 * hash + Objects.hashCode(this.user);
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
        final RequestLog other = (RequestLog) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.statusCode != other.statusCode) {
            return false;
        }
        if (!Objects.equals(this.remoteAddr, other.remoteAddr)) {
            return false;
        }
        if (!Objects.equals(this.methodName, other.methodName)) {
            return false;
        }
        if (!Objects.equals(this.requestURI, other.requestURI)) {
            return false;
        }
        if (!Objects.equals(this.protocolName, other.protocolName)) {
            return false;
        }
        if (!Objects.equals(this.length, other.length)) {
            return false;
        }
        if (!Objects.equals(this.timer, other.timer)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return user + " " + remoteAddr + " - - [" + dateTime +"]" +
                    "\"" + methodName + " " + requestURI + " " + 
                    protocolName + "\" " + statusCode + " " + length + timer;
    }
}