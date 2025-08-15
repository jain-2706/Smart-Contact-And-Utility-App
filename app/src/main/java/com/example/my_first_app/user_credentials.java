package com.example.my_first_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class user_credentials extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_credentials);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText edt1=(EditText)findViewById(R.id.user);
        EditText edt2=(EditText)findViewById(R.id.pass);
        TextView t1=(TextView)findViewById(R.id.text_to_set);

        sqlite_class sqc=new sqlite_class(user_credentials.this);

        //To add some details previously
        sqc.addDetails("xxxx","1234");
        sqc.addDetails("xyxy","1221");
        sqc.addDetails("ywtss","1234");
        sqc.addDetails("tha","5678");
        sqc.addDetails("abcd","5768");
        sqc.addDetails("efgh","9088");
        sqc.addDetails("ijkl","2458");
        sqc.addDetails("mnop","9088");
        sqc.addDetails("qrst","8978");
        sqc.addDetails("uvwx","9090");
        sqc.addDetails("yzab","0000");
        sqc.addDetails("abet","1111");



        //To fetch and check
        Button bt=(Button)findViewById(R.id.login);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edt1.getText().toString();
                String b = edt2.getText().toString();
                if(a.isEmpty() && b.isEmpty())
                {
                    t1.setText("First Enter Username and Password ");
                }
                else if(a.isEmpty())
                {
                    t1.setText("Please enter Username");
                    edt2.setText("");
                }
                else if(b.isEmpty())
                {
                    t1.setText("Please enter Password ");
                    edt1.setText("");
                }
                else {
                    ArrayList<fetch_information_class> ans = sqc.checkuser();
                    int f = 0;

                    for (int i = 0; i < ans.size(); i++) {
                        if (ans.get(i).username.equals(a) && ans.get(i).password.equals(b)) {
                            f = 1;
                            break;
                        }
                    }
                    if(f==1)
                    {
                        t1.setText("You Successfully Logged In 🎉🎉😍😍");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent is = new Intent(user_credentials.this, Opening_Activity.class);
                                startActivity(is);
                            } },3000);
                    }
                    else
                    {
                        t1.setText("Invalid Username or Password or both");
                    }
                }
            }
        });
    }
}