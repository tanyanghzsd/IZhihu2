package com.ty.izhihu.fragment;
import android.support.v7.widget.CardView;
import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.ty.izhihu.R;
import com.ty.izhihu.activity.NewsDetailActivity;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.adapter.SelectionDetailAdapter;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadHistoryNewsTask;
import com.ty.izhihu.task.LoadNewsListTask;
import com.ty.izhihu.task.LoadNewsTask;
import com.ty.izhihu.task.LoadSelectionDetailListTask;
import com.ty.izhihu.task.LoadThemeListTask;
import com.ty.izhihu.task.LoadThemeSecondListTask;
import com.ty.izhihu.utility.Tool;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewsListFragment extends Fragment implements OnItemClickListener{
	private ListView lv;
	private View view;
	private String date;
	private int theme_id;
	private NewsAdapter adapter;
	private int selection_id;

	//test
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==0){
								Tool.showMsg(getActivity(), "selection_id:"+msg.obj);
				//				Log.d("info", (String) msg.obj);
				try {
					List<News> lists = JsonHelper.parseJsonToList((String) msg.obj);
					adapter = new NewsAdapter(getActivity(), R.layout.listview_item,0, lists);
					lv.setAdapter(adapter);
					Log.d("INFO", lists.get(0).getTitle());

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_news_list, container, false);
		lv = (ListView) view.findViewById(R.id.lv_news_list);

		initDatas();
		lv.setOnItemClickListener(this);

		return view;
	}



	/*
	 * 当ListView的一个item被点击时调用该方法
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(),NewsDetailActivity.class);

		intent.putExtra("news",adapter.getItem(position));

		startActivity(intent);
	}

	void initDatas(){
		Bundle bundle = getArguments();
		date = bundle.getString("date");
		
		//theme_id
		theme_id = bundle.getInt("theme_id");

		selection_id = bundle.getInt("selection_news_id");
		
		if(NetUtils.isConnected(getActivity())){
			
			if(selection_id>=0){
//			new LoadSelectionDetailListTask(adapter).execute(selection_id+"");


				//test
				new Thread(new Runnable() {

					@Override
					public void run() {
						String string;
						try {
							string = Http.getSelectionDetailList(selection_id);
							Log.i("STRINGURL", string);
							Message msg = new Message();
							msg.obj=string;
							msg.what=0;
							handler.sendMessage(msg);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}
				}).start();
			}
			if(theme_id>=0){
				adapter = new NewsAdapter(getActivity(), R.layout.listview_item);
				lv.setAdapter(adapter);
				new LoadThemeSecondListTask(adapter).execute(theme_id+"");

			}
			if(!TextUtils.isEmpty(date)){
				adapter = new NewsAdapter(getActivity(), R.layout.listview_item);
				lv.setAdapter(adapter);
				new LoadNewsListTask(adapter).execute(date);
			}
		}else{
			Tool.showMsg(getActivity(), getResources().getString(R.string.network_is_useless));

		}


	}





}
