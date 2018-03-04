package com.michaelkatan.midproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MichaelKatan on 03/03/2018.
 */

public class Person implements Serializable
{
    String _name;
    String _phone;
    String _email;
    String _website;
    String _adress;

    transient Bitmap pic;
    Date date;
    Time time;
    String[] days;


    public Person(String _name, String _phone, String _email, String _website,
                  String _adress, Bitmap pic, Date date, Time time,String[] days) {
        this._name = _name;
        this._phone = _phone;
        this._email = _email;
        this._website = _website;
        this._adress = _adress;
        this.pic = pic;
        this.date = date;
        this.time = time;
        this.days = days;

    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException
    {
        pic.compress(Bitmap.CompressFormat.JPEG,50,stream);
        stream.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        pic = BitmapFactory.decodeStream(stream);
        stream.defaultReadObject();
    }

}
