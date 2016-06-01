package com.ty.izhihu.activity;

import com.ty.izhihu.R;
import com.ty.izhihu.fragment.SelectionFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class SelectionActivity extends FragmentActivity{
	
	private FragmentManager manager;
	private SelectionFragment fragment;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_selection);
		
		manager = getSupportFragmentManager();
		fragment = new SelectionFragment();
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frame_selection, fragment);
		transaction.commit();
		
	}
}
