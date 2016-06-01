package com.ty.izhihu.fragment;
import android.support.v7.widget.CardView;
import com.ty.izhihu.R;
import com.ty.izhihu.activity.MainActivity;
import com.ty.izhihu.activity.NewsDetailActivity;
import com.ty.izhihu.activity.NewsListActivity;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.adapter.ThemeAdapter;
import com.ty.izhihu.bean.Theme;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadNewsListTask;
import com.ty.izhihu.task.LoadStartImageTask;
import com.ty.izhihu.task.LoadThemeListTask;
import com.ty.izhihu.utility.Tool;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ThemeFragment extends android.support.v4.app.Fragment implements OnItemClickListener{
	private ListView lv;
	private View view;
	private ThemeAdapter adapter;
	private String date;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_news_list, container, false);
		lv = (ListView) view.findViewById(R.id.lv_news_list);
		adapter = new ThemeAdapter(getActivity(), R.layout.listview_item_theme);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		initDatas();
		return view;
	}

	

	/*
	 * 当ListView的一个item被点击时调用该方法
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Theme theme = adapter.getItem(position);
		Intent intent = new Intent(getActivity(),NewsListActivity.class);
		int theme_id = theme.getId();
		String theme_name = theme.getName();
		String theme_tag=theme_id+","+theme_name;
		intent.putExtra("theme_tag", theme_tag);
		
		startActivity(intent);
	}

	
	
	
	
	
	void initDatas(){
		Bundle bundle = getArguments();
		if(NetUtils.isConnected(getActivity())){
			new LoadThemeListTask(adapter).execute();
		}else{
			Tool.showMsg(getActivity(), getResources().getString(R.string.network_is_useless));
			
		}
		
	}
}
