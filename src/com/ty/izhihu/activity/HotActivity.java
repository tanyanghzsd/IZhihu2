package com.ty.izhihu.activity;

import com.ty.izhihu.R;
import com.ty.izhihu.fragment.HotFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class HotActivity extends FragmentActivity {
	private FragmentManager manager;
	private HotFragment hotFragment;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle arg0) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_history);
		
		manager = getSupportFragmentManager();
		hotFragment = new HotFragment();
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frame_history, hotFragment);
		transaction.commit();
	}
}
