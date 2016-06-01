package com.ty.izhihu.activity;

import com.ty.izhihu.R;
import com.ty.izhihu.fragment.NewsListFragment;
import com.ty.izhihu.fragment.ThemeFragment;
import com.ty.izhihu.utility.Tool;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ThemeActivity extends FragmentActivity {
	private FragmentManager manager;
	private ThemeFragment fragment;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_news_list);
		
		String date = getIntent().getStringExtra("date");
		
		
		
		Bundle bundle = new Bundle();
		bundle.putString("date", date);
		setTitle(Tool.formatDate(date));
		manager = getSupportFragmentManager();
		fragment = new ThemeFragment();
		fragment.setArguments(bundle);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frame_news_list, fragment);
		transaction.commit();
	}
}
