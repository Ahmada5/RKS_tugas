package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    protected Cursor cursor;
    datahelper dbHelper;
    Button login, reset;
    EditText user, pass;
    String password = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new datahelper(this);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);

        login = (Button) findViewById(R.id.login);
        reset = (Button) findViewById(R.id.reset);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(user.getText().toString().equals("admin") && pass.getText().toString().equals("admin")){

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Username / Password Salah",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                user.setText("");
                pass.setText("");
            }
        });
    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}