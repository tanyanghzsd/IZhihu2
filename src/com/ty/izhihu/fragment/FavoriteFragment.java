package com.ty.izhihu.fragment;

import com.ty.izhihu.R;
import com.ty.izhihu.activity.NewsDetailActivity;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.db.DailyNewsDB;
import com.ty.izhihu.utility.Tool;
import com.ty.izhihu.widget.RefreshLayout;
import com.ty.izhihu.widget.RefreshLayout.OnLoadListener;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.support.v7.widget.CardView;
public class FavoriteFragment extends Fragment implements OnRefreshListener,OnLoadListener,OnItemClickListener,OnItemLongClickListener{
	private static final String NEWS = "news";
	private static final String IS_FAVORITE = "is_favorite";
	private static final String DELETE_FAVORITE = "删除该收藏";
	private boolean isFavorite;
	private ListView lv;
	private NewsAdapter adapter;
	private View view;
	private RefreshLayout refreshLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_favorite, container, false);
		initViews();
		
		return view;
	}
	
	void initViews(){
		lv=(ListView) view.findViewById(R.id.lv_favorite);
		adapter = new NewsAdapter(getActivity(), R.layout.listview_item, R.id.lv_favorite, DailyNewsDB.getInstance(getActivity()).LoadAllFavoriteNews());
		
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		
		refreshLayout = (RefreshLayout) view.findViewById(R.id.refresh_layout_favorite);
		refreshLayout.setColorSchemeResources(
				android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
		refreshLayout.setOnRefreshListener(this);
		refreshLayout.setOnLoadListener(this);
	}
	
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
		isFavorite=DailyNewsDB.getInstance(getActivity()).isFavoriteSaved(adapter.getItem(position).getId());
		intent.putExtra(IS_FAVORITE, isFavorite);
		intent.putExtra(NEWS, adapter.getItem(position));
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
		Builder builder = new AlertDialog.Builder(getActivity());
		builder.setItems(new String[]{DELETE_FAVORITE}, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				DailyNewsDB.getInstance(getActivity()).deleteFavoriteNews(adapter.getItem(position));
				adapter.refreshNewsList(DailyNewsDB.getInstance(getActivity()).LoadAllFavoriteNews());
				adapter.notifyDataSetInvalidated();
				Tool.showMsg(getActivity(),getString(R.string.delete_success));
			}
		}).show();
		
		return true;
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		refreshLayout.setLoading(false);
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		refreshLayout.setRefreshing(false);
		adapter.refreshNewsList(DailyNewsDB.getInstance(getActivity()).LoadAllFavoriteNews());
		adapter.notifyDataSetInvalidated();
	}
	
	
}
