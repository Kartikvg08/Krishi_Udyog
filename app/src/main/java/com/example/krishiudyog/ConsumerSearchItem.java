package com.example.krishiudyog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsumerSearchItem extends AppCompatActivity {

    Spinner itemspinner;
    ImageButton btsearch;
    RecyclerView conrv;
    Conitemadapter conadapter;
    List<Itemslist> conitemslist;
    DatabaseReference dbitems;
    TextView ProducerName;
    Button logoutbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_search_item);

        logoutbt=findViewById(R.id.logoutbt);
        itemspinner = findViewById(R.id.itemspinner);
        btsearch = findViewById(R.id.btsearch);
        conitemslist = new ArrayList<>();
        conrv = findViewById(R.id.conrv);
        conrv.setHasFixedSize(true);
        conrv.setLayoutManager(new LinearLayoutManager(this));
        conadapter = new Conitemadapter(this, conitemslist);
        conrv.setAdapter(conadapter);
        ProducerName=findViewById(R.id.ProducerName);


        dbitems = FirebaseDatabase.getInstance().getReference("Itemslist");
        final Query query = FirebaseDatabase.getInstance().getReference("Itemslist");

        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemname = itemspinner.getSelectedItem().toString();

                query.orderByChild("productname").equalTo(itemname).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        conitemslist.clear();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Itemslist item = snapshot.getValue(Itemslist.class);
                                conitemslist.add(item);
                            }
                            conadapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(ConsumerSearchItem.this,"Sorry, Item Not Available",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        logoutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ConsumerSearchItem.this,ConsumerLogin.class));
            }
        });
    }


}

