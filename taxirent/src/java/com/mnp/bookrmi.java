/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnp;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

/**
 *
 * @author mnpoonia
 */
public interface bookrmi extends Remote {
    
    public int bookCab(BookingInfo info) throws RemoteException;
    
    public boolean checkAvailablity(Date d) throws RemoteException;
    
    public String sayMessage(String msg) throws RemoteException;
}
