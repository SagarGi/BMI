package com.sagargurung.bmi;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        TextView heading1;
        heading1 = findViewById(R.id.heading1);
        heading1.setPaintFlags(heading1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView findmoreonweb;
        findmoreonweb = findViewById(R.id.findmoreonweb);
        findmoreonweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                browse(v);
            }
        });


        //back button
        ImageView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void browse(View view)
    {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://healthengine.com.au/info/bmi-body-mass-index"));
        startActivity(browse);
    }


}
