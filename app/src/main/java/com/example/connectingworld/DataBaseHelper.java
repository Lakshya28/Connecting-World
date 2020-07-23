package com.example.connectingworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "News.db";
    public static final String TABLE_NAME = "News_table";

    public static final String COL_1 = "Id";
    public static final String COL_2 = "Title";
    public static final String COL_3 = "URL";
    public static final String COL_4 = "Image_URL";
    public static final String COL_5 = "Section_Name";
    public static final String COL_6 = "Published_Date";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " TEXT PRIMARY KEY, " + COL_2 + " TEXT, " + COL_3 + " TEXT, " + COL_4 + " TEXT, " + COL_5 + " TEXT, " + COL_6 + " TEXT)";
        Log.d("LakTable", table);
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String id, String title, String url, String imageUrl, String sectionName, String publishedDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, url);
        contentValues.put(COL_4, imageUrl);
        contentValues.put(COL_5, sectionName);
        contentValues.put(COL_6, publishedDate);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID=?", new String[]{id});
    }

    public boolean searchData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Select * from " + TABLE_NAME + " where " + COL_1 + " = " + "'" + id + "'";
        Log.d("LakQuery ", sql);
        Cursor cursor = db.rawQuery(sql, null);
        return cursor.getCount() > 0;
    }


    public ArrayList<NewsData> getNewsList() {
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NewsData> newsData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String url = cursor.getString(2);
                String imageUrl = cursor.getString(3);
                String sectionName = cursor.getString(4);
                String publishedDate = cursor.getString(5);
                newsData.add(new NewsData(id, title, url, imageUrl, sectionName, publishedDate));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return newsData;
    }


}
