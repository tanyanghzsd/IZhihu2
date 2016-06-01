package com.ty.izhihu.activity;

import java.text.Format;
import java.text.SimpleDateFormat;

import com.ty.izhihu.R;
import com.ty.izhihu.bean.Theme;
import com.ty.izhihu.fragment.NewsListFragment;
import com.ty.izhihu.utility.Tool;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;

@SuppressLint("NewApi")
public class NewsListActivity extends FragmentActivity{

	private FragmentManager manager;
	private NewsListFragment newsListFragment;
	private FragmentTransaction transaction;
	private Intent intent;
	private String date=null;
	private int theme_id=-1;
	private String theme_name=null;
	private int selection_id=-1;
	private String selection_news_name=null;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		setContentView(R.layout.activity_news_list);

		initTitleBar();

		Bundle bundle = new Bundle();
		bundle.putString("date", date);
		bundle.putInt("theme_id", theme_id);
		bundle.putInt("selection_news_id", selection_id);


		manager = getSupportFragmentManager();
		newsListFragment = new NewsListFragment();
		newsListFragment.setArguments(bundle);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frame_news_list, newsListFragment);
		transaction.commit();
	}

	void initTitleBar(){
		
		intent = getIntent();

		//来自HistoryFragment的date，用来获取History详细列表
		if(!TextUtils.isEmpty(intent.getStringExtra("date"))){
			date = intent.getStringExtra("date");
			setTitle(Tool.formatDate(date));
		}


		//来自于ThemeFragment的id，用来获取theme详细列表
		if(!TextUtils.isEmpty(intent.getStringExtra("theme_tag"))){
			String theme_tag = intent.getStringExtra("theme_tag");
			Log.i("TAG", theme_tag);
			theme_id = Integer.parseInt(theme_tag.split(",")[0]);
			Log.i("TAG2", theme_id+"");
			theme_name = theme_tag.split(",")[1];
			setTitle(theme_name);
		}


		//来自SelectionFragment的id，用来获取selection详细列表
		if(!TextUtils.isEmpty(intent.getStringExtra("selection_news_name"))){
			selection_id = intent.getIntExtra("selection_news_id", 0);
			selection_news_name = intent.getStringExtra("selection_news_name");
			setTitle(selection_news_name);
		}
	}
}
