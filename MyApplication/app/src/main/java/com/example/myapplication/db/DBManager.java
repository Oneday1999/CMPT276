package com.example.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
}
