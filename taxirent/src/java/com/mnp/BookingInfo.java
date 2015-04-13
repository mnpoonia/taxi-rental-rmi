/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnp;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author mnpoonia
 */
public class BookingInfo implements Serializable {
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getFrom_Addr() {
        return from_Addr;
    }

    public void setFrom_Addr(String from_Addr) {
        this.from_Addr = from_Addr;
    }

    public String getTo_addr() {
        return to_addr;
    }

    public void setTo_addr(String to_addr) {
        this.to_addr = to_addr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    private String mob_no;
    private String from_Addr;
    private String to_addr;
    private Date date;
    private String email;
}
