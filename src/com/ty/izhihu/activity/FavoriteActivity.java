package com.ty.izhihu.activity;

import com.ty.izhihu.R;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.db.DailyNewsDB;
import com.ty.izhihu.fragment.FavoriteFragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

/*
 * 长按删除一个item后，画面没有及时更新（被删除的item仍然存在，在 退出重新进入后才没有）
 * 已解决！
 */
public class FavoriteActivity extends FragmentActivity{
	
	
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private FavoriteFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_favorite);
		manager = getSupportFragmentManager();
		transaction = manager.beginTransaction();
		fragment = new FavoriteFragment();
		transaction.replace(R.id.action_favorite, fragment);
		transaction.commit();
		

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	
	
}
