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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterConsumer extends AppCompatActivity {

    TextView tvregisterconsumer,tvcreate,tvconsumerloginlink;
    EditText etconsumername,etconsumeremail,etconsumerpassword,etconsumerphone;
    Button btregister;
    FirebaseAuth fAuth;
    ProgressBar pbconsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_consumer);

        tvregisterconsumer=findViewById(R.id.tvregisterconsumer);
        tvcreate=findViewById(R.id.tvcreate);
        tvconsumerloginlink=findViewById(R.id.tvconsumerloginlink);
        etconsumername=findViewById(R.id.etconsumername);
        etconsumeremail=findViewById(R.id.etconsumeremail);
        etconsumerpassword=findViewById(R.id.etconsumerpassword);
        etconsumerphone=findViewById(R.id.etconsumerphone);
        btregister=findViewById(R.id.btregister);
        fAuth=FirebaseAuth.getInstance();
        pbconsumer=findViewById(R.id.pbconsumer);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),ConsumersHomePage.class));
            finish();
        }

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=etconsumername.getText().toString().trim();
                final String email1=etconsumeremail.getText().toString().trim();
                final String password1=etconsumerpassword.getText().toString().trim();
                final String phone=etconsumerphone.getText().toString().trim();

                if(name.isEmpty())
                {
                    etconsumername.setError("Please enter your name");
                    etconsumername.requestFocus();
                    return;
                }

                if(email1.isEmpty())
                {
                    etconsumeremail.setError("Please enter your email");
                    etconsumeremail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches())
                {
                    etconsumeremail.setError("Please enter valid email");
                    etconsumeremail.requestFocus();
                    return;
                }

                if(password1.isEmpty())
                {
                    etconsumerpassword.setError("Please enter your password");
                    etconsumerpassword.requestFocus();
                    return;
                }

                if(phone.isEmpty())
                {
                    etconsumerphone.setError("Please enter your phone number");
                    etconsumerphone.requestFocus();
                    return;
                }
                if(password1.length() < 6)
                {
                    etconsumerpassword.setError("Password should be minimum of 6 literals");
                    etconsumerpassword.requestFocus();
                    return;
                }

                pbconsumer.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Consumers consumer= new Consumers(name,email1,phone,password1);

                            FirebaseDatabase.getInstance().getReference("Consumers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(consumer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        pbconsumer.setVisibility(View.GONE);
                                        Toast.makeText(RegisterConsumer.this, "User Created sucessfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterConsumer.this,com.example.krishiudyog.ConsumerLogin.class));
                                        Toast.makeText(RegisterConsumer.this, "Please Enter Login Credentials to login", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterConsumer.this, "Error!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            pbconsumer.setVisibility(View.GONE);
                            Toast.makeText(RegisterConsumer.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        tvconsumerloginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterConsumer.this,com.example.krishiudyog.ConsumerLogin.class));
            }
        });

    }
}