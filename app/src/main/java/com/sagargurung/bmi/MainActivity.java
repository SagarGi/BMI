package com.sagargurung.bmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button calculate1;
    Button calculate3;
    Button calculate4;
    Button calculate2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean flag = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Button calculate1;
        calculate1 = findViewById(R.id.calculate1);
        calculate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Calculation.class);
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

        if(flag==true)
        {
            onBackPressed();
        }

       // Button calculate3;
        calculate3 = findViewById(R.id.calculate3);
        calculate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutapp();

            }
        });

       // Button calculate4;
        calculate4 = findViewById(R.id.calculate4);
        calculate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,description.class);
                overridePendingTransition(0, 0);
                //overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

        calculate2 = findViewById(R.id.calculate2);
        gotoHistory();


    }


    public void onBackPressed()
    {
         boolean flag=true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void aboutapp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.about_app,null);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.setView(view);
        builder.show();


    }
    public void gotoHistory()
    {
        calculate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,history.class);
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });
    }

}
