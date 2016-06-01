package com.ty.izhihu.fragment;

import java.util.List;

import com.ty.izhihu.R;
import com.ty.izhihu.activity.MainActivity;
import com.ty.izhihu.activity.NewsListActivity;
import com.ty.izhihu.adapter.HistoryAdapter;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadHistoryNewsTask;
import com.ty.izhihu.task.LoadNewsTask;
import com.ty.izhihu.utility.Tool;
import com.ty.izhihu.widget.RefreshLayout;
import com.ty.izhihu.widget.RefreshLayout.OnLoadListener;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.support.v7.widget.CardView;
public class HistoryFragment extends Fragment implements OnItemClickListener,OnRefreshListener,OnLoadListener{

	private ListView lv;
	private HistoryAdapter adapter;
	private RefreshLayout refreshLayout;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_history, container,false);
		
		initViews();
		initDatas();
		
		return view;
	}
	
	void initViews(){
		lv = (ListView) view.findViewById(R.id.lv_history);
		adapter = new HistoryAdapter(getActivity(), R.layout.listview_item);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		
		refreshLayout = (RefreshLayout) view.findViewById(R.id.refresh_layout_history);
		refreshLayout.setColorSchemeResources(
				android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
		refreshLayout.setOnRefreshListener(this);
		refreshLayout.setOnLoadListener(this);
	}
	
	void initDatas(){
		if(NetUtils.isConnected(getActivity())){
			String[] paras=new String[]{};
			List<String> dates=Tool.getLastSevenDays();
			
			new LoadHistoryNewsTask(adapter,new OnFinishListener() {
				
				private ProgressDialog progressDialog;

				@Override
				public void afterTaskFinish() {
					// TODO Auto-generated method stub
					if(progressDialog!=null){
						progressDialog.dismiss();
					}
				}

				@Override
				public void beforeTaskBeging() {
					progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
					progressDialog.setMessage(getActivity().getString(R.string.loading));
					progressDialog.show();
					
				}
			}).execute(dates.toArray(paras));
			
		}else{
			Tool.showMsg(getActivity(),getString(R.string.network_is_useless));
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent=new Intent(getActivity(),NewsListActivity.class);
		String date = adapter.getItem(position).getDate();
		intent.putExtra("date", date);
		startActivity(intent);
	}

	@Override
	public void onRefresh() {
		// 下拉刷新
		refresh();
	}

	@Override
	public void onLoad() {
		// 上拉加载
		refresh();
	}
	
	void refresh(){
		if(NetUtils.isConnected(getActivity())){
			String[] paras=new String[]{};
			List<String> dates=Tool.getNextSevenDays();
			
			new LoadHistoryNewsTask(adapter,new OnFinishListener() {
				

				@Override
				public void afterTaskFinish() {
					refreshLayout.setRefreshing(false);
					refreshLayout.setLoading(false);
				}

				@Override
				public void beforeTaskBeging() {
					
				}
			}).execute(dates.toArray(paras));
			
			
		}else{
			refreshLayout.setRefreshing(false);
			refreshLayout.setLoading(false);
			Tool.showMsg(getActivity(),getString(R.string.network_is_useless));
		}
	}
}
