package com.ty.izhihu.ui;

import com.ty.izhihu.R;
import com.ty.izhihu.app.Constants;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.NewsDetail;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.db.DailyNewsDB;
import com.ty.izhihu.db.NewsDetailDB;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadNewsDetailTask;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class NewsDetailActivity extends BaseActivity {
	private static final String IS_FAVORITE = "is_favorite";
	private WebView webView;
	private boolean isFavorite;
	private News news;
	private long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		webView=(WebView) findViewById(R.id.webview);
		setWebView(webView);

		Intent intent = getIntent();
		isFavorite=intent.getBooleanExtra(IS_FAVORITE, false);
		news = (News) intent.getSerializableExtra("news");
		id = news.getId();

		if(NewsDetailDB.getInstance(this).isExist(id))
		{
			loadContent();
		}
		else 
		{
			if(NetUtils.isConnected(this))
			{
				new LoadNewsDetailTask(webView,new OnFinishListener() {

					@Override
					public void afterTaskFinish() {
						NewsDetailDB.getInstance(NewsDetailActivity.this)
						.saveNewsDetail(Constants.NEWS_DETAIL);
					}
				}).execute(news.getId());

			} 
			else
			{

				showMsg(getString(R.string.network_is_useless));
			}
		}


	}

	void loadContent(){
		NewsDetail mNewsDetail = NewsDetailDB.getInstance(this).getNewsDetailById(id);
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
		webView.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void setWebView(WebView mWebView) {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setVerticalScrollBarEnabled(true);
		mWebView.setHorizontalScrollBarEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_detail, menu);
		if(isFavorite)
			menu.getItem(0).setIcon(R.drawable.fav_active);
		else
			menu.getItem(0).setIcon(R.drawable.fav_normal);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.action_favorite){
			if(isFavorite){
				isFavorite=false;
				DailyNewsDB.getInstance(NewsDetailActivity.this).deleteFavoriteNews(news);
				item.setIcon(R.drawable.fav_normal);

				showMsg(getString(R.string.cancel_favorite));
			}else{
				isFavorite=true;
				DailyNewsDB.getInstance(NewsDetailActivity.this).saveFavoriteNews(news);
				item.setIcon(R.drawable.fav_active);
				showMsg(getString(R.string.is_favorited));
			}
		}

		return true;

	}

	
}
