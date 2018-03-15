package com.michaelkatan.midproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ListOfPersons extends Activity {
    ArrayList<Person> list;
    MyAdapter myAdapter;

    RecyclerView recyclerView;
    GridLayoutManager mLayoutManager;

    ActionMode.Callback mActionModeCallback;

    int PersonPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personslist);


        list = new ArrayList<>();
        recyclerView = findViewById(R.id.my_recycler_view);

        mLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        final RecyclerAdapter adapter = new RecyclerAdapter(list);

        myAdapter = new MyAdapter(this, R.layout.mylistitem, list);
        recyclerView.setAdapter(adapter);

        updateArr();

        adapter.setMyClickListener(new RecyclerAdapter.myClicker() {
            @Override
            public void onPersonClick(View view, int position)
            {
                Person person = list.get(position);
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
                    list.remove(pos);
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
                        list.remove(PersonPos);
                        adapter.notifyItemRemoved(PersonPos);
                        mode.finish();
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
