package com.ty.izhihu.task;

import java.util.ArrayList;
import java.util.List;

import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.adapter.SelectionDetailAdapter;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.bean.Selection;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;

import android.os.AsyncTask;

public class LoadSelectionDetailListTask extends AsyncTask<String, Void, List<News>> {

	private NewsAdapter mNewsAdapter;
	private OnFinishListener listener; 
	
	public LoadSelectionDetailListTask(NewsAdapter newsAdapter) {
		// TODO Auto-generated constructor stub
		this.mNewsAdapter=newsAdapter;
	}
	
	public LoadSelectionDetailListTask(NewsAdapter newsAdapter,OnFinishListener listener) {
		// TODO Auto-generated constructor stub
		this.mNewsAdapter=newsAdapter;
		this.listener=listener;
	}
	
	@Override
	protected List<News> doInBackground(String... para) {
		List<News> lists = new ArrayList<News>();
		try {
			
			lists = JsonHelper.parseJsonToList(Http.getSelectionDetailList(Integer.parseInt(para[0])));
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
