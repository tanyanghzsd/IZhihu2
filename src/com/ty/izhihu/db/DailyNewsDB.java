package com.ty.izhihu.db;

import java.util.ArrayList;
import java.util.List;

import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.NewsDetail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DailyNewsDB{

	private Context context;
	public static DailyNewsDB instance;
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public DailyNewsDB(Context context) {
		dbHelper=new DBHelper(context);
		db=dbHelper.getWritableDatabase();
		// TODO Auto-generated constructor stub
	}

	public static DailyNewsDB getInstance(Context context){
		if(instance==null) {
			instance = new DailyNewsDB(context);
		}
		return instance;
	}

	public void saveFavoriteNews(News news){


		long id = news.getId();
		String title = news.getTitle();
		String images = news.getImages();

		ContentValues cv = new ContentValues();
		cv.put(DBHelper.COLUMN_NEWS_ID, id);
		cv.put(DBHelper.COLUMN_NEWS_TITLE, title);
		cv.put(DBHelper.COLUMN_NEWS_IMAGE, images);
		db.insert(DBHelper.TABLE_NAME, null, cv);

	}

	public void saveFavoriteNews(long id){
		ContentValues cv = new ContentValues();
		cv.put(DBHelper.COLUMN_NEWS_ID, id);
		db.insert(DBHelper.TABLE_NAME, null, cv);

	}
	public void deleteFavoriteNews(long id){
		db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_NEWS_ID+"=?", new String[]{id+""});
	}
	

	public void deleteFavoriteNews(News news){
		long id = news.getId();
		db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_NEWS_ID+"=?", new String[]{id+""});
	}

	public boolean isFavoriteSaved(long id){
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, DBHelper.COLUMN_NEWS_ID+"=?", new String[]{id+""}, null, null, null);
		if (cursor.moveToNext()) {
			cursor.close();
			return true;
		} else {
			return false;
		}
	}

	public List<News> LoadAllFavoriteNews(){
		List<News> listNews=new ArrayList<News>();
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				News news = new News();
				news.setId(cursor.getInt(1));
				news.setTitle(cursor.getString(2));
				news.setImages(cursor.getString(3));
				listNews.add(news);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return listNews;
	}



}
