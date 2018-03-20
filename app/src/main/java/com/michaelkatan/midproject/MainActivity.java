package com.michaelkatan.midproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{
    Button newPerson;
    Button allPersons;
    SingleTonList listOfpersons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newPerson = findViewById(R.id.addProduct);
        allPersons = findViewById(R.id.allProducts);


        newPerson.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,AddNewPerson.class);
                startActivity(intent);
            }
        });

        allPersons.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ListOfPersons.class);
                startActivity(intent);

            }
        });

        listOfpersons = SingleTonList.getInstance(this);
    }
}
