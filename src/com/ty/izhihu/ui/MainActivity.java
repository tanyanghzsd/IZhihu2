package com.ty.izhihu.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.ty.izhihu.R;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.db.DailyNewsDB;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadNewsTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/*
 * 从一个item点击进去出现右上角收藏标志没有变化（已经被收藏的没有变化）
 * 已解决！
 */
public class MainActivity extends BaseActivity implements OnRefreshListener,OnItemClickListener{

	
	private static final String IS_FAVORITE = "is_favorite";
	private static final String NEWS = "news";
	private SwipeRefreshLayout refreshLayout;
	private ListView lv;
	private NewsAdapter adapter;
	private boolean isFavorite=false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
		initListeners();
		initDatas();
		
	}

	void initViews(){
		setTitle(getTime());
		refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
		refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
		
		lv = (ListView) findViewById(R.id.lv);
		adapter = new NewsAdapter(this, R.layout.listview_item);
		lv.setAdapter(adapter);
	}
	
	void initListeners(){
		refreshLayout.setOnRefreshListener(this);
		lv.setOnItemClickListener(this);
	}
	
	void initDatas(){
		if(NetUtils.isConnected(this)){
			new LoadNewsTask(adapter).execute();
		}else{
			showMsg(getString(R.string.network_is_useless));
		}
	}
	
	/*
	 * SwipeRefreshLayout调用该方法刷新，在此方法中刷新
	 */
	@Override
	public void onRefresh() {
		 if (NetUtils.isConnected(this)) {
	            new LoadNewsTask(adapter, new OnFinishListener() {
	                @Override
	                public void afterTaskFinish() {
	                    refreshLayout.setRefreshing(false);
	                }
	            }).execute();
	        } else {
	        	showMsg(getString(R.string.network_is_useless));
	        	
	            refreshLayout.setRefreshing(false);
	            
	            
	        }
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST+1, 0, getResources().getString(R.string.my_favortie));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==Menu.FIRST+1){
			
			startActivity(new Intent(MainActivity.this,FavoriteActivity.class));
		}
		return true;
	}
	/*
	 * 当ListView的一个item被点击时调用该方法
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MainActivity.this,NewsDetailActivity.class);
		News item = adapter.getItem(position);
		isFavorite=DailyNewsDB.getInstance(this).isFavoriteSaved(item);
		intent.putExtra(NEWS, adapter.getItem(position));
		intent.putExtra(IS_FAVORITE, isFavorite);
		startActivity(intent);
	}

	
	
	public String getTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format));
        return format.format(c.getTime());

    }
	
	
}
