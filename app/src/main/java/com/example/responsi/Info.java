package com.example.responsi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Info extends AppCompatActivity {
    protected Cursor cursor;
    Context context;
    Button Bkembali, Bprint;
    datahelper dbHelper;
    TextView text0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        dbHelper = new datahelper(this);
        Bprint = findViewById(R.id.button1);
        Bkembali = findViewById(R.id.button2);
        text0 = (TextView) findViewById(R.id.info0);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_tumbuhan WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text0.setText(cursor.getString(0).toString());
        }


        Bprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"print dummy", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Bkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}