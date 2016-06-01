package com.ty.izhihu.task;

import com.ty.izhihu.app.IConstants;
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
		listener.beforeTaskBeging();
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
		
		if(listener!=null){
			mWebView.loadUrl(mNewsDetail.getShare_url());
			
//			load(mNewsDetail);
			IConstants.NEWS_DETAIL=mNewsDetail;
			listener.afterTaskFinish();
			
		}	
	}
	
	void load(NewsDetail mNewsDetail){
		String headerImage;
        if (mNewsDetail.getImage() == null || mNewsDetail.getImage() == "") {
            headerImage = "file:///android_asset/news_detail_header_image.jpg";

        } else {
            headerImage = mNewsDetail.getImage();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\">")
                .append(mNewsDetail.getTitle()).append("</h1>")
                .append("<span class=\"img-source\">")
                .append(mNewsDetail.getImage_source()).append("</span>")
                .append("<img src=\"").append(headerImage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");
        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + mNewsDetail.getBody().replace("<div class=\"img-place-holder\">", sb.toString());
        mWebView.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);
    }
	
}
