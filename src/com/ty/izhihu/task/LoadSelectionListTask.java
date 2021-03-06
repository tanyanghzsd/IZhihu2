package com.ty.izhihu.task;

import java.util.List;

import com.ty.izhihu.adapter.SelectionAdapter;
import com.ty.izhihu.adapter.ThemeAdapter;
import com.ty.izhihu.app.IConstants;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.bean.Theme;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;

import android.os.AsyncTask;

public class LoadSelectionListTask extends AsyncTask<Void, Void, List<Theme>>{

	private SelectionAdapter adapter;
	private OnFinishListener listener; 
	
	public LoadSelectionListTask(SelectionAdapter adapter) {
		// TODO Auto-generated constructor stub
		this.adapter=adapter;
	}
	
	public LoadSelectionListTask(SelectionAdapter adapter,OnFinishListener listener) {
		// TODO Auto-generated constructor stub
		this.adapter=adapter;
		this.listener=listener;
	}
	
	@Override
	protected List<Theme> doInBackground(Void... arg0) {
		List<Theme> lists = null;
		try {
			lists = JsonHelper.parseJsonToSelectList(Http.getSelectionList());
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			return lists;
		}
	}
	
	@Override
	protected void onPostExecute(List<Theme> result) {
		adapter.refreshNewsList(result);
		if(listener!=null){
			listener.afterTaskFinish();
		}
	}
	
	
}
