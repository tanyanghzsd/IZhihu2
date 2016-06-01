package com.ty.izhihu.task;

import java.util.ArrayList;
import java.util.List;

import com.ty.izhihu.adapter.CommentAdapter;
import com.ty.izhihu.adapter.HistoryAdapter;
import com.ty.izhihu.bean.Comments;
import com.ty.izhihu.bean.HistoryNews;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;

import android.os.AsyncTask;

public class LoadCommentsTask extends AsyncTask<Long, Void, List<Comments>> {

	private CommentAdapter adapter;
	private OnFinishListener listener; 
	
	public LoadCommentsTask(CommentAdapter adapter) {
		// TODO Auto-generated constructor stub
		this.adapter=adapter;
	}
	
	public LoadCommentsTask(CommentAdapter adapter,OnFinishListener listener) {
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
	protected List<Comments> doInBackground(Long... arg0) {
		List<Comments> lists=new ArrayList<Comments>();
		
		try {
			lists.addAll(JsonHelper.parseJsonToComments(Http.getComments(arg0[0])));
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return lists;
		}
	}
	
	@Override
	protected void onPostExecute(List<Comments> result) {
		adapter.refreshNewsList(result);
		if(listener!=null){
			listener.afterTaskFinish();
		}
	}

}
