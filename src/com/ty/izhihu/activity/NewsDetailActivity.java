package com.ty.izhihu.activity;

import com.ty.izhihu.R;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.NewsDetail;
import com.ty.izhihu.db.DailyNewsDB;
import com.ty.izhihu.db.NewsDetailDB;
import com.ty.izhihu.fragment.NewsDetailFragment;
import com.ty.izhihu.fragment.NewsDetailFragment.OnGetNewsDetail;
import com.ty.izhihu.utility.Tool;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class NewsDetailActivity extends FragmentActivity implements OnPageChangeListener{
	
	private News news;
	private long id;
	private android.support.v4.app.FragmentTransaction transaction;
	private android.support.v4.app.FragmentManager manager;
	private NewsDetailFragment fragment;
	private ViewPager pager;
	private boolean isFavorite;
	private String shareUrl;
	private String hot_url;
	private long hot_news_id;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Intent intent = getIntent();
		Bundle bundle = new Bundle();
		
		if(intent.getSerializableExtra("news")!=null){
			news = (News) intent.getSerializableExtra("news");
			id = news.getId();
			isFavorite=DailyNewsDB.getInstance(this).isFavoriteSaved(id);
			bundle.putLong("id", id);
		}
		
		
		
		//来自HotFragment的hot_news_id
		if(!TextUtils.isEmpty(intent.getStringExtra("hot_url"))){
			hot_url = intent.getStringExtra("hot_url");
			bundle.putString("hot_url", hot_url);
		}
		if(intent.getLongExtra("hot_news_id", 0)>0){
			hot_news_id = intent.getLongExtra("hot_news_id", 0);
			isFavorite=DailyNewsDB.getInstance(this).isFavoriteSaved(hot_news_id);
			bundle.putLong("hot_news_id", hot_news_id);
		}
		
		
		
		fragment = new NewsDetailFragment(new OnGetNewsDetail() {
			
			@Override
			public void passNewsDetail(NewsDetail newsDetail) {
				// TODO Auto-generated method stub
				shareUrl=newsDetail.getShare_url();
			}
		});
		
		
		
		
		
		fragment.setArguments(bundle);
		
		manager = getSupportFragmentManager();
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frame_detail, fragment);
		transaction.commit();

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_detail, menu);
		if(isFavorite)
			menu.getItem(1).setIcon(R.drawable.fav_active);
		else
			menu.getItem(1).setIcon(R.drawable.fav_normal);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.action_favorite){
			if(isFavorite){
				isFavorite=false;
				if(news!=null){
					DailyNewsDB.getInstance(NewsDetailActivity.this).deleteFavoriteNews(news);
				}else{
					DailyNewsDB.getInstance(NewsDetailActivity.this).deleteFavoriteNews(hot_news_id);
				}
				
				item.setIcon(R.drawable.fav_normal);

				Tool.showMsg(NewsDetailActivity.this,getString(R.string.cancel_favorite));
			}else{
				isFavorite=true;
				if(news!=null){
					DailyNewsDB.getInstance(NewsDetailActivity.this).saveFavoriteNews(news);
				}else{
					DailyNewsDB.getInstance(NewsDetailActivity.this).saveFavoriteNews(hot_news_id);
				}
				
				item.setIcon(R.drawable.fav_active);
				Tool.showMsg(NewsDetailActivity.this,getString(R.string.is_favorited));
			}
		}
		
		if(item.getItemId()==R.id.action_share){
			if(news!=null){
				Tool.shareMsg(this, null, "分享到...", "知乎日报:"+"《"+news.getTitle()+"》"+"\n"+shareUrl, null);
			}else{
				Tool.shareMsg(this, null, "分享到...", "知乎日报"+"\n"+hot_url, null);
			}
			
		}
		
		if(item.getItemId()==R.id.action_back){
			finish();
		}

		return true;

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
