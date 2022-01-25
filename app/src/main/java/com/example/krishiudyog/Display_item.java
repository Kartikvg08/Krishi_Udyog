package com.example.krishiudyog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Display_item extends AppCompatActivity {

    int cost,qtity;
    ImageView display_image,ivdisplay,ivdisplaycall,displaylocation;
    TextView displayname,displayqty,tv_qty,displayprice,displaypname,tvmobile,tvrules,tvplace,displayquality;
    Button placeorder;
    EditText etqty;

    String Currentdate;
    Calendar calendar;
    String Producerid;
    String orderedqty;
    String Consumername;
    String Consumerno;
    String itemname;
    String producername;
    String quantity;
    String contactno;
    String price;
    String location;
    String quality;
    public Display_item(){}

    DatabaseReference orders,dbconsumers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        final SendNotif sn=new SendNotif();
        final Orders order=new Orders();

        display_image=findViewById(R.id.display_image);
        ivdisplay=findViewById(R.id.ivdisplay);
        ivdisplaycall=findViewById(R.id.ivdisplaycall);
        displaylocation=findViewById(R.id.displaylocation);
        displayname=findViewById(R.id.displayname);
        displayqty=findViewById(R.id.displayqty);
        tv_qty=findViewById(R.id.tv_qty);
        displayprice=findViewById(R.id.displayprice);
        displaypname=findViewById(R.id.displaypname);
        tvmobile=findViewById(R.id.tvmobile);
        tvplace=findViewById(R.id.tvplace);
        displayquality=findViewById(R.id.displayquality);
        placeorder=findViewById(R.id.placeorder);
        orders=FirebaseDatabase.getInstance().getReference("Orders");
        dbconsumers=FirebaseDatabase.getInstance().getReference("Consumers");
        etqty=findViewById(R.id.etqty);

        calendar=Calendar.getInstance();
        Currentdate= DateFormat.getDateInstance().format(calendar.getTime());


        assigndata();

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String orderid=orders.push().getKey();
                final String consumerid= FirebaseAuth.getInstance().getCurrentUser().getUid();

                orderedqty=etqty.getText().toString().trim();
                qtity=Integer.parseInt(orderedqty);
                System.out.println("qty: "+qtity);
                dbconsumers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                                Consumername= dataSnapshot.child(consumerid).child("fullname").getValue(String.class);
                                Consumerno=dataSnapshot.child(consumerid).child("phone").getValue(String.class);

                                order.setConsumername(Consumername);
                                order.setConsumerno(Consumerno);
                                order.setProducername(producername);
                                order.setOqlty(quality);
                                order.setOqty(orderedqty);
                                order.setRate(price);
                                order.setOpname(itemname);
                                order.setDate(Currentdate);

                                int amount=cost*qtity;
                                String amt=Integer.toString(amount);
                                order.setAmount(amt);
                                orders.child(orderid).setValue(order);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //sn.firebase(Producerid,Consumername,order);
                Toast.makeText(getApplicationContext(),"Item ordered Successfully!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),com.example.krishiudyog.ConsumerSearchItem.class));

            }
        });
        sn.UpdateToken();
    }

    public void assigndata(){

        this.itemname=getIntent().getStringExtra("productname");
        this.producername=getIntent().getStringExtra("producername");
        this.quantity=getIntent().getStringExtra("availablequantity");
        this.contactno=getIntent().getStringExtra("contactno");
        this.price=getIntent().getStringExtra("price");
        this.location=getIntent().getStringExtra("location");
        this.quality=getIntent().getStringExtra("quality");

        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();
        hashMap.put("ONION",R.mipmap.bigonion);
        hashMap.put("POTATO",R.mipmap.bigpotato);
        hashMap.put("CABBAGE",R.mipmap.bigcabbage);
        hashMap.put("RICE",R.mipmap.bigrice);
        hashMap.put("WHEAT",R.mipmap.bigwheat);
        hashMap.put("CAULIFLOWER",R.mipmap.bigcauliflower);
        hashMap.put("BRINJAL",R.mipmap.bigbrinjal);
        hashMap.put("CARROT",R.mipmap.bigcarrot);
        hashMap.put("RADISH",R.mipmap.bigradish);
        hashMap.put("GREEN BEANS",R.mipmap.biggreenbeans);
        hashMap.put("CAPSICUM",R.mipmap.bigcapsicum);

        displayname.setText(getIntent().getStringExtra("productname"));
        displaypname.setText(getIntent().getStringExtra("producername"));
        tvplace.setText(getIntent().getStringExtra("location"));
        tvmobile.setText(getIntent().getStringExtra("contactno"));
        displayqty.setText(getIntent().getStringExtra("availablequantity"));
        displayprice.setText(getIntent().getStringExtra("price"));
        cost=Integer.parseInt(price);

        displayquality.setText(getIntent().getStringExtra("quality"));
        Producerid=getIntent().getStringExtra("producerid");

        display_image.setImageDrawable(getApplicationContext().getResources().getDrawable(hashMap.get(getIntent().getStringExtra("productname"))));
    }

    public void getdata(String name,String pname,String quantity,String contactno,String price,String location,String quality){

        this.itemname=name;
        this.producername=pname;
        this.quantity=quantity;
        this.contactno=contactno;
        this.price=price;
        this.location=location;
        this.quality=quality;

    }
}