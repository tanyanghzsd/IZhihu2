package com.ty.izhihu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private Context context;
	private final static String DB_NAME="favorite_news.db";
	public static final String TABLE_NAME = "daily_news_fav";
	
    public static final int DB_VERSION = 4;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NEWS_ID = "news_id";
    public static final String COLUMN_NEWS_TITLE = "news_title";
    public static final String COLUMN_NEWS_IMAGE = "news_image";
    
    public static final String TABLE_NEWS_DETAIL="table_news_detail";
    public static final String ND_COLUMN_BODY="body";
    public static final String ND_COLUMN_IMAGE_SOURCE="image_source";
    public static final String ND_COLUMN_TITLE="title";
    public static final String ND_COLUMN_IMAGE="image";
    public static final String ND_COLUMN_SHARE_URL="share_url";
    public static final String ND_COLUMN_GA_PREFIX="ga_prefix";
    public static final String ND_COLUMN_ID="id";
    public static final String ND_COLUMN_TYPE="type";

    public static final String DATABASE_CREATE
            = "CREATE TABLE " + TABLE_NAME
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NEWS_ID + " INTEGER , "
            + COLUMN_NEWS_TITLE + " TEXT, "
            + COLUMN_NEWS_IMAGE + " TEXT);";
    
    public static final String DATABASE_CREATE_NEWS_DETAIL
    		= "CREATE TABLE " + TABLE_NEWS_DETAIL
    		+ "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ ND_COLUMN_ID + " INTEGER UNIQUE, "
    		+ ND_COLUMN_BODY + " TEXT, "
    		+ ND_COLUMN_IMAGE_SOURCE + " TEXT, "
    		+ ND_COLUMN_TITLE + " TEXT, "
    		+ ND_COLUMN_IMAGE + " TEXT, "
    		+ ND_COLUMN_SHARE_URL + " TEXT, "
    		+ ND_COLUMN_GA_PREFIX + " TEXT, "
    		+ ND_COLUMN_TYPE + " TEXT);";
    		

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE_NEWS_DETAIL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS_DETAIL);
        onCreate(db);
	}

}
