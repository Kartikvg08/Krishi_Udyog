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

public class RegisterProducer extends AppCompatActivity {

    TextView tvregisterproducer,tvcreate,tvproducerloginlink;
    EditText etproducername,etproduceremail,etproducerpassword,etproducerphone,etplace;
    Button btregister;
    FirebaseAuth fAuth;
    ProgressBar pbproducer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_producer);

        tvregisterproducer=findViewById(R.id.tvregisterproducer);
        tvcreate=findViewById(R.id.tvcreate);
        tvproducerloginlink=findViewById(R.id.tvproducerloginlink);
        etproducername=findViewById(R.id.etproducername);
        etproduceremail=findViewById(R.id.etproduceremail);
        etproducerpassword=findViewById(R.id.etproducerpassword);
        etproducerphone=findViewById(R.id.etproducerphone);
        etplace=findViewById(R.id.etplace);
        btregister=findViewById(R.id.btregister);
        fAuth=FirebaseAuth.getInstance();
        pbproducer=findViewById(R.id.pbproducer);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),ProducersHomePage.class));
            finish();
        }

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=etproducername.getText().toString().trim();
                final String email1=etproduceremail.getText().toString().trim();
                final String password1=etproducerpassword.getText().toString().trim();
                final String phone=etproducerphone.getText().toString().trim();
                final String place=etplace.getText().toString().trim();

                Itemslist i=new Itemslist();
                i.setContact(phone);
                i.setPname(name);
                i.setLocation(place);

                if(name.isEmpty())
                {
                    etproducername.setError("Please enter your name");
                    etproducername.requestFocus();
                    return;
                }

                if(email1.isEmpty())
                {
                    etproduceremail.setError("Please enter your email");
                    etproduceremail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches())
                {
                    etproduceremail.setError("Please enter valid email");
                    etproduceremail.requestFocus();
                    return;
                }

                if(password1.isEmpty())
                {
                    etproducerpassword.setError("Please enter your password");
                    etproducerpassword.requestFocus();
                    return;
                }

                if(phone.isEmpty())
                {
                    etproducerphone.setError("Please enter your phone number");
                    etproducerphone.requestFocus();
                    return;
                }
                if(password1.length() < 6)
                {
                    etproducerpassword.setError("Password should be minimum of 6 literals");
                    etproducerpassword.requestFocus();
                    return;
                }
                if(place.isEmpty())
                {
                    etplace.setError("Please enter Place");
                    etplace.requestFocus();
                    return;
                }

                pbproducer.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Producers producer= new Producers(name,email1,phone,password1,place);
                            producer.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            FirebaseDatabase.getInstance().getReference("Producers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(producer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        pbproducer.setVisibility(View.GONE);
                                        Toast.makeText(RegisterProducer.this, "User Created sucessfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterProducer.this,com.example.krishiudyog.ProducerLogin.class));
                                        Toast.makeText(getApplicationContext(),"Please Enter Login credentials to login",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterProducer.this, "Error!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            pbproducer.setVisibility(View.GONE);
                            Toast.makeText(RegisterProducer.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        tvproducerloginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterProducer.this,com.example.krishiudyog.ProducerLogin.class));
            }
        });

    }


}






