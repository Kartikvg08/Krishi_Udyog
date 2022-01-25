package com.example.krishiudyog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class ConsumerViewItem extends AppCompatActivity {

    int[] images={R.drawable.add,R.drawable.remove};
    String[] names={"Wheat","Rice"};
    String[] desc = {"Grade1","Grade2"};
    String[] price = {"200","200"};
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_view_item);
        ListView listview = (ListView)findViewById(R.id.list_view);
        CustomAdapter customAdapter=new CustomAdapter();
        listview.setAdapter(customAdapter);
        btn=findViewById(R.id.btn_plce_order);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumerViewItem.this,ShowCart.class);
                startActivity(intent);
            }
        });

    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.list,null);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.img_product);
            TextView textViewName = (TextView)convertView.findViewById(R.id.txt_product_name);
            TextView textViewDesc = (TextView)convertView.findViewById(R.id.txt_product_desc);
            TextView textViewPrice = (TextView)convertView.findViewById(R.id.txt_price);
            final ElegantNumberButton btn1 = (ElegantNumberButton)convertView.findViewById(R.id.txt_amt);


            Button btn2 = findViewById(R.id.addcart);


            imageView.setImageResource(images[position]);
            textViewName.setText(names[position]);
            textViewDesc.setText(desc[position]);
            textViewPrice.setText(price[position]);


            return convertView;
        }
    }

}