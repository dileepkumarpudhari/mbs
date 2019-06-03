
import java.sql.Time;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DileepKumar
 */
public class WorkHoursBean {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getClockin() {
        return clockin;
    }

    public void setClockin(Timestamp clockin) {
        this.clockin = clockin;
    }

    public Timestamp getClockout() {
        return clockout;
    }

    public void setClockout(Timestamp clockout) {
        this.clockout = clockout;
    }

    public Time getHrsworked() {
        return hrsworked;
    }

    public void setHrsworked(Time hrsworked) {
        this.hrsworked = hrsworked;
    }

   
    private int ID;
    private String email;
    private Timestamp clockin;
    private Timestamp clockout;
    private Time hrsworked;
    
    
    
    
    
}
