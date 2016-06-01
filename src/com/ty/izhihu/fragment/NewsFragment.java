package com.ty.izhihu.fragment;
import android.support.v7.widget.CardView;
import com.ty.izhihu.R;
import com.ty.izhihu.activity.NewsDetailActivity;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadNewsTask;
import com.ty.izhihu.utility.Tool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class NewsFragment extends Fragment implements OnRefreshListener,OnItemClickListener{
	private SwipeRefreshLayout refreshLayout;
	private ListView lv;
	private View view;
	private NewsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_news, container, false);
		initViews();
		initListeners();
		initDatas();
		return view;
	}

	void initViews(){
		refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
		refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
		
		lv = (ListView) view.findViewById(R.id.lv);
		adapter = new NewsAdapter(getActivity(), R.layout.listview_item);
		lv.setAdapter(adapter);
	}
	
	void initListeners(){
		refreshLayout.setOnRefreshListener(this);
		lv.setOnItemClickListener(this);
	}

	/*
	 * 当ListView的一个item被点击时调用该方法
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < adapter.getCount(); i++) {
			sb.append(adapter.getItem(i)+",");
		}
		String idLists = sb.toString();
		intent.putExtra("news",adapter.getItem(position));
		intent.putExtra("all_id", idLists);
		startActivity(intent);
	}

	/*
	 * SwipeRefreshLayout调用该方法刷新，在此方法中刷新
	 */
	@Override
	public void onRefresh() {
		 if (NetUtils.isConnected(getActivity())) {
	            new LoadNewsTask(adapter, new OnFinishListener() {
	                @Override
	                public void afterTaskFinish() {
	                    refreshLayout.setRefreshing(false);
	                }

					@Override
					public void beforeTaskBeging() {
						// TODO Auto-generated method stub
						
					}
	            }).execute();
	        } else {
	        	Tool.showMsg(getActivity(),getString(R.string.network_is_useless));
	            refreshLayout.setRefreshing(false);
	        }
	}
	
	
	
	
	void initDatas(){
		if(NetUtils.isConnected(getActivity())){
			new LoadNewsTask(adapter).execute();
		}else{
			Tool.showMsg(getActivity(),getString(R.string.network_is_useless));
		}
	}
	
	
	
}
