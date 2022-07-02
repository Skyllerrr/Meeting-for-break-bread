package com.example.sns_test;

public class booking_seho {
    String Name;
    String title;
    String number;
    String current_number;
    String time;
    String email;

    booking_seho(){}

    booking_seho(String Name,String title, String current_number, String number, String time,String email){
        this.Name= Name;
        this.title = title;
        this.number = number;
        this.current_number = current_number;
        this.time = time;
        this.email=email;
    }
    public String getEmail() {return  email;}
    public String getName() {return Name;}
    public String getTitle() {return title;}
    public String getNumber() {return number;}
    public String getCurrent_number(){return current_number;}
    public String getTime(){return time;}
}
