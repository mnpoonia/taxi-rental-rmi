/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnp;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mnpoonia
 */
public class CabBook extends UnicastRemoteObject implements bookrmi {

    @Override
    public String sayMessage(String msg) throws RemoteException {
        String mm = msg;
        return mm;
    }

    static int no_of_cars = 3;

    public CabBook() throws RemoteException {
        super();
    }

    @Override
    public synchronized int bookCab(BookingInfo info) throws RemoteException {
        int book_id = -1;
        Connection con = null;
        try {
            boolean avail = checkAvailablity(info.getDate());
            if (avail) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //con = DriverManager.getConnection("jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/cab_bookings", "admin3cF6Yrv", "sbG8tuQifdBw");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab_bookings", "root", "root123");
                String sql = "INSERT INTO `cab_bookings`.`bookings` "
                        + "(`id`, `mob_no`, `email`, `from_addr`, `to_addr`, `date`) "
                        + "VALUES (NULL,'"
                        + info.getMob_no() + "','"
                        + info.getEmail() + "','"
                        + info.getFrom_Addr() + "','"
                        + info.getTo_addr() + "','"
                        + info.getDate() + "'"
                        + ")";
                Statement st = con.createStatement();
                System.out.println(sql);
                boolean execute = st.execute(sql);
                book_id=1;
            } else {
                book_id = -1;
            }

        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(CabBook.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(con!=null)
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CabBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return book_id;
    }

    @Override
    public synchronized boolean checkAvailablity(Date d) throws RemoteException {
        boolean avail = false;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //con = DriverManager.getConnection("jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/cab_bookings", "admin3cF6Yrv", "sbG8tuQifdBw");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab_bookings", "root", "root123");
            PreparedStatement st1 = con.prepareStatement("select count(*) as cnt from bookings where date = '" + d + "'");
            ResultSet res = st1.executeQuery();
            if (res.next()) {
                int count = res.getInt("cnt");
                if (count < CabBook.no_of_cars) {
                    avail = true;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CabBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CabBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CabBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CabBook.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CabBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return avail;
    }
    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            // create a new service named myMessage
            registry.rebind("cabbook", new CabBook());

            System.out.println("server started");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }
}
