package com.michaelkatan.midproject;

import android.app.Activity;
import android.app.DatePickerDialog;
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

    Person person = new Person();
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newperson_layout);

        getReferences();

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






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == PICTURE_REQUSEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Bitmap temp;

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
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
    }
}
