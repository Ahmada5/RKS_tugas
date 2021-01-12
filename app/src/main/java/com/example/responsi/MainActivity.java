package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String[] daftar;
    ListView ListView1;
    Menu menu;
    protected Cursor cursor;
    datahelper dbcenter;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent inte = new Intent(MainActivity.this, Buat.class);
                startActivity(inte);
            }
        });

        ma = this;
        dbcenter = new datahelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_tumbuhan",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for	(int cc=0; cc < cursor.getCount(); cc++){ cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView1 = (ListView)findViewById(R.id.listView1);
        ListView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView1.setSelected(true);
        ListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat tumbuhan", "Update tumbuhan", "Info tumbuhan", "Hapus tumbuhan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent see = new Intent(getApplicationContext(), Lihat.class);
                                see.putExtra("nama", selection);
                                startActivity(see);
                                break;
                            case 1 :
                                Intent update = new Intent(getApplicationContext(), Update.class);
                                update.putExtra("nama", selection);
                                startActivity(update);
                                break;
                            case 2 :
                                Intent info = new Intent(getApplicationContext(), Info.class);
                                info.putExtra("nama", selection);
                                startActivity(info);
                                break;
                            case 3 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from tb_tumbuhan where nama = '"+selection+"'");
                                RefreshList();
                                Toast.makeText(getApplicationContext(), "Data tumbuhan Dihapus", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});

        ((ArrayAdapter)ListView1.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.logout){
            startActivity(new Intent(this, Login.class));
            Toast.makeText(getApplicationContext(), "Log Out Berhasil", Toast.LENGTH_LONG).show();
        }

        return true;
    }

}