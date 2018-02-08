package com.faum.faum_rider;

/**
 * Created by arsal on 01-Oct-17.
 */

public class User_Infromation {

    private String firstname,lastname;


    public User_Infromation(){

    }



    public User_Infromation(String fname,String lname){
        this.firstname = fname;
        this.lastname =lname;

    }




    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
        return firstname;
    }


}
