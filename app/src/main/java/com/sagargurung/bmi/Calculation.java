package com.sagargurung.bmi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.CalendarContract;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculation extends AppCompatActivity {
    TextInputLayout entername;
    TextInputLayout enterage;
    //TextInputLayout enterheight;
    TextInputLayout enterweight;
    TextInputEditText eweight;
    Button calculatebmi;
    Spinner feet;
    Spinner inch;
    int feetposition;
    int inchposition;
    Button save,cancel;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        ImageView back1;
        back1 = findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //text input layout
        entername = findViewById(R.id.entername);
        enterage = findViewById(R.id.enterage);
        //enterheight = findViewById(R.id.enterheight);
        enterweight = findViewById(R.id.enterweight);
        calculatebmi = findViewById(R.id.calculatebmi);
        eweight = findViewById(R.id.eweight);

        feet = findViewById(R.id.feet);
        inch = findViewById(R.id.inch);

        List<String> feetlist= new ArrayList<String>();
        feetlist.add("Feet");
        feetlist.add("1 ft");
        feetlist.add("2 ft");
        feetlist.add("3 ft");
        feetlist.add("4 ft");
        feetlist.add("5 ft");
        feetlist.add("6 ft");
        feetlist.add("7 ft");
        feetlist.add("8 ft");
        feetlist.add("9 ft");
        feetlist.add("10 ft");
        feetlist.add("11 ft");
        feetlist.add("12 ft");
        feetlist.add("13 ft");
        feetlist.add("14 ft");
        feetlist.add("15 ft");

        ArrayAdapter<String> arrayAdapterFeet = new ArrayAdapter<String>(Calculation.this,android.R.layout.simple_spinner_item,feetlist);
        arrayAdapterFeet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feet.setAdapter(arrayAdapterFeet);
        feet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feet.setSelection(position);
                feetposition = feet.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> inchlist= new ArrayList<String>();

        inchlist.add("Inch");
        inchlist.add("0''");
        inchlist.add("1''");
        inchlist.add("2''");
        inchlist.add("3''");
        inchlist.add("4''");
        inchlist.add("5''");
        inchlist.add("6''");
        inchlist.add("7''");
        inchlist.add("8''");
        inchlist.add("9''");
        inchlist.add("10''");
        inchlist.add("11''");
        inchlist.add("12''");

        ArrayAdapter<String> arrayAdapterInch = new ArrayAdapter<String>(Calculation.this,android.R.layout.simple_spinner_item,inchlist);
        arrayAdapterInch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inch.setAdapter(arrayAdapterInch);
        inch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                inch.setSelection(position);
                inchposition = inch.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    private boolean validateName()
    {
        final String name = entername.getEditText().getText().toString().trim();
        if(name.isEmpty())
        {
            entername.setError("Please Enter Name!");
            return false;

        }
        else if(name.length()>10)
        {
            entername.setError("Name too long!");
            return false;
        }
        else
        {
            entername.setError(null);
            entername.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validateAge()
    {
        final String age = enterage.getEditText().getText().toString().trim();
        if(age.isEmpty())
        {
            enterage.setError("Please Enter Age!");
            return false;

        }

        else
        {
            enterage.setError(null);
            return true;
        }
    }


    private boolean validateWeight()
    {
        final String weight = enterweight.getEditText().getText().toString().trim();
        if(weight.isEmpty())
        {
            enterweight.setError("Please Enter Weight!");
            return false;

        }

        else
        {
            enterweight.setError(null);
            return true;
        }
    }

    private boolean validateFeet()
    {
        if(feetposition == 0 && inchposition == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Calculation.this);
            builder.setMessage("Please select Feet and Inch!")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        else if(feetposition == 0)
        {
             AlertDialog.Builder builder = new AlertDialog.Builder(Calculation.this);
            builder.setMessage("Please select Feet!")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        else if(inchposition == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Calculation.this);
            builder.setMessage("Please select Inch!")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }

        else
        {
            return true;
        }

    }

    public void confirmCalculate(final View view)
    {
        if(!validateName() | !validateAge() | !validateWeight())
        {
            return;
        }
        else if(!validateFeet())
        {
            return;
        }

        else {

            final Dialog dialog = new Dialog(Calculation.this);
            dialog.setContentView(R.layout.result);
            dialog.setCancelable(false);
            dialog.show();



            Button save;
            Button cancel;
            TextView tvbmi;
            TextView tvname , tvheight , tvage , tvweight;
            final ImageView viewimage;


            save = dialog.findViewById(R.id.save);
            cancel = dialog.findViewById(R.id.cancel);
            tvbmi = dialog.findViewById(R.id.tvbmi);
            tvage = dialog.findViewById(R.id.tvage1);
            tvheight = dialog.findViewById(R.id.tvheight);
            viewimage = dialog.findViewById(R.id.viewimage);

            tvweight = dialog.findViewById(R.id.tvweight);

            tvname = dialog.findViewById(R.id.tvname);

            final String fname = entername.getEditText().getText().toString().trim();
            final String age = enterage.getEditText().getText().toString().trim();
            final String weight = enterweight.getEditText().getText().toString().trim();


            final int fage = Integer.parseInt(age);
            //int fheight = Integer.parseInt(height);
           final  int fweight = Integer.parseInt(weight);

            final float bmiresult;
            float feetcentimeters;
            float inchcentimeters;
            final float totalmeter;
            feetcentimeters = (float)feetposition * (float)30.48;
            inchcentimeters = (float) (inchposition-1) *(float) 2.54;
            totalmeter = (feetcentimeters + inchcentimeters) / 100;
            bmiresult = fweight/(totalmeter*totalmeter);
            //image view;
            if(bmiresult<18.5)
            {
                viewimage.setImageResource(R.drawable.underweight);
            }
            else if(bmiresult >= 18.5 && bmiresult <= 24.9)
            {
                viewimage.setImageResource(R.drawable.healthyweight);
            }
            else if(bmiresult >= 25.0 && bmiresult <= 29.9)
            {
                viewimage.setImageResource(R.drawable.overweight);
            }
            else if(bmiresult >= 30.0 && bmiresult <= 34.9)
            {
                viewimage.setImageResource(R.drawable.obeseclass1);
            }
            else {
                viewimage.setImageResource(R.drawable.obeseclass2);
            }




            //cancel button

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            //text view part
            tvname.setText("Hello\t"+ fname + ",");
            tvage.setText("Age\t:\t" +String.valueOf(fage)+"\tyears");
            tvheight.setText("Height\t:\t"+feetposition+"\tft\t\t"+(inchposition-1)+"\t''\t");
            tvweight.setText("Weight\t:\t"+String.valueOf(fweight)+"\tKG");
            tvbmi.setText("Your BMI is\t:\t" + new DecimalFormat("##.##").format(bmiresult)+"\tkg/m2");


            //save button

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper mydb = new DatabaseHelper(Calculation.this);
                    boolean isInserted = mydb.insertData(fname, fage, totalmeter, fweight, bmiresult);
                    if (isInserted == true) {
                        dialog.dismiss();
                        Toast.makeText(Calculation.this, "Information Saved!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Calculation.this, "Information Not Saved!", Toast.LENGTH_SHORT).show();


                    }
                }
            });




        }


    }
}
