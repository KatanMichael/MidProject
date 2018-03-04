package com.michaelkatan.midproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by MichaelKatan on 04/03/2018.
 */

public class ShowPerson extends Activity
{
    TextView name;
    TextView email;
    TextView phone;
    TextView adress;
    TextView website;

    ImageView imageView;

    Person p;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_pesron);

        name = findViewById(R.id.person_nameTV);
        email = findViewById(R.id.person_emailTV);
        phone = findViewById(R.id.person_phoneTV);
        adress = findViewById(R.id.person_adressTV);
        website = findViewById(R.id.person_webTV);

        imageView = findViewById(R.id.person_pic);

        p = (Person) getIntent().getExtras().get("person");


        name.setText(p._name);
        email.setText(p._email);
        phone.setText(p._phone);
        adress.setText(p._adress);
        website.setText(p._website);
        imageView.setImageBitmap(p.pic);


    }
}
