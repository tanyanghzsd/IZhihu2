package com.ty.izhihu.activity;

import com.ty.izhihu.utility.SplashScreen;
import com.ty.izhihu.utility.Tool;
import com.ty.izhihu.R;
import com.ty.izhihu.adapter.MyPagerAdapter;
import com.ty.izhihu.bean.OnGetPosition;
import com.ty.izhihu.net.Http;
import com.ty.izhihu.net.JsonHelper;
import com.ty.izhihu.net.NetUtils;
import com.ty.izhihu.task.LoadStartImageTask;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/*
 * 从一个item点击进去出现右上角收藏标志没有变化（已经被收藏的没有变化）
 * 已解决！
 */
@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements OnGetPosition,AnimationListener{
	private int position;
	private ViewPager pager;
	private com.astuetz.PagerSlidingTabStrip tabs;
	private SplashScreen mSplashScreen;
	private LinearLayout mainLayout;
	private ImageView imageView;
	private TextView tvVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = getLayoutInflater().inflate(R.layout.activity_main, null);
		setContentView(view);
		
		
		int screenWidth = Tool.getScreenWidth(this);
		int screenHeight = Tool.getScreenHeight(this);
		imageView = (ImageView) view.findViewById(R.id.load_iv);
		LayoutParams params = new LayoutParams(screenWidth, screenHeight);
		imageView.setLayoutParams(params);
		
		if(NetUtils.isConnected(this)){
			new LoadStartImageTask(imageView).execute();
		}else{
			Tool.showMsg(MainActivity.this, getResources().getString(R.string.network_is_useless));
			
		}
		
		//scaleAnimation动画
		Animation alphaAnimation = new AnimationUtils().loadAnimation(this, R.anim.push_left_out);
		alphaAnimation.setStartOffset(2000);
		alphaAnimation.setAnimationListener(this);
		imageView.setAnimation(alphaAnimation);
		
		
		
		
	}

	class MyOnPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void onPageSelected(int position) {
			
		}
		
	}

	
	
	
	void init(){
		pager = (ViewPager) findViewById(R.id.pager);
		tabs = (com.astuetz.PagerSlidingTabStrip)findViewById(R.id.tabs);
		pager.setOffscreenPageLimit(5);
		

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		
		tabs.setTextColor(Color.GRAY);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		
		tabs.setViewPager(pager);
		
		tabs.setOnPageChangeListener(new MyOnPageChangeListener());
	}


	@Override
	public void onAnimationEnd(Animation arg0) {
		
		imageView.setVisibility(View.GONE);
		
	}


	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		init();
	}


	@Override
	public int getCurrentPagerIdx() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
