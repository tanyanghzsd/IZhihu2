package com.ty.izhihu.task;

import java.util.List;

import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;

import android.os.AsyncTask;

public class LoadNewsTask extends AsyncTask<Void, Void, List<News>> {

	private NewsAdapter mNewsAdapter;
	private OnFinishListener listener; 
	
	public LoadNewsTask(NewsAdapter newsAdapter) {
		// TODO Auto-generated constructor stub
		this.mNewsAdapter=newsAdapter;
	}
	
	public LoadNewsTask(NewsAdapter newsAdapter,OnFinishListener listener) {
		// TODO Auto-generated constructor stub
		this.mNewsAdapter=newsAdapter;
		this.listener=listener;
	}
	
	@Override
	protected List<News> doInBackground(Void... arg0) {
		List<News> lists = null;
		try {
			lists = JsonHelper.parseJsonToList(Http.getLastNewsList());
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return lists;
		}
	}
	
	@Override
	protected void onPostExecute(List<News> result) {
		mNewsAdapter.refreshNewsList(result);
		if(listener!=null){
			listener.afterTaskFinish();
		}
	}

}
