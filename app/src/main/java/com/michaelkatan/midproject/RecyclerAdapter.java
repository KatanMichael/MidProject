package com.michaelkatan.midproject;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MichaelKatan on 11/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<Person>
{

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v)
        {
            super(v);
            mTextView = v;
        }
    }


    @Override
    public Person onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(Person holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }
}
