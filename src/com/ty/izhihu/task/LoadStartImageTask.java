package com.ty.izhihu.task;

import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;
import com.ty.izhihu.utility.Tool;

import android.os.AsyncTask;
import android.widget.ImageView;

public class LoadStartImageTask extends AsyncTask<Void, Void, String>{
	private ImageView imageView;
	
	public LoadStartImageTask(ImageView imageView) {
		// TODO Auto-generated constructor stub
		this.imageView=imageView;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		String result="";
		try {
			result = JsonHelper.parseJsonToStartImage(Http.getStartImage());
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return result;
		}
	}

	@Override
	protected void onPostExecute(String result) {
		Tool.loadImage(result, imageView);
	}
}
