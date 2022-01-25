package com.example.krishiudyog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Prod_Orders extends AppCompatActivity {

    DatabaseReference dborders, dbproducers;

    String producername, ordersproducername;


    OrderAdapter orderadapter;
    List<Orders> orderslist;
    RecyclerView listView;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod__orders);
        listView = (RecyclerView) findViewById(R.id.rv);
        orderslist = new ArrayList<>();
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        orderadapter = new OrderAdapter(this, orderslist);
        listView.setAdapter(orderadapter);
        dborders = FirebaseDatabase.getInstance().getReference("Orders");
        dbproducers = FirebaseDatabase.getInstance().getReference("Producers");

        final String Producerid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dbproducers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                producername = dataSnapshot.child(Producerid).child("fullname").getValue(String.class);
                System.out.println(producername);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query query = FirebaseDatabase.getInstance().getReference("Orders");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderslist.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Orders o = ds.getValue(Orders.class);
                        if(o.getProducername().equals(producername)) {
                            orderslist.add(o);
                            //Toast.makeText(getApplicationContext(),"Shree Ram Chandra ki jai",Toast.LENGTH_SHORT).show();
                        }
                    }
                    orderadapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
