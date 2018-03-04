package com.michaelkatan.midproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MichaelKatan on 28/02/2018.
 */

public class AddNewPerson extends Activity
{

    final int PICTURE_REQUSEST_CODE = 1;

    EditText fullNameET;
    EditText phoneNumberET;
    EditText emailET;
    EditText homeET;
    EditText websiteET;

    Button birthPickBtn;
    Button timeCallBtn;
    Button bestDaysBtn;
    Button pictureBtn;
    Button savePersonBtn;

    TextView datePickTV;
    TextView timePickTV;
    TextView daysPickTV;

    ImageView imageView;

    Person person;
    Date date;
    Time time;
    Bitmap pic;

    ArrayList<Person> personArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newperson_layout);

        getReferences();
        updateArr();

        pictureBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent,PICTURE_REQUSEST_CODE);

            }
        });

        birthPickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog dialog = new DatePickerDialog(AddNewPerson.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        date = new Date(year,month,dayOfMonth);
                        datePickTV.setText(date.getDate()+"/"+date.getMonth()+"/"+date.getYear());
                    }
                },2018,1,1);

                dialog.show();

            }
        });

        timeCallBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog dialog = new TimePickerDialog(AddNewPerson.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        time = new Time(hourOfDay,minute,0);
                        timePickTV.setText(hourOfDay+":"+minute);

                    }
                },0,0,true);

                dialog.show();

            }
        });



        bestDaysBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


            }
        });


        savePersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String tempName = fullNameET.getText().toString();
                String phone = phoneNumberET.getText().toString();
                String email = emailET.getText().toString();
                String webSite = websiteET.getText().toString();
                String address = homeET.getText().toString();
                String[] days = {"Sunday","Monday"};

                Person person = new Person(tempName,phone,email,
                        webSite,address,pic,date,time,days);


                personArrayList.add(person);

                try
                {
                    FileOutputStream outputStream = openFileOutput("persons", MODE_PRIVATE);
                    ObjectOutputStream stream = new ObjectOutputStream(outputStream);

                    for(int i = 0; i < personArrayList.size(); i++)
                    {
                        stream.writeObject(personArrayList.get(i));
                    }

                    stream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void updateArr()
    {
        try
        {
            FileInputStream inputStream = openFileInput("persons");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Person p;

            while((p = (Person) objectInputStream.readObject())!= null)
            {
                personArrayList.add(p);
            }

            objectInputStream.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == PICTURE_REQUSEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {

                Bundle extras = data.getExtras();
                pic = (Bitmap) extras.get("data");
                imageView.setImageBitmap(pic);
            }
        }


    }

    private void getReferences()
    {
        fullNameET = findViewById(R.id.new_nameET);
        phoneNumberET = findViewById(R.id.new_phoneET);
        emailET = findViewById(R.id.new_emailET);
        homeET = findViewById(R.id.new_adressET);
        websiteET = findViewById(R.id.new_webET);

        birthPickBtn = findViewById(R.id.new_birthBtn);
        timeCallBtn = findViewById(R.id.new_timeCallBtn);
        bestDaysBtn = findViewById(R.id.new_daysBtn);
        pictureBtn = findViewById(R.id.new_picBtn);
        savePersonBtn = findViewById(R.id.new_saveBtn);

        datePickTV = findViewById(R.id.new_birthTv);
        timePickTV = findViewById(R.id.new_timeCallTv);
        daysPickTV = findViewById(R.id.new_daysTv);

        imageView = findViewById(R.id.new_image);

        date = new Date();

        personArrayList = new ArrayList<>();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
