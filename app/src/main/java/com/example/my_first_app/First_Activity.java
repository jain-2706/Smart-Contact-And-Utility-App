package com.example.my_first_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class First_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences s1=getSharedPreferences("login",MODE_PRIVATE);
        s1.getBoolean("flag",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!s1.getBoolean("flag", false)) {
                    Intent in = new Intent(First_Activity.this, user_credentials.class);
                    startActivity(in);
                }
                else
                {
                    Intent in = new Intent(First_Activity.this, Opening_Activity.class);
                    startActivity(in);
                }
            }
        },3000);
    }
}