package com.example.responsi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class datahelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tumbuhan.db";
    private static int DATABASE_VERSION = 1;
    public datahelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table tb_tumbuhan(no integer primary key autoincrement, nama text null, jenis text null, siram integer null, pupuk integer null);";
        Log.d("Data","onCreate: "+sql);
        db.execSQL(sql);

//        sql = "INSERT INTO tb_admin(nama_kurir, alamat_galon, jenis_galon, harga_galon, stok_galon) VALUES('jaka', 'kaliurang', 'mini', '8000', '5');";
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }
}

