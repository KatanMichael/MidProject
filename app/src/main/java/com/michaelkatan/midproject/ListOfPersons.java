package com.michaelkatan.midproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by MichaelKatan on 04/03/2018.
 */

public class ListOfPersons extends Activity {
    ListView listView;
    ArrayAdapter<Person> arrayAdapter;
    ArrayList<Person> list;
    MyAdapter myAdapter;

    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personslist);

        listView = findViewById(R.id.listView);
        list = new ArrayList<>();

        recyclerView = findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        //arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        // listView.setAdapter(arrayAdapter);

        myAdapter = new MyAdapter(this, R.layout.mylistitem, list);

        listView.setAdapter(myAdapter);
        recyclerView.setAdapter(arrayAdapter);

        updateArr();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(ListOfPersons.this, ShowPerson.class);
                Person p = list.get(position);
                intent.putExtra("person",p);
                startActivity(intent);
            }
        });

    }

    private void updateArr() {
        try {
            FileInputStream inputStream = openFileInput("persons");
            ObjectInputStream stream = new ObjectInputStream(inputStream);

            Person person;
            while ((person = (Person) stream.readObject()) != null) {
                list.add(person);
            }

            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onPause() {
        try {
            FileOutputStream outputStream = openFileOutput("persons", MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);

            for (int i = 0; i < list.size(); i++) {
                stream.writeObject(list.get(i));
            }

            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        super.onPause();
    }

}
