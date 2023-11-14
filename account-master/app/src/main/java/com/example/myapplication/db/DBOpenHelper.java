package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(@Nullable Context context) {
        super(context, "AccountAble.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table typetb(id integer primary key autoincrement,typename varchar(10),imageId integer,sImageId integer,kind integer)";
        db.execSQL(sql);
        insertType(db);
        // create account table
        sql = "create table accounttb(id integer primary key autoincrement,typename varchar(10),imageId integer,notes varchar(80),money float, " +
                "time varchar(60),year integer, month integer, day integer, kind integer)";
        db.execSQL(sql);
    }

    private void insertType(SQLiteDatabase db) {
        String sql = "insert into typetb (typename, imageId, sImageId, kind) values (?,?,?,?)";
        db.execSQL(sql,new Object[]{"Other", R.mipmap.icon_other,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Food", R.mipmap.icon_food,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Traffic", R.mipmap.icon_traffic,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Shopping", R.mipmap.icon_shopping,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Clothes", R.mipmap.icon_clothes,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Daily", R.mipmap.icon_daily,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Ent", R.mipmap.icon_entertainment,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Tour", R.mipmap.icon_tour,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Study", R.mipmap.icon_study,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Medicine", R.mipmap.icon_medicine,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"House", R.mipmap.icon_houserent,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Donate", R.mipmap.icon_donate,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Phone", R.mipmap.icon_phone1,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Smoke&Wine", R.mipmap.icon_smoke_wine,R.mipmap.click,0});
        db.execSQL(sql,new Object[]{"Makeup", R.mipmap.icon_makeup,R.mipmap.click,0});

        db.execSQL(sql,new Object[]{"Other", R.mipmap.icon_other,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Salary", R.mipmap.icon_salary,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Winning", R.mipmap.icon_winning,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Bonus", R.mipmap.icon_bonus,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Investment", R.mipmap.icon_investment,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Borrow", R.mipmap.icon_donate,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Loan", R.mipmap.icon_business,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Collection", R.mipmap.icon_intrest,R.mipmap.click,1});
        db.execSQL(sql,new Object[]{"Interest", R.mipmap.icon_daily,R.mipmap.click,1});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
