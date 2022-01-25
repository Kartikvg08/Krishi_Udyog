package com.example.krishiudyog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Trial extends AppCompatActivity {

    TextView tv1,tv2;
    ImageView add,sub;
    Button btn;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        add=findViewById(R.id.add);
        sub=findViewById(R.id.sub);

    }
    public void incval(View view)
    {
        count++;
        if(tv2!=null)
            tv2.setText(Integer.toString(count));
    }
    public void decval(View view)
    {
        count--;
        if(tv2!=null)
            tv2.setText(Integer.toString(count));
    }

}