package com.ty.izhihu.fragment;

import com.ty.izhihu.R;
import com.ty.izhihu.adapter.CommentAdapter;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadCommentsTask;
import com.ty.izhihu.utility.Tool;
import com.ty.izhihu.widget.RefreshLayout;
import com.ty.izhihu.widget.RefreshLayout.OnLoadListener;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

public class CommentFragment extends Fragment implements OnRefreshListener,OnLoadListener,OnTouchListener{
	
	
	private ListView lv;
	private CommentAdapter adapter;
	private long id;
	private RefreshLayout refreshLayout;
	private float startY;
	private float startX;
	private long hot_news_id;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_comment, container, false);
		lv = (ListView) view.findViewById(R.id.lv_comment);
		refreshLayout = (RefreshLayout) view.findViewById(R.id.refresh_layout_comment);
		refreshLayout.setOnRefreshListener(this);
		refreshLayout.setOnLoadListener(this);
		refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
		
		adapter = new CommentAdapter(getActivity(), R.layout.listview_item_comment);
		lv.setAdapter(adapter);
		lv.setOnTouchListener(this);
		Bundle bundle = getArguments();
		if(bundle.getLong("id")>0){
			id = bundle.getLong("id");
		}
		if(bundle.getLong("hot_news_id")>0){
			hot_news_id = bundle.getLong("hot_news_id");
		}
		
		initDatas();
		
		return view;
	}
	
	void initDatas(){
		if(NetUtils.isConnected(getActivity())){
			if(id>0){
				new LoadCommentsTask(adapter,new OnFinishListener() {
					
					private ProgressDialog progressDialog;

					@Override
					public void afterTaskFinish() {
						
					}

					@Override
					public void beforeTaskBeging() {
						
					}
				}).execute(id);
			}
			if(hot_news_id>0){
				new LoadCommentsTask(adapter,new OnFinishListener() {
					
					private ProgressDialog progressDialog;

					@Override
					public void afterTaskFinish() {
						
					}

					@Override
					public void beforeTaskBeging() {
						
					}
				}).execute(hot_news_id);
			}
			
			
		}else{
			Tool.showMsg(getActivity(),getString(R.string.network_is_useless));
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		refreshLayout.setRefreshing(false);
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		refreshLayout.setLoading(false);
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = event.getY();
			startX = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			
			
			break;
		case MotionEvent.ACTION_UP:
			float y2 = event.getY();
			float x2 = event.getX();
			
			if(x2-startX>Tool.getScreenWidth(getActivity())/2){
				getActivity().finish();
			}
			
				
			break;
		default:
			break;
		}
		return false;
	}
}
