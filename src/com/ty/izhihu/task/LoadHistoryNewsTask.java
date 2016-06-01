package com.ty.izhihu.task;

import java.util.ArrayList;
import java.util.List;

import com.ty.izhihu.adapter.HistoryAdapter;
import com.ty.izhihu.bean.HistoryNews;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;

import android.os.AsyncTask;

public class LoadHistoryNewsTask extends AsyncTask<String, Void, List<HistoryNews>> {

	private HistoryAdapter adapter;
	private OnFinishListener listener; 
	
	public LoadHistoryNewsTask(HistoryAdapter newsAdapter) {
		// TODO Auto-generated constructor stub
		this.adapter=newsAdapter;
	}
	
	public LoadHistoryNewsTask(HistoryAdapter adapter,OnFinishListener listener) {
		// TODO Auto-generated constructor stub
		this.adapter=adapter;
		this.listener=listener;
	}
	
	@Override
	protected void onPreExecute() {
		if(listener!=null){
			//listener.beforeTaskBeging();
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	protected List<HistoryNews> doInBackground(String... arg0) {
		List<String> lists=new ArrayList<String>();
		for(int i=0;i<arg0.length;i++){
			lists.add(arg0[i]);
		}
		List<HistoryNews> historyNewsLists = new ArrayList<HistoryNews>();
		try {
			historyNewsLists.addAll(JsonHelper.parseJsonToHistoryNews(Http.getHistoryNewsByDate(lists)));
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return historyNewsLists;
		}
	}
	
	@Override
	protected void onPostExecute(List<HistoryNews> result) {
		adapter.refreshNewsList(result);
		if(listener!=null){
			listener.afterTaskFinish();
		}
	}

}
