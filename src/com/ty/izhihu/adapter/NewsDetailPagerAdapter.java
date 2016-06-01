package com.ty.izhihu.adapter;

import com.ty.izhihu.fragment.NewsDetailFragment;
import com.ty.izhihu.fragment.NewsFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NewsDetailPagerAdapter extends FragmentPagerAdapter{

	private final String[] TITLES={"最新消息","过往消息","我的收藏"};
	private final int NUM_OF_PAGERS=TITLES.length;
	
	public NewsDetailPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NUM_OF_PAGERS;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return TITLES[position];
	}

	@Override
	public Fragment getItem(int position) {
		NewsDetailFragment fragment = new NewsDetailFragment();
		return fragment;
	}


}
