package com.michaelkatan.midproject;

import java.io.Serializable;

/**
 * Created by MichaelKatan on 03/03/2018.
 */

public class Time implements Serializable
{
    int _hour;
    int _minutes;

    public Time(int _hour, int _minutes) {
        this._hour = _hour;
        this._minutes = _minutes;
    }

    public int get_hour() {
        return _hour;
    }

    public void set_hour(int _hour) {
        this._hour = _hour;
    }

    public int get_minutes() {
        return _minutes;
    }

    public void set_minutes(int _minutes) {
        this._minutes = _minutes;
    }
}
