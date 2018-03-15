package com.michaelkatan.midproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MichaelKatan on 11/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
{
    List<Person> personList;
    Context context;

    myClicker myClickListener;

    public RecyclerAdapter(List<Person> list)
    {
        //this.context = context;
        this.personList = list;
    }

    public void setMyClickListener(myClicker listener)
    {
        this.myClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylistitem,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Person person = new Person();
        person._name = personList.get(position)._name;
        person.pic = personList.get(position).pic;

        holder.nameTv.setText(person._name);
        holder.imageView.setImageBitmap(person.pic);
    }

    @Override
    public int getItemCount()
    {
        return personList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTv;
        ImageView imageView;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            nameTv = itemView.findViewById(R.id.list_nameTV);
            imageView = itemView.findViewById(R.id.list_imageView);

            itemView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    myClickListener.onPersonClick(v,getAdapterPosition());

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v)
                {
                    myClickListener.onPersonLongClick(v,getAdapterPosition());
                    return true;
                }
            });

        }
    }

    public interface myClicker
    {
        void onPersonClick(View view, int position);
        void onPersonLongClick(View view, int position);

    }

}
