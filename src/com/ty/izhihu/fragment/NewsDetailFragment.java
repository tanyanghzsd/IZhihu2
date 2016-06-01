package com.ty.izhihu.fragment;

import com.ty.izhihu.R;
import com.ty.izhihu.activity.CommentActivity;
import com.ty.izhihu.app.IConstants;
import com.ty.izhihu.bean.NewsDetail;
import com.ty.izhihu.bean.OnFinishListener;
import com.ty.izhihu.db.NewsDetailDB;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadNewsDetailTask;
import com.ty.izhihu.utility.Tool;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageButton;


@SuppressLint("ValidFragment")
public class NewsDetailFragment extends Fragment implements OnClickListener,OnTouchListener{

	private WebView webView;
	private long id;
	protected ProgressDialog progressDialog;
	private ImageButton commentBtn;
	private float startY;
	private OnGetNewsDetail listener;
	private float startX;
	private String hot_url;
	private long hot_news_id;
	
	
	public NewsDetailFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public NewsDetailFragment(OnGetNewsDetail listener){
		this.listener=listener;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

		webView=(WebView) view.findViewById(R.id.webview);
		webView.setOnTouchListener(this);
		
		setWebView(webView);
		
		commentBtn = (ImageButton) view.findViewById(R.id.comment_ib);
		commentBtn.setOnClickListener(this);

		Bundle bundle = getArguments();
		hot_news_id = bundle.getLong("hot_news_id");
		id = bundle.getLong("id");
		
		if(hot_news_id>0){
			webView.loadUrl("http://daily.zhihu.com/story/"+hot_news_id);
		}else{
			loadWebViewById();
		}

		

		return view;
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void setWebView(WebView mWebView) {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setVerticalScrollBarEnabled(true);
		mWebView.setHorizontalScrollBarEnabled(false);
	}
	
	
	private void loadWebViewById(){
		if(id>=0){
			if(NetUtils.isConnected(getActivity()))
			{
				new LoadNewsDetailTask(webView,new OnFinishListener() {

					@Override
					public void afterTaskFinish() {
						
						if(progressDialog.isShowing()){
							NewsDetailDB.getInstance(getActivity())
							.saveNewsDetail(IConstants.NEWS_DETAIL);
							listener.passNewsDetail(IConstants.NEWS_DETAIL);
							progressDialog.dismiss();
						}

					}

					@Override
					public void beforeTaskBeging() {
						// TODO Auto-generated method stub
						progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
						progressDialog.setMessage("正在加载...");
						progressDialog.show();
					}
				}).execute(id);

			} 
			else
			{

				Tool.showMsg(getActivity(),getString(R.string.network_is_useless));
			}
		}

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.comment_ib:
			Intent intent = new Intent(getActivity(),CommentActivity.class);
			if(id>0){
				intent.putExtra("id", id);
			}
			if(hot_news_id>0){
				intent.putExtra("hot_news_id", hot_news_id);
			}
				
			
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		commentBtn.setVisibility(View.GONE);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = event.getY();
			startX = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			
			
			break;
		case MotionEvent.ACTION_UP:
			float y2 = event.getY();
			float x2 = event.getX();
			
			if(x2-startX>Tool.getScreenWidth(getActivity())/2){
				getActivity().finish();
			}
			
			if(y2-startY>50){
				commentBtn.setAnimation(new AnimationUtils().loadAnimation(getActivity(), R.drawable.show_from_bottom));
				commentBtn.setVisibility(View.VISIBLE);
			}
				
			break;
		default:
			break;
		}
		return false;
	}
	
	public interface OnGetNewsDetail{
		void passNewsDetail(NewsDetail newsDetail);
	}

	void loadUrl(){
		
	}
}
