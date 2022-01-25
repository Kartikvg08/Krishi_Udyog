package com.example.krishiudyog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConsumersHomePage extends AppCompatActivity {
    ImageView search;
    Button btlogout;
    TextView tvwelcome;

    DatabaseReference dbconsumer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumers_home_page);
        search=findViewById(R.id.search);
        btlogout=findViewById(R.id.btlogout);
        tvwelcome=findViewById(R.id.tvwelcome);

        dbconsumer= FirebaseDatabase.getInstance().getReference("Consumers");
        final String consumerid=FirebaseAuth.getInstance().getCurrentUser().getUid();

        dbconsumer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Consumername=dataSnapshot.child(consumerid).child("fullname").getValue(String.class);
                tvwelcome.setText("Welcome "+Consumername);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumersHomePage.this,ConsumerSearchItem.class);
                startActivity(intent);
            }
        });
        btlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ConsumersHomePage.this,ConsumerLogin.class));
            }
        });
    }
}