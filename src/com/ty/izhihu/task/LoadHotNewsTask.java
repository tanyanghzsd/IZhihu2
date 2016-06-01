package com.ty.izhihu.task;

import java.util.List;

import com.ty.izhihu.adapter.HotAdapter;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.bean.HotNews;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;

import android.os.AsyncTask;

public class LoadHotNewsTask extends AsyncTask<Void, Void, List<HotNews>> {

	private HotAdapter mNewsAdapter;
	private OnFinishListener listener; 
	
	public LoadHotNewsTask(HotAdapter newsAdapter) {
		// TODO Auto-generated constructor stub
		this.mNewsAdapter=newsAdapter;
	}
	
	public LoadHotNewsTask(HotAdapter newsAdapter,OnFinishListener listener) {
		// TODO Auto-generated constructor stub
		this.mNewsAdapter=newsAdapter;
		this.listener=listener;
	}
	
	@Override
	protected List<HotNews> doInBackground(Void... arg0) {
		List<HotNews> lists = null;
		try {
			lists = JsonHelper.parseJsonToHotNews(Http.getHotNews());
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return lists;
		}
	}
	
	@Override
	protected void onPostExecute(List<HotNews> result) {
		mNewsAdapter.refreshNewsList(result);
		if(listener!=null){
			listener.afterTaskFinish();
		}
	}

}
