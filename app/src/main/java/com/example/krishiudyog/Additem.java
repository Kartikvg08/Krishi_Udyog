package com.example.krishiudyog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Additem extends AppCompatActivity {
    EditText etname,etqty,etrate;
    Spinner qualityspinner,namespinner;
    Button addbtn;
    static int cncnt=0;
    HashMap<String, Integer> hashMap=new HashMap<String, Integer>();


    String ProducerLocation,ProducerName,ProducerContact;
    String quality,quantity,rate,name;
    String Productid;

    DatabaseReference databaseproducts,dbitems,dbproducer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        etname=findViewById(R.id.etname);
        etqty=findViewById(R.id.etqty);
        etrate=findViewById(R.id.etrate);
        addbtn=findViewById(R.id.addbtn);
        qualityspinner=findViewById(R.id.qualityspinner);
        namespinner=findViewById(R.id.namespinner);
        databaseproducts= FirebaseDatabase.getInstance().getReference("Products");
        dbitems=FirebaseDatabase.getInstance().getReference("Itemslist");
        dbproducer=FirebaseDatabase.getInstance().getReference("Producers");

        addintohash();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=namespinner.getSelectedItem().toString();
                quality=qualityspinner.getSelectedItem().toString();
                rate=etrate.getText().toString().trim();
                quantity=etqty.getText().toString().trim();

                /*if(name.isEmpty())
                {
                    etname.setError("Please Enter the item name");
                    etname.requestFocus();
                    return;
                }*/
                if(rate.isEmpty())
                {
                    etrate.setError("Please Enter the cost of item");
                    etrate.requestFocus();
                    return;
                }
                if(quantity.isEmpty())
                {
                    etqty.setError("Please Enter the quantity");
                    etqty.requestFocus();
                    return;
                }

                final String ProducerId=FirebaseAuth.getInstance().getCurrentUser().getUid();
                dbproducer.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            ProducerName = dataSnapshot.child(ProducerId).child("fullname").getValue(String.class);
                            ProducerContact = dataSnapshot.child(ProducerId).child("phone").getValue(String.class);
                            if(ProducerContact.equals(null)){ProducerContact="8861662368";}
                            ProducerLocation = dataSnapshot.child(ProducerId).child("place").getValue(String.class);

                            Itemslist i=new Itemslist();
                            i.setLocation(ProducerLocation);
                            i.setPname(ProducerName);
                            i.setContact(ProducerContact);
                            i.setQuality(quality);
                            i.setQuantity(quantity);
                            i.setRate(rate);
                            i.setProductname(name);
                            i.setPid(ProducerId);
                            Integer img=hashMap.get(name);
                            i.setImage(img);

                            dbitems.child(Productid).setValue(i);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Productid=databaseproducts.push().getKey();

                Products product=new Products(rate,Productid,name,quality,quantity);

                assert Productid != null;
                databaseproducts.child(Productid).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Additem.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Additem.this,com.example.krishiudyog.ViewItems.class));
                        }
                        else
                        {
                            Toast.makeText(Additem.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }

    public void addintohash()
    {
        hashMap.put("ONION",R.mipmap.onion);
        hashMap.put("POTATO",R.mipmap.potato);
        hashMap.put("CABBAGE",R.mipmap.cabbage);
        hashMap.put("RICE",R.mipmap.rice);
        hashMap.put("WHEAT",R.mipmap.wheat);
        hashMap.put("CAULIFLOWER",R.mipmap.cauliflower);
        hashMap.put("BRINJAL",R.mipmap.brinjal);
        hashMap.put("CARROT",R.mipmap.carrot);
        hashMap.put("RADISH",R.mipmap.radish);
        hashMap.put("GREEN BEANS",R.mipmap.greenbeans);
        hashMap.put("CAPSICUM",R.mipmap.capsicum);
        Itemslist i=new Itemslist();
        i.setimagehash(hashMap);
    }


}