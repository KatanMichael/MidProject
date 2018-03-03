package com.michaelkatan.midproject;

import android.graphics.Bitmap;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MichaelKatan on 03/03/2018.
 */

public class Person
{
    String _name;
    int _phoneNumber;
    int _price;
    String _email;
    String _adress;
    String _website;

    Date _birthDate;
    Time _callTime;
    ArrayList<String> _bestDays;
    Bitmap image;


    public Person(String _name, int _phoneNumber, int _price, String _email, String _adress,
                  String _website, Date _birthDate, Time _callTime, ArrayList<String> _bestDays, Bitmap image) {
        this._name = _name;
        this._phoneNumber = _phoneNumber;
        this._price = _price;
        this._email = _email;
        this._adress = _adress;
        this._website = _website;
        this._birthDate = _birthDate;
        this._callTime = _callTime;
        this._bestDays = _bestDays;
        this.image = image;
    }


    public Person() {}

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(int _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_adress() {
        return _adress;
    }

    public void set_adress(String _adress) {
        this._adress = _adress;
    }

    public String get_website() {
        return _website;
    }

    public void set_website(String _website) {
        this._website = _website;
    }

    public Date get_birthDate() {
        return _birthDate;
    }

    public void set_birthDate(Date _birthDate) {
        this._birthDate = _birthDate;
    }

    public Time get_callTime() {
        return _callTime;
    }

    public void set_callTime(Time _callTime) {
        this._callTime = _callTime;
    }

    public ArrayList<String> get_bestDays() {
        return _bestDays;
    }

    public void set_bestDays(ArrayList<String> _bestDays) {
        this._bestDays = _bestDays;
    }
}
