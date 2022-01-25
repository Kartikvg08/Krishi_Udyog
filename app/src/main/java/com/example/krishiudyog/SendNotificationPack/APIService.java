package com.example.krishiudyog.SendNotificationPack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAK5BzLEU:APA91bFIPR5eteHfyF8bUgrytasvnu8CtN7d5oLRrmHJyWRPIK_xhHlM5AYudRMbJbWMZwcqwJ_Jrh4zYX04TW49unnwSXw9KX4GTyFp8ttNXPU2LZWEXS-kywBtLpaQCWCgk05sZz6u" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
}
