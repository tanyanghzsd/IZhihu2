package com.ty.izhihu.db;

import java.util.ArrayList;
import java.util.List;

import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.NewsDetail;
import com.ty.izhihu.bean.Theme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ThemeDB{

	public static ThemeDB instance;
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	public ThemeDB(Context context) {
		dbHelper=new DBHelper(context);
		db=dbHelper.getWritableDatabase();
		// TODO Auto-generated constructor stub
	}
	
	public static ThemeDB getInstance(Context context){
		if(instance==null) {
			instance = new ThemeDB(context);
		}
		return instance;
	}
	
	public void saveThemeNews(Theme news){
		long id = news.getId();
		String name = news.getName();
		String thumbnail= news.getThumbnail();
		String description = news.getDescription();
		
		ContentValues cv = new ContentValues();
		cv.put(DBHelper.SELECTION__COLUMN_ID, id);
		cv.put(DBHelper.SELECTION__COLUMN_NAME, name);
		cv.put(DBHelper.SELECTION__COLUMN_THUMBNAIL, thumbnail);
		cv.put(DBHelper.SELECTION__COLUMN_DESCRIPTION, description);
		db.insert(DBHelper.TABLE_SELECTION, null, cv);
		
	}
	
	public Theme LoadThemeByName(String name){
		Cursor cursor = db.query(DBHelper.TABLE_SELECTION, null, DBHelper.SELECTION__COLUMN_NAME+"=?", new String[]{name}, null, null, null);
		Theme theme = null;
		if (cursor.moveToFirst()) {
            do {
                Theme news = new Theme();
                int id = news.getId();
                String name2 = news.getName();
                String thumbnail = news.getThumbnail();
                String description = news.getDescription();
                theme = new Theme(name2, id, thumbnail, description);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return theme;
	}
	
	

}
