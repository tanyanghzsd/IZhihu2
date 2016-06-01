package com.ty.izhihu.adapter;

import com.ty.izhihu.activity.MainActivity;
import com.ty.izhihu.bean.OnGetPosition;
import com.ty.izhihu.fragment.FavoriteFragment;
import com.ty.izhihu.fragment.HistoryFragment;
import com.ty.izhihu.fragment.HotFragment;
import com.ty.izhihu.fragment.NewsFragment;
import com.ty.izhihu.fragment.SelectionFragment;
import com.ty.izhihu.fragment.ThemeFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends FragmentPagerAdapter {
	private Context context;
	private final String[] TITLES={"主题","热门","最新","历史","专栏","收藏"};
	private final int NUM_OF_PAGERS=TITLES.length;
	private OnGetPosition listener;
	
	public MyPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}
	
	public MyPagerAdapter(FragmentManager fragmentManager,OnGetPosition listener) {
		super(fragmentManager);
		this.listener=listener;
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
		Fragment fragment = new Fragment();
		switch (position) {
		case 0:
			fragment=new ThemeFragment();
			break;
		case 1:
			fragment=new HotFragment();
			break;
		case 2:
			fragment=new NewsFragment();
			break;
		case 3:
			fragment=new HistoryFragment();
			break;
		case 4:
			fragment=new SelectionFragment();
			break;
		case 5:
			fragment=new FavoriteFragment();
			break;
		default:
			break;
		}
		return fragment;
	}
	
	
	@Override
	public int getItemPosition(Object object) {
		View view=(View)object;
		
		int currentPagerIdx = listener.getCurrentPagerIdx();
		if(currentPagerIdx == (Integer)view.getTag()){  
            return POSITION_NONE;  
        }else{  
            return POSITION_UNCHANGED;    
        } 
	}
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	
	
	
}
