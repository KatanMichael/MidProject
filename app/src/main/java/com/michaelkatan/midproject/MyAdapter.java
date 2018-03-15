package com.michaelkatan.midproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MichaelKatan on 04/03/2018.
 */

public class MyAdapter extends ArrayAdapter<Person>
{
    private Context context;
    private int resource;
    private List<Person> personList;

    public MyAdapter(Context myContext, int resource, List<Person> myList)
    {
        super(myContext,resource,myList);

        this.context = myContext;
        this.resource = resource;
        this.personList = myList;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null);

//        TextView nameTV = view.findViewById(R.id.list_nameTV);
//        TextView emailTV = view.findViewById(R.id.list_emailTV);
//        TextView phoneTV = view.findViewById(R.id.list_phoneTV);
//        TextView webTV = view.findViewById(R.id.list_webTV);
//        TextView adressTV = view.findViewById(R.id.list_adressTV);

        ImageView imageView = view.findViewById(R.id.list_imageView);

        Person p = personList.get(position);

//        nameTV.setText(p._name);
//        emailTV.setText(p._email);
//        phoneTV.setText(p._phone);
//        webTV.setText(p._email);
//        adressTV.setText(p._adress);

        imageView.setImageBitmap(p.pic);

        return view;

    }



}
