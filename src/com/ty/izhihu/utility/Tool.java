package com.ty.izhihu.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ty.izhihu.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class Tool {
	private final static String DATE_FORMAT="yyyy/MM/d,EEE";
	private final static String DATE_FORMAT_FOR_PARA="yyyyMMdd";
	private static Calendar calendar;
	private static List<String> lists;
	
	@SuppressLint("SimpleDateFormat")
	public static String getTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(c.getTime());

    }
	
	public static List<String> getLastSevenDays() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FOR_PARA);
	    lists = new ArrayList<String>();   
	    
        for (int i = 0; i < 7; i++) {
        	calendar = Calendar.getInstance();
        	calendar.add(Calendar.DATE, - i);
        	Date monday = calendar.getTime();
            String preMonday = sdf.format(monday);
            lists.add(preMonday);
		}
        return lists;
    }
	
	public static List<String> getNextSevenDays(){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FOR_PARA);
		int i = 0;
        while (i<7) {
        	calendar = Calendar.getInstance();
        	calendar.add(Calendar.DATE, - (lists.size()));
        	Date monday = calendar.getTime();
            String preMonday = sdf.format(monday);
            lists.add(preMonday);
            i++;
		}
        
        return lists;
	}
	
	public static String formatDate(String date){
		return date.substring(0, 4)+"/"+date.charAt(4)+date.charAt(5)+"/"+date.charAt(6)+date.charAt(7);
	}
	
	public static void showMsg(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void loadImage(String uri,ImageView imageView){
		ImageLoader imageLoader = ImageLoader.getInstance();
	    DisplayImageOptions options = new DisplayImageOptions.Builder()
	            .showImageOnLoading(R.drawable.no_image)
	            .showImageOnFail(R.drawable.no_image)
	            .showImageForEmptyUri(R.drawable.no_image)
	            .cacheInMemory(true)
	            .cacheOnDisk(true)
	            .considerExifParams(true)
	            .build();
	    
	    imageLoader.displayImage(uri, imageView, options);
	}
	
	public static String getVersionName(Context context) {
		
		return getPackageInfo(context).versionName;
	}

	//版本号
	public static int getVersionCode(Context context) {
		return getPackageInfo(context).versionCode;
	}
	
	private static PackageInfo getPackageInfo(Context context) {
		PackageInfo pi = null;

		try {
			PackageManager pm = context.getPackageManager();
			pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);

			return pi;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pi;
	}
	
	public static int getScreenWidth(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
	
	public static int getScreenHeight(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 分享功能
	 * 
	 * @param context
	 *            上下文
	 * @param activityTitle
	 *            Activity的名字
	 * @param msgTitle
	 *            消息标题
	 * @param msgText
	 *            消息内容
	 * @param imgPath
	 *            图片路径，不分享图片则传null
	 */
	public static void shareMsg(Context context,String activityTitle, String msgTitle, String msgText,
			String imgPath) {
		android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_SEND);
		if (imgPath == null || imgPath.equals("")) {
			intent.setType("text/plain"); // 纯文本
		} else {
			File f = new File(imgPath);
			if (f != null && f.exists() && f.isFile()) {
				intent.setType("image/jpg");
				Uri u = Uri.fromFile(f);
				intent.putExtra(Intent.EXTRA_STREAM, u);
			}
		}
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, msgTitle);
		intent.putExtra(android.content.Intent.EXTRA_TEXT, msgText);
		intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(android.content.Intent.createChooser(intent, activityTitle));
	}
	
	public static int getRandomHeight(){
		Random random = new Random();
		int nextInt = random.nextInt(100);
		return nextInt+100;
		
	}
	
	public static int adjustPosition(int position){
		int pos = 0;
		switch (position) {
		case 0:
			pos=1;
			break;
		case 1:
			pos=2;
			break;
		case 2:
			pos=5;
			break;
		case 3:
			pos=6;
			break;
		case 4:
			pos=12;
			break;
		case 5:
			pos=9;
			break;
		case 6:
			pos=14;
			break;
		case 7:
			pos=8;
			break;
		case 8:
			pos=3;
			break;
		case 9:
			pos=10;
			break;
		case 10:
			pos=11;
			break;
		case 11:
			pos=7;
			break;
		case 12:
			pos=15;
			break;
		case 13:
			pos=16;
			break;
		case 14:
			pos=17;
			break;
		case 15:
			pos=19;
			break;
		case 16:
			pos=20;
			break;
		case 17:
			pos=21;
			break;
		case 18:
			pos=23;
			break;
		case 19:
			pos=24;
			break;
		case 20:
			pos=25;
			break;
		case 21:
			pos=26;
			break;
		case 22:
			pos=27;
			break;
		case 23:
			pos=28;
			break;
		case 24:
			pos=29;
			break;
		case 25:
			pos=30;
			break;
		case 26:
			pos=31;
			break;
		case 27:
			pos=32;
			break;
		case 28:
			pos=33;
			break;
		case 29:
			pos=34;
			break;
		default:
			break;
		}
		return pos;
	}
}
