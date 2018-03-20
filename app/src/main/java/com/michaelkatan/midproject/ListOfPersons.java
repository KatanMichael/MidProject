package com.michaelkatan.midproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

public class ListOfPersons extends Activity
{
    MyAdapter myAdapter;

    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;

    ActionMode.Callback mActionModeCallback;

    int PersonPos;

    SingleTonList listOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personslist);

        listOfPeople = SingleTonList.getInstance(this);

        recyclerView = findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager(mLayoutManager);
        final RecyclerAdapter adapter = new RecyclerAdapter(listOfPeople.getPersonArrayList());

        myAdapter = new MyAdapter(this, R.layout.mylistitem, listOfPeople.getPersonArrayList());
        recyclerView.setAdapter(adapter);


        adapter.setMyClickListener(new RecyclerAdapter.myClicker() {
            @Override
            public void onPersonClick(View view, int position)
            {
                Person person = listOfPeople.getPerson(position);
                Intent intent = new Intent(ListOfPersons.this,ShowPerson.class);
                intent.putExtra("person",person);

                startActivity(intent);

            }

            @Override
            public void onPersonLongClick(View view, int position)
            {
                view.startActionMode(mActionModeCallback);
                PersonPos = position;

            }
        });

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                if(direction == ItemTouchHelper.LEFT)
                {
                    int pos = viewHolder.getAdapterPosition();
                    listOfPeople.removeFromList(pos);
                    adapter.notifyItemRemoved(pos);
                }
            }
        };

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        mActionModeCallback = new ActionMode.Callback() {

            // Called when the action mode is created; startActionMode() was called
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.card_select, menu);
                return true;
            }

            // Called each time the action mode is shown. Always called after onCreateActionMode, but
            // may be called multiple times if the mode is invalidated.
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false; // Return false if nothing is done
            }

            // Called when the user selects a contextual menu item
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cardMenu_Edit:
                        {
                        Toast.makeText(ListOfPersons.this, "Edit", Toast.LENGTH_SHORT).show();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    }
                    case R.id.cardMenu_remove:
                    {
                        listOfPeople.removeFromList(PersonPos);
                        adapter.notifyItemRemoved(PersonPos);
                        mode.finish();
                        return  true;
                    }

                }
                return false;
            }


            // Called when the user exits the action mode
            @Override
            public void onDestroyActionMode(ActionMode mode)
            {

            }
        };

    }

    @Override
    protected void onPause()
    {
        listOfPeople.updateFile();
        super.onPause();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.card_select, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.cardMenu_Edit)
        {
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.cardMenu_remove)
        {
            Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }
}
