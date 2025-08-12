package com.example.my_first_app;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Opening_Activity extends AppCompatActivity {
    private final String notification_id="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opening);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        channel();
        create_notification();

    }
    public void channel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel n1=new NotificationChannel(notification_id,"Notification Channel",IMPORTANCE_HIGH);
            NotificationManager nm=(NotificationManager) getSystemService(NotificationManager.class);
            nm.createNotificationChannel(n1);

        }


    }
    @SuppressLint("NotificationPermission")
    public void create_notification()
    {
        Bitmap bit=vector_to_Bitmap(Opening_Activity.this,R.drawable.frame__3_);
        NotificationCompat.BigPictureStyle n1=new NotificationCompat.BigPictureStyle()
                .setBigContentTitle("Try to Open the WhatsApp")
                .setSummaryText("Enhanced its features")
                .bigPicture(bit);
        Intent int_ent=new Intent(Opening_Activity.this, MainActivity2.class);
        int_ent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent p1=PendingIntent.getActivity(Opening_Activity.this,1,int_ent,FLAG_IMMUTABLE);

        NotificationCompat.Builder nb=new NotificationCompat.Builder(Opening_Activity.this,notification_id)
                .setOngoing(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText("Open the WhatsApp")
                .setContentTitle("WhatsApp")
                .setSmallIcon(R.drawable.frame__3_)
                .setLargeIcon(bit)
                .setContentIntent(p1)
                .setStyle(n1);
        NotificationManager nm=(NotificationManager) getSystemService(NotificationManager.class);
        nm.notify(1,nb.build());
    }
    public  Bitmap vector_to_Bitmap(Context c1,int image)
    {
        Drawable dr= ContextCompat.getDrawable(c1,image);
        int intrinsic_width= dr.getIntrinsicWidth()>0? dr.getIntrinsicWidth() : 100;
        int intrinsic_height= dr.getIntrinsicHeight()>0? dr.getIntrinsicHeight() : 100;
        Bitmap b1=Bitmap.createBitmap(intrinsic_width,intrinsic_height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(b1);
        dr.setBounds(0,0,intrinsic_width,intrinsic_height);
        dr.draw(canvas);
        return b1;
    }






}