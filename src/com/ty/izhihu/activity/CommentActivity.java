package com.ty.izhihu.activity;

import com.ty.izhihu.R;
import com.ty.izhihu.fragment.CommentFragment;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.Window;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class CommentActivity extends FragmentActivity{
	private FragmentManager manager;
	private CommentFragment fragment;
	private FragmentTransaction transaction;
	private long id;
	private long hot_news_id;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setTitle("评论");
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setIcon(R.drawable.userinfo_navigationbar_back);
		
		setContentView(R.layout.activity_comment);
		
		id = getIntent().getExtras().getLong("id");
		hot_news_id = getIntent().getExtras().getLong("hot_news_id");
		Bundle bundle = new Bundle();
		if(id>0){
			bundle.putLong("id", id);
		}
		if(hot_news_id>0){
			bundle.putLong("hot_news_id", hot_news_id);
		}
		
		
		
		manager = getSupportFragmentManager();
		fragment = new CommentFragment();
		fragment.setArguments(bundle);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frame_comment, fragment);
		transaction.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
}
