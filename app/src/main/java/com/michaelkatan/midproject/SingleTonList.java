package com.michaelkatan.midproject;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.ObjectInputStream;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by MichaelKatan on 20/03/2018.
 */

public class SingleTonList
{
    private static SingleTonList instance = null;
    ArrayList<Person> personArrayList;
    private static Context context;

    private SingleTonList()
    {
        personArrayList = new ArrayList<>();
        updatePersonsList();
    }

    private void updatePersonsList()
    {
        Person p;

        try {
            FileInputStream inputStream = context.openFileInput("persons");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            while ((p = (Person) objectInputStream.readObject())!= null)
            {
                personArrayList.add(p);
            }

            objectInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static SingleTonList getInstance(Context inContext)
    {
        context = inContext;
        if(instance == null)
        {
            instance = new SingleTonList();
        }

        return instance;
    }

    public static SingleTonList getInstance()
    {
        if(instance == null)
        {
            instance = new SingleTonList();
        }

        return instance;
    }

    public void instartToList(Person person)
    {
        personArrayList.add(person);
    }

    public void removeFromList(Person person)
    {
        personArrayList.remove(person);
    }

    public void removeFromList(int pos)
    {
        personArrayList.remove(pos);
    }

    public void updateFile()
    {
        try {
            FileOutputStream outputStream = context.openFileOutput("persons",MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);

            for(Person p: personArrayList)
            {
                stream.writeObject(p);
            }

            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }

    public Person getPerson(int pos)
    {
        Person p;
        p = personArrayList.get(pos);

        return p;
    }
}
