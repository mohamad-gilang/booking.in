package com.example.bookingin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class BookingData {

    private String name, phone, date, passenger, paket;

    public BookingData(String name, String phone, String date, String passenger, String paket) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.passenger = passenger;
        this.paket = paket;
    }

    public BookingData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

}