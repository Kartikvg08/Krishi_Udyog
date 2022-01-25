package com.example.krishiudyog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.krishiudyog.SendNotificationPack.APIService;
import com.example.krishiudyog.SendNotificationPack.Client;
import com.example.krishiudyog.SendNotificationPack.Data;
import com.example.krishiudyog.SendNotificationPack.MyResponse;
import com.example.krishiudyog.SendNotificationPack.NotificationSender;
import com.example.krishiudyog.SendNotificationPack.Token;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendNotif extends AppCompatActivity {
    Button placeorder;
    String title = " wants to buy your product";
    private APIService apiService;

    public void UpdateToken() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseMessaging.getInstance().getToken().getResult();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    public void sendNotifications(String usertoken, String title, String message) {
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        Data data = new Data(title, message);
        NotificationSender sender = new NotificationSender(data, usertoken);
        apiService.sendNotifcation(sender).enqueue(new Callback<MyResponse>() {
            @Override

            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().success != 1) {
                        Toast.makeText(getApplicationContext(), "Failed ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SendNotif.this, "Item ordered and notified", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }

    public void firebase(final String token, final String Consumername, final Orders od) {
        FirebaseDatabase.getInstance().getReference().child("Tokens").child(token).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String usertoken = dataSnapshot.getValue(String.class);
                String Message = "name: " + od.getOpname() + "\n" + "quantity: " + od.getOqty() + "\n" + "quality" + od.getOqlty();
                sendNotifications(usertoken, Consumername + title, Message);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }
}

