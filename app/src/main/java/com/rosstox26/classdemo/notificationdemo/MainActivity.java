package com.rosstox26.classdemo.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button buttonSend;
    private Button buttonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonCheck = (Button) findViewById((R.id.buttonCheck));
        buttonSend.setOnClickListener(this);
        buttonCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == findViewById(R.id.buttonSend)) {
            sendNotification("You clicked the button - Now Die");
        } else {
            //Check whether the app is connected
            //Add the following to Manifest before running
            //<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                Toast.makeText(this, "You are all connected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You are NOT Connected", Toast.LENGTH_SHORT).show();
            }


        }
    }


private void sendNotification(String text) {

    try {
        sleep(5000);
    } catch (Exception e) {

        // do nothing
    }
    Intent notificationIntent = new Intent(this, Main2Activity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    int flags = PendingIntent.FLAG_UPDATE_CURRENT;

    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, flags);

    int icon = R.mipmap.ic_launcher;
    CharSequence ticketText = "You App Has Been Updated";
//    CharSequence contentTitle = getText(R.string.app_name);
    CharSequence contentTitle = "Click to see your reward";
    CharSequence contentText = text;


    Notification notification = new Notification.Builder(this)
            .setSmallIcon(icon)
            .setTicker(ticketText)
            .setContentTitle(contentTitle)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build();

    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    final int NOTIFICATION_ID = 1;
    manager.notify(NOTIFICATION_ID, notification);

}

}
