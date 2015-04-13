/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli.mnp;

import com.mnp.BookingInfo;
import com.mnp.bookrmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.NotBoundException;
import java.sql.Date;

/**
 *
 * @author mnpoonia
 */
public class RMIClient {

    bookrmi b=null;

    public int bookCab(BookingInfo info) {
        int no=-1;
        try {
            RMIClient cli = new RMIClient();
            bookrmi b = cli.initialize();
            no = b.bookCab(info);
            System.out.println("Done");
        } catch (RemoteException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getCause());
        }
        return no;
    }
    
    public bookrmi initialize() {
        try {
            Registry myReg = LocateRegistry.getRegistry(1099);
            b = (bookrmi) myReg.lookup("cabbook");
            //Class<? extends Remote> aClass = lookup.getClass();
            //System.out.println(aClass.getName());
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    } 
    public static void main(String[] args) throws RemoteException {
        String mob = "9876543210";
            String email = "aman@gmail.com";
            String from = "aasdsdsad";
            String to = "qwee rrrr";
            String date = "9";
            String mon = "3";
            String year = "2015";
            
            RMIClient cli = new RMIClient();
            bookrmi b = cli.initialize();
            System.out.println(b.sayMessage("aman poonia"));
            BookingInfo info = new BookingInfo();
            
            Date d = Date.valueOf(year + "-" + mon + "-" + date);
            info.setMob_no(mob);
            info.setEmail(email);
            info.setFrom_Addr(from);
            info.setTo_addr(to);
            info.setDate(d);
            
            int id = cli.bookCab(info);
    }

}
