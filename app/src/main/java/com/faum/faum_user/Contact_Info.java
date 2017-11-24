package com.faum.faum_user;

/**
 * Created by arsal on 03-Oct-17.
 */

public class Contact_Info {
    private String Landline,Cell,RAddress;

    public Contact_Info(){

    }

    public Contact_Info(String land,String cel, String addr){
        this.Cell = cel;
        this.Landline = land;
        this.RAddress = addr;
    }


    public String getLandline() {
        return Landline;
    }

    public String getCell() {
        return Cell;
    }

    public String getRAddress() {
        return RAddress;
    }
}


