package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private  static SQLiteDatabase db;
    public static void initDB(Context context){
        DBOpenHelper helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    public static List<TypeBean>getTypeList(int kind){
        List<TypeBean>list = new ArrayList<>();
        String sq1 = "select * from typetb where kind=" + kind;
        Cursor cursor = db.rawQuery(sq1,null);
        while (cursor.moveToNext()) {
           String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
           int imageId = cursor.getInt(cursor.getColumnIndexOrThrow("imageId"));
           int sImageId = cursor.getInt(cursor.getColumnIndexOrThrow("sImageId"));
           int kind1 = cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
           int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
           TypeBean typeBean = new TypeBean(id,typename,imageId,sImageId,kind1);
           list.add(typeBean);
        }
        return list;
    }


    /*
    insert an element into the account table
     */
    public static void insertItemToAccounttb(AccountBean bean){
        ContentValues values = new ContentValues();
        values.put("typename",bean.getTypename());
        values.put("imageId",bean.getImageId());
        values.put("notes",bean.getNotes());
        values.put("money",bean.getMoney());
        values.put("time",bean.getTime());
        values.put("year",bean.getYear());
        values.put("month",bean.getMonth());
        values.put("day",bean.getDay());
        values.put("kind",bean.getKind());

        Log.d(String.valueOf(bean.getTime()), "time is: ");
        Log.d(String.valueOf(bean.getYear()), "year is: ");
        Log.d(String.valueOf(bean.getMonth()), "month is: ");
        Log.d(String.valueOf(bean.getDay()), "day is: ");
        db.insert("accounttb",null,values);
    }

    public static List<AccountBean>getAccountListOneDayFromAccounttb(int year,int month,int day){
        List<AccountBean>list = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? and day=? order by id desc";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + ""});
        Log.d(String.valueOf(cursor.getCount()), "getAccountListOneDayFromAccounttb: ");

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String typename = cursor.getString(cursor.getColumnIndex("typename"));
            @SuppressLint("Range") String note = cursor.getString(cursor.getColumnIndex("notes"));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
            @SuppressLint("Range") int sImageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            @SuppressLint("Range") int kind = cursor.getInt(cursor.getColumnIndex("kind"));
            @SuppressLint("Range") float money = cursor.getFloat(cursor.getColumnIndex("money"));
            AccountBean accountBean = new AccountBean(id, typename, sImageId, note, money, time, year, month, day, kind);
            list.add(accountBean);
        }
        return list;
    }

    public static float getSumMoneyOneDay (int year, int month, int day, int kind) {
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and month=? and day=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + "", kind + ""});
        // Traverse
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") float money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
            total = money;
        }
        return total;
    }

    public static float getSumMoneyOneMonth (int year, int month, int kind) {
        float total = 0.0f;
        String sql = "select sum(money) from accounttb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", kind + ""});
        // Traverse
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") float money = cursor.getFloat(cursor.getColumnIndex("sum(money)"));
            total = money;
        }
        return total;
    }
}
