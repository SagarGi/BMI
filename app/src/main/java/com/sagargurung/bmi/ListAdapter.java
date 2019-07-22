package com.sagargurung.bmi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<GetterSetter> informationlist;

    public ListAdapter(Context context, ArrayList<GetterSetter> informationlist) {
        this.context = context;
        this.informationlist = informationlist;
    }

    @Override
    public int getCount() {
        return informationlist.size();
    }

    @Override
    public Object getItem(int position) {
        return informationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_list,parent,false);
           // convertView = inflater.inflate(R.layout.history_list,null);
           TextView idtv = convertView.findViewById(R.id.idtv);
           TextView nametv = convertView.findViewById(R.id.nametv);
           TextView agetv = convertView.findViewById(R.id.agetv);
          TextView  heighttv = convertView.findViewById(R.id.heighttv);
         TextView weighttv = convertView.findViewById(R.id.weighttv);
          TextView  bmitv = convertView.findViewById(R.id.bmitv);
         ImageView  viewimageresult = convertView.findViewById(R.id.imageviewresult);
            GetterSetter gs = informationlist.get(position);

            idtv.setText("ID \t\t\t\t:\t\t\t\t"+String.valueOf(gs.getId()));
            nametv.setText("Name \t:\t\t\t\t" + gs.getName());
            agetv.setText("Age \t\t\t:\t\t\t\t" + String.valueOf(gs.getAge()) + "\tYear");
            heighttv.setText("Height \t:\t\t\t\t" + new DecimalFormat("##.##").format(gs.getHeight())+ "\tmeter");
            weighttv.setText("Weight \t:\t\t\t\t" + String.valueOf(gs.getWeight()) + "\tKg");
            bmitv.setText("BMI \t\t\t:\t\t\t\t" + new DecimalFormat("##.##").format(gs.getBmi())+"\tKg/m2");

            //dynamic image
            float bmiresult1 = gs.getBmi();
            if(bmiresult1<18.5)
            {
                viewimageresult.setImageResource(R.drawable.underweight);
            }
            else if(bmiresult1 >= 18.5 && bmiresult1 <= 24.9)
            {
                viewimageresult.setImageResource(R.drawable.healthyweight);
            }
            else if(bmiresult1 >= 25.0 && bmiresult1 <= 29.9)
            {
                viewimageresult.setImageResource(R.drawable.overweight);
            }
            else if(bmiresult1 >= 30.0 && bmiresult1 <= 34.9)
            {
                viewimageresult.setImageResource(R.drawable.obeseclass1);
            }
            else {
                viewimageresult.setImageResource(R.drawable.obeseclass2);
            }




        }
        return convertView;
    }
}
