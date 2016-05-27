package com.ty.izhihu.db;

import com.ty.izhihu.bean.NewsDetail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NewsDetailDB {
	private Context context;
	public static NewsDetailDB instance;
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	
	public NewsDetailDB(Context context) {
		dbHelper=new DBHelper(context);
		db=dbHelper.getWritableDatabase();
		// TODO Auto-generated constructor stub
	}
	
	public static NewsDetailDB getInstance(Context context){
		if(instance==null) {
			instance = new NewsDetailDB(context);
		}
		return instance;
	}
	
	public void saveNewsDetail(NewsDetail newsDetail){
		ContentValues values = new ContentValues();
		values.put(DBHelper.ND_COLUMN_BODY, newsDetail.getBody());
		values.put(DBHelper.ND_COLUMN_IMAGE_SOURCE, newsDetail.getImage_source());
		values.put(DBHelper.ND_COLUMN_TITLE, newsDetail.getTitle());
		values.put(DBHelper.ND_COLUMN_IMAGE, newsDetail.getImage());
		values.put(DBHelper.ND_COLUMN_SHARE_URL, newsDetail.getShare_url());
		values.put(DBHelper.ND_COLUMN_GA_PREFIX, newsDetail.getGa_prefix());
		values.put(DBHelper.ND_COLUMN_ID, newsDetail.getId());
		values.put(DBHelper.ND_COLUMN_TYPE, newsDetail.getType());
		db.insert(DBHelper.TABLE_NEWS_DETAIL, null, values);
	}
	
	public boolean isExist(long id){
		Cursor cursor = db.query(DBHelper.TABLE_NEWS_DETAIL, null, DBHelper.ND_COLUMN_ID+"=?", new String[]{id+""}, null, null, null);
		if (cursor.moveToNext()) {
            cursor.close();
            return true;
        } else {
            return false;
        }
	}
	
	public String getShareUrlById(long id){
		String shareUrl=null;
		Cursor cursor = db.query(DBHelper.TABLE_NEWS_DETAIL, null, DBHelper.ND_COLUMN_ID+"=?", new String[]{id+""}, null, null, null);
		if (cursor.moveToNext()) {
            shareUrl = cursor.getString(cursor.getColumnIndex(DBHelper.ND_COLUMN_SHARE_URL));
            cursor.close();
        }
		return shareUrl;
	}
	
	public NewsDetail getNewsDetailById(long id){
		Cursor cursor = db.query(DBHelper.TABLE_NEWS_DETAIL, null, DBHelper.ND_COLUMN_ID+"=?", new String[]{id+""}, null, null, null);
		NewsDetail newsDetail=null;
		if (cursor.moveToNext()) {
			
            String share_url = cursor.getString(cursor.getColumnIndex(DBHelper.ND_COLUMN_SHARE_URL));
            String image = cursor.getString(cursor.getColumnIndex(DBHelper.ND_COLUMN_IMAGE));
            String title = cursor.getString(cursor.getColumnIndex(DBHelper.ND_COLUMN_TITLE));
            String image_source = cursor.getString(cursor.getColumnIndex(DBHelper.ND_COLUMN_IMAGE_SOURCE));
            String body = cursor.getString(cursor.getColumnIndex(DBHelper.ND_COLUMN_BODY));
          
            newsDetail.setShare_url(share_url);
            newsDetail.setImage(image);
            newsDetail.setImage_source(image_source);
            newsDetail.setTitle(title);
            newsDetail.setBody(body);
            
            cursor.close();
        }
		return newsDetail;
	}
	
}
