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

public class Update extends AppCompatActivity {
    protected Cursor cursor;
    datahelper dbHelper;
    Button button1, button2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new datahelper(this);
        text1 = (EditText) findViewById(R.id.editTextup1);
        text2 = (EditText) findViewById(R.id.editTextup2);
        text3 = (EditText) findViewById(R.id.editTextup3);
        text4 = (EditText) findViewById(R.id.editTextup4);
        text5 = (EditText) findViewById(R.id.editTextup5);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_tumbuhan WHERE nama = '" + getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
        }

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update tb_tumbuhan set nama='"+
                        text2.getText().toString() +"', jenis='" +
                        text3.getText().toString() +"', siram='"+
                        text4.getText().toString() +"', pupuk='" +
                        text5.getText().toString() +"' where no='"+ text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Tumbuhan berhasil di update", Toast.LENGTH_LONG).show();
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