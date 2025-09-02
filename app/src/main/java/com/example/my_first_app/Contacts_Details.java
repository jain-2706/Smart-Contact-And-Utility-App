package com.example.my_first_app;

import static com.example.my_first_app.R.id.toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    @SuppressLint("MissingInflatedId")
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

        arr.add(new struct(R.drawable.first,"My Number","This has to Bring"));
        arr.add(new struct(R.drawable.second,"Ramu Bhaiya","Bhaiya ek kaam kardo mere College ka"));
        arr.add(new struct(R.drawable.third,"Shubham Bhaiya","Bhaiya wo samaan chahiye mujhe kab doge"));
        arr.add(new struct(R.drawable.frame__4_,"Badi Mosi","Mosi aaj ke liye Khana Bana lena mera"));
        arr.add(new struct(R.drawable.frame__5_,"Police Uncle","A crime has happened but you nicely caught the Culprit"));
        arr.add(new struct(R.drawable.frame__6_,"Annu Aunty","Jai Jinendra Aunty."));
        arr.add(new struct(R.drawable.child,"Aeshna","Hi Aishna How are You"));
        arr.add(new struct(R.drawable.brother,"Aagam Bhaiya","Bhaiya mere ek project banwa do na,Please"));
        arr.add(new struct(R.drawable.sister,"Nishtha Didi","Didi Kab jaa rhi ho Mandir Aap"));
        arr.add(new struct(R.drawable.father,"Papa","Papa mere liye ye pen liyana"));
        arr.add(new struct(R.drawable.mother,"Mummy","Mummy Mai is Sunday ko Aaunga"));
        arr.add(new struct(R.drawable.family,"Family Group","Sab ek baar saare Photos bhej do"));
        RecyclerView r1=findViewById(R.id.recycler_view);
        r1.setLayoutManager(new LinearLayoutManager(Contacts_Details.this));
       recycler_Ater a1=new recycler_Ater(arr,Contacts_Details.this);
        r1.setAdapter(a1);
        AutoCompleteTextView ws=findViewById(R.id.autoComplete);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(Contacts_Details.this,android.R.layout.simple_list_item_1,new ArrayList<>());
        ws.setAdapter(adapter);



        ws.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                a1.filt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Toolbar t1=findViewById(toolbar);
        setSupportActionBar(t1);
       ImageView image_of_camera=(ImageView)findViewById(R.id.camera);
       image_of_camera.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in_cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(in_cam,100);
           }
       });






       //If I want to set it the search bar
//       ws.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//           @Override
//           public boolean onQueryTextSubmit(String query) {
//               a1.filt(query);
//               return true;
//           }
//
//           @Override
//           public boolean onQueryTextChange(String newText) {
//               a1.filt(newText);
//               return true;
//           }
//       });


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
                 else if(id==R.id.call_user)
                 {
                     Intent in=new Intent(Intent.ACTION_DIAL);
//                     in.setData(Uri.parse("tel:+91999299929"));
                     startActivity(in);
                 }
                 else if(id==R.id.email)
                 {
                     Intent in=new Intent(Intent.ACTION_SEND);
                     in.setType("message/rfc822");
                     in.putExtra(Intent.EXTRA_EMAIL,new String[]{"abc@gmail.com","xyz@gmail.com"});
                     in.putExtra(Intent.EXTRA_SUBJECT,"Internship From Google:  ");
                     in.putExtra(Intent.EXTRA_TEXT,"An Excitng Offer from Google for the students");
                     startActivity(Intent.createChooser(in,"Read the Email"));
                 }
                 else {
                     Intent in=new Intent(Intent.ACTION_SEND);
                     in.setType("text/plain");
                     in.putExtra(Intent.EXTRA_TEXT,"Share this message with your friends");
                     startActivity(Intent.createChooser(in,"Share message"));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK)
        {
            if(requestCode==100)
            {
                Intent s=new Intent(Intent.ACTION_SEND);
                Bitmap bit= (Bitmap) data.getExtras().get("data");
                String path=MediaStore.Images.Media.insertImage(getContentResolver(),bit,"Image",null);
                s.setType("image/*");
                s.putExtra(Intent.EXTRA_STREAM,Uri.parse(path));
                startActivity(Intent.createChooser(s,"Sharing this Image"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}