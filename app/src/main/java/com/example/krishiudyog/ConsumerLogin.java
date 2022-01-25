package com.example.krishiudyog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConsumerLogin extends AppCompatActivity {

    TextView tvconsumerlogin,tvregisterlink;
    EditText etconsumeremail,etconsumerpassword;
    Button btconsumerlogin;
    ProgressBar consumerpb;
    FirebaseAuth fAuth;
    DatabaseReference dbconsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_login);

        tvconsumerlogin=findViewById(R.id.tvconsumerlogin);
        tvregisterlink=findViewById(R.id.tvregisterlink);
        etconsumeremail=findViewById(R.id.etconsumeremail);
        etconsumerpassword=findViewById(R.id.etconsumerpassword);
        btconsumerlogin=findViewById(R.id.btconsumerlogin);
        consumerpb=findViewById(R.id.consumerpb);
        fAuth=FirebaseAuth.getInstance();
        dbconsumer=FirebaseDatabase.getInstance().getReference("Consumers");

        tvregisterlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConsumerLogin.this,com.example.krishiudyog.RegisterConsumer.class));
            }
        });

        btconsumerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=etconsumeremail.getText().toString().trim();
                String password=etconsumerpassword.getText().toString().trim();

                if(email.isEmpty())
                {
                    etconsumeremail.setError("Please enter your email");
                    etconsumeremail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    etconsumeremail.setError("Please enter valid email");
                    etconsumeremail.requestFocus();
                    return;
                }

                if(password.isEmpty())
                {
                    etconsumerpassword.setError("Please enter your password");
                    etconsumerpassword.requestFocus();
                    return;
                }

                if(password.length() < 6)
                {
                    etconsumerpassword.setError("Password should be minimum of 6 literals");
                    etconsumerpassword.requestFocus();
                    return;
                }

                consumerpb.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            dbconsumer= FirebaseDatabase.getInstance().getReference("Consumers");
                            final String consumerid=FirebaseAuth.getInstance().getCurrentUser().getUid();

                            dbconsumer.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String Consumername=dataSnapshot.child(consumerid).child("fullname").getValue(String.class);
                                    Toast.makeText(getApplicationContext(),"Welcome "+Consumername,Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            startActivity(new Intent(ConsumerLogin.this,com.example.krishiudyog.ConsumerSearchItem.class));
                        }
                        else{
                            consumerpb.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"Error logging in!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}