package com.example.my_first_app;

import static com.example.my_first_app.R.id.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Contacts_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<struct>arr=new ArrayList<struct>();
        AutoCompleteTextView a2=new AutoCompleteTextView(Contacts_Details.this);
        arr.add(new struct(R.drawable.first,"My Number","This has to Bring"));
        arr.add(new struct(R.drawable.second,"Ramu Bhaiya","Bhaiya ek kaam kardo mere College ka"));
        arr.add(new struct(R.drawable.third,"Shubham Bhaiya","Bhaiya wo samaan chahiye mujhe kab doge"));
        arr.add(new struct(R.drawable.frame__4_,"Badi Mosi","Mosi aaj ke liye Khana Bana lena mera"));
        arr.add(new struct(R.drawable.frame__5_,"Police Uncle","A crime has happened but you nicely caught the Culprit..."));
        arr.add(new struct(R.drawable.frame__6_,"Annu Aunty","Jai Jinendra Aunty...."));
        RecyclerView r1=findViewById(R.id.recycler_view);
        r1.setLayoutManager(new LinearLayoutManager(Contacts_Details.this));
       recycler_Ater a1=new recycler_Ater(arr,Contacts_Details.this);
        AutoCompleteTextView ar2=new AutoCompleteTextView(Contacts_Details.this);
        r1.setAdapter(a1);
        String[] suggest={"My Number","Ramu Bhaiya","Shubham Bhaiya","Badi Mosi","Police Uncle","Annu Aunty"};
        AutoCompleteTextView ws=findViewById(R.id.autoComplete);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(Contacts_Details.this,android.R.layout.simple_list_item_1,suggest);
        ws.setAdapter(adapter);
        ws.setThreshold(1);
        Toolbar t1=findViewById(toolbar);
        setSupportActionBar(t1);
        getSupportActionBar().setTitle("");

//        ImageView img1=findViewById(R.id.smoo);
//        img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation anm1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
//                img1.startAnimation(anm1);
//            }
//        });
        BottomNavigationView bot=findViewById(R.id.bottom);
        bot.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id=item.getItemId();
                 if(id==R.id.maps_google)
                 {
                     Intent intent=new Intent(Contacts_Details.this,MapsActivity.class);
                     startActivity(intent);
                 }
                return true;
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.three_dots,menu);
        return true;
    }


}