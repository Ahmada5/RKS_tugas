package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Buat extends AppCompatActivity {
    protected Cursor cursor;
    datahelper dbHelper;
    Button button1, button2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat);

        dbHelper = new datahelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("INSERT INTO tb_tumbuhan(no, nama, jenis, siram, pupuk) VALUES('" +
                    text1.getText().toString()+"','"+
                    text2.getText().toString() +"','" +
                    text3.getText().toString()+"','"+
                    text4.getText().toString() +"','" +
                    text5.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Berhasil di simpan", Toast.LENGTH_LONG).show();
            MainActivity.ma.RefreshList();
            finish();
        }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

}