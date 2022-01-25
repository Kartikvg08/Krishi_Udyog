package com.example.krishiudyog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewItems extends AppCompatActivity {

    RecyclerView rv;
    Itemadapter itemadapter;
    List<Itemslist> itemslist;
    DatabaseReference dbproducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        itemslist=new ArrayList<>();
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        itemadapter = new Itemadapter(this, itemslist);
        rv.setAdapter(itemadapter);

        //Select * from Product
        dbproducts= FirebaseDatabase.getInstance().getReference("Itemslist");
        dbproducts.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            itemslist.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Itemslist item = snapshot.getValue(Itemslist.class);
                    Itemslist i=new Itemslist();

                    itemslist.add(item);

                }
                itemadapter.notifyDataSetChanged();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };



}