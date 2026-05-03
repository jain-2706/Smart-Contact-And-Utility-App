package com.example.my_first_app;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class Opening_Activity extends AppCompatActivity {
    private final String notification_id = "1";
    DrawerLayout dlay;
    NavigationView nview;
    ScrollView dashboardScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opening);

        // 1. Initialize Views
        dlay = findViewById(R.id.main_s);
        nview = findViewById(R.id.nview);
        dashboardScroll = findViewById(R.id.dashboard_scroll);
        Toolbar tbar = findViewById(R.id.tool);
        setSupportActionBar(tbar);

        // 2. Padding Fix
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_s), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        channel();
        create_notification();

        // 3. Navigation Drawer Setup
        ActionBarDrawerToggle tg = new ActionBarDrawerToggle(this, dlay, tbar, R.string.Open_Drawer, R.string.Close_Drawer);
        dlay.addDrawerListener(tg);
        tg.syncState();

        // 4. Logout Button
        MaterialButton logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(v -> {
            SharedPreferences s1 = getSharedPreferences("login", MODE_PRIVATE);
            s1.edit().putBoolean("flag", false).apply();
            startActivity(new Intent(Opening_Activity.this, First_Activity.class));
            finish();
        });

        // 5. Drawer Menu Clicks
        nview.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.face) loadfragment(new Facebook_Fragment(), 0);
            else if (id == R.id.google) loadfragment(new Google_Fragment(), 0);
            else if (id == R.id.insta) loadfragment(new Instagram_Fragment(), 0);
            else if (id == R.id.twitter) loadfragment(new Twitter_Fragment(), 0);
            else startActivity(new Intent(Opening_Activity.this, MainActivity2.class));

            dlay.closeDrawers();
            return true;
        });
        LinearLayout l1=findViewById(R.id.mapsfrom);
        LinearLayout l2=findViewById(R.id.contactfrom);
        LinearLayout l3=findViewById(R.id.camerafrom);
        LinearLayout l4=findViewById(R.id.profilefrom);
        l1.setOnClickListener(v->{
            Intent intent=new Intent(Opening_Activity.this,MapsActivity.class);
            startActivity(intent);
        });
        l2.setOnClickListener(v -> {
            Intent in=new Intent(Intent.ACTION_DIAL);
//                     in.setData(Uri.parse("tel:+91999299929"));
            startActivity(in);
        });
        l3.setOnClickListener(v -> {
            Intent in_cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(in_cam,100);
        });
        l4.setOnClickListener(v -> {
            Intent in=new Intent(Intent.ACTION_SEND);
            in.setType("message/rfc822");
            in.putExtra(Intent.EXTRA_EMAIL,new String[]{"abc@gmail.com","xyz@gmail.com"});
            in.putExtra(Intent.EXTRA_SUBJECT,"Internship From Google:  ");
            in.putExtra(Intent.EXTRA_TEXT,"An Excitng Offer from Google for the students");
            startActivity(Intent.createChooser(in,"Read the Email"));
        });

    }

    public void loadfragment(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Hide the dashboard UI when a fragment is loaded
        if (dashboardScroll != null) dashboardScroll.setVisibility(View.GONE);

        if (flag == 1) ft.add(R.id.frame, fragment);
        else ft.replace(R.id.frame, fragment);

        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (dlay.isDrawerOpen(nview)) {
            dlay.closeDrawers();
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            // Show dashboard again when coming back from a fragment
            if (dashboardScroll != null) dashboardScroll.setVisibility(View.VISIBLE);
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    // --- Notifications & Bitmaps ---
    public void channel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel n1 = new NotificationChannel(notification_id, "Notification Channel", IMPORTANCE_HIGH);
            NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
            if (nm != null) nm.createNotificationChannel(n1);
        }
    }

    @SuppressLint("NotificationPermission")
    public void create_notification() {
        Bitmap bit = vector_to_Bitmap(this, R.drawable.frame__3_);
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this, notification_id)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText("Open the WhatsApp")
                .setContentTitle("WhatsApp")
                .setSmallIcon(R.drawable.frame__3_)
                .setLargeIcon(bit)
                .setAutoCancel(true);

        NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
        if (nm != null) nm.notify(1, nb.build());
    }

    public Bitmap vector_to_Bitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable == null) return null;
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}