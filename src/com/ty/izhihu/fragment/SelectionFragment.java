package com.ty.izhihu.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ty.izhihu.R;
import com.ty.izhihu.activity.NewsListActivity;
import com.ty.izhihu.adapter.CommentAdapter;
import com.ty.izhihu.adapter.MyItemClickListener;
import com.ty.izhihu.adapter.MyItemLongClickListener;
import com.ty.izhihu.adapter.SelectionAdapter;
import com.ty.izhihu.app.IConstants;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.bean.Theme;
import com.ty.izhihu.db.ThemeDB;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadCommentsTask;
import com.ty.izhihu.task.LoadSelectionListTask;
import com.ty.izhihu.utility.Tool;
import com.ty.izhihu.widget.RefreshLayout;
import com.ty.izhihu.widget.RefreshLayout.OnLoadListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SelectionFragment extends Fragment implements 
									   OnRefreshListener,
									   OnLoadListener,
									   MyItemClickListener,
									   MyItemLongClickListener{
	
	
	private RecyclerView recyclerView;
	private SelectionAdapter adapter;
	private RefreshLayout refreshLayout;
	private float startY;
	private float startX;
	private StaggeredGridLayoutManager staggeredGridLayoutManager;
	private List<Theme> lists=new ArrayList<Theme>();
	private GridLayoutManager gridLayoutManager;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_selection, container, false);
		
		initDatas();
		
		return view;
	}
	
	void initDatas(){
		
		adapter = new SelectionAdapter(getActivity());
		adapter.setOnItemClickListener(this);
		adapter.setOnItemLongClickListener(this);
		
		recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		refreshLayout = (RefreshLayout) view.findViewById(R.id.refresh_layout_selection);
		refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
		refreshLayout.setOnRefreshListener(this);
		refreshLayout.setOnLoadListener(this);
		
		staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,LinearLayout.VERTICAL);
		recyclerView.setLayoutManager(staggeredGridLayoutManager);
		recyclerView.setAdapter(adapter);
		
		if(NetUtils.isConnected(getActivity())){
			new LoadSelectionListTask(adapter).execute();
		}else{
			Tool.showMsg(getActivity(), getString(R.string.network_is_useless));
		}
		
		
	}

	@Override
	public void onRefresh() {
		
		if(NetUtils.isConnected(getActivity())){
			new LoadSelectionListTask(adapter,new OnFinishListener() {
				
				@Override
				public void beforeTaskBeging() {
				}
				
				@Override
				public void afterTaskFinish() {
					refreshLayout.setRefreshing(false);
					
				}
			}).execute();
		}else{
			refreshLayout.setRefreshing(false);
			Tool.showMsg(getActivity(), getString(R.string.network_is_useless));
		}
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		refreshLayout.setLoading(false);
	}

	@Override
	public void onItemLongClick(View view, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(View view, int position) {
		
		Intent intent = new Intent();
		intent.setClass(getActivity(), NewsListActivity.class);
		
		
		/*
		 * 每个专题都有name，那么必然有一个id，通过name能够找到id，那么在解析selection时就将
		 * 它存到数据库中
		 */
		String selection_name = adapter.getItem(position).getName();
		Theme theme = ThemeDB.getInstance(getActivity()).LoadThemeByName(selection_name);
		int pos = theme.getId();
		
		intent.putExtra("selection_news_id",pos);
		
		intent.putExtra("selection_news_name",selection_name);
		
		startActivity(intent);
		
	}
	

}
