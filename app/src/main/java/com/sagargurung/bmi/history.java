package com.sagargurung.bmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class history extends AppCompatActivity {
    ListView listview;
    ImageView backhistory;
    ArrayList<GetterSetter> informationlist;
    com.sagargurung.bmi.ListAdapter adapter;
    DatabaseHelper mydb;
    Button clearall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        backhistory = findViewById(R.id.backhistory);
        clickBack();
        listview = findViewById(R.id.listview);
        informationlist = new ArrayList<>();
        adapter = new com.sagargurung.bmi.ListAdapter(this, informationlist);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //get all data from the sqlite;
        mydb = new DatabaseHelper(this);
        Cursor res = mydb.getAllData();

        while (res.moveToNext()) {
            int id = res.getInt(0);
            String name = res.getString(1);
            Integer age = res.getInt(2);
            float height = res.getFloat(3);
            Integer weight = res.getInt(4);
            float bmi = res.getFloat(5);
            //list.add(new GetterSetter(id,name,String.valueOf(age),height,weight,bmi));
            GetterSetter gs = new GetterSetter(id, name, age, height, weight, bmi);
            informationlist.add(gs);
        }
        clearall = findViewById(R.id.clearall);
        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb = new DatabaseHelper(history.this);
                Cursor res = mydb.getAllData();
                if(!res.moveToNext())
                {
                    Toast.makeText(history.this,"No Information Available to Delete!",Toast.LENGTH_SHORT).show();
                }
                else {

                    new AlertDialog.Builder(history.this)
                            .setIcon(android.R.drawable.ic_delete)
                            .setTitle("Delete All History")
                            .setCancelable(false)
                            .setMessage("Are you sure you want to delete?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mydb.deleteAll();
                                    Intent intent = getIntent();
                                    overridePendingTransition(0, 0);
                                    finish();
                                    startActivity(intent);
                                    overridePendingTransition(0, 0);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
                //mydb.deleteAll();
            }
        });

    }



        //back pressed on the history screen
        public void clickBack()
        {
            backhistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        // inserting data in the listview


}

