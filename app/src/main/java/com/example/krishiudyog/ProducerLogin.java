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

public class ProducerLogin extends AppCompatActivity {

    TextView tvproducerlogin,tvregisterlink;
    EditText etproduceremail,etproducerpassword;
    Button btproducerlogin;
    ProgressBar producerpb;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_login);

        tvproducerlogin=findViewById(R.id.tvproducerlogin);
        tvregisterlink=findViewById(R.id.tvregisterlink);
        etproduceremail=findViewById(R.id.etproducer1email);
        etproducerpassword=findViewById(R.id.etproducer1password);
        btproducerlogin=findViewById(R.id.btproducerlogin);
        producerpb=findViewById(R.id.producerpb);
        fAuth=FirebaseAuth.getInstance();

        tvregisterlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProducerLogin.this,com.example.krishiudyog.RegisterProducer.class));
            }
        });

        btproducerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=etproduceremail.getText().toString().trim();
                String password=etproducerpassword.getText().toString().trim();

                if(email.isEmpty())
                {
                    etproduceremail.setError("Please enter your email");
                    etproduceremail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    etproduceremail.setError("Please enter valid email");
                    etproduceremail.requestFocus();
                    return;
                }

                if(password.isEmpty())
                {
                    etproducerpassword.setError("Please enter your password");
                    etproducerpassword.requestFocus();
                    return;
                }

                if(password.length() < 6)
                {
                    etproducerpassword.setError("Password should be minimum of 6 literals");
                    etproducerpassword.requestFocus();
                    return;
                }

                producerpb.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(ProducerLogin.this,com.example.krishiudyog.ProducersHomePage.class);
                            intent.putExtra("producerid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                            startActivity(intent);

                        }
                        else{
                            producerpb.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"Error logging in!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}