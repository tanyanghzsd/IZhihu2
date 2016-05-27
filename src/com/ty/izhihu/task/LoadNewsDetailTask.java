package com.ty.izhihu.task;

import com.ty.izhihu.app.Constants;
import com.ty.izhihu.bean.NewsDetail;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;

import android.os.AsyncTask;
import android.webkit.WebView;

public class LoadNewsDetailTask extends AsyncTask<Long, Void, NewsDetail>{
	public static Object object;
	private WebView mWebView;
	private OnFinishListener listener; 
	
	public LoadNewsDetailTask(WebView mWebView,OnFinishListener listener) {
		this.mWebView=mWebView;
		this.listener=listener;
	}
	
	public  LoadNewsDetailTask(WebView mWebView) {
		this.mWebView=mWebView;
	}
	
	@Override
	protected void onPreExecute() {
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		progressDialog.show();
	}
	
	@Override
	protected NewsDetail doInBackground(Long... arg0) {
		NewsDetail newsDetail=null;
		try {
			newsDetail=JsonHelper.parseJsonToDetail(Http.getNewsDetail(arg0[0]));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return newsDetail;
		}
	}

	@Override
	protected void onPostExecute(NewsDetail mNewsDetail) {
		mWebView.loadUrl(mNewsDetail.getShare_url());
		Constants.NEWS_DETAIL=mNewsDetail;	
			
			
		

	}
	
}
