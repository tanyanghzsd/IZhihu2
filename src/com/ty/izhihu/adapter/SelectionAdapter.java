package com.ty.izhihu.adapter;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ty.izhihu.R;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.Theme;
import com.ty.izhihu.db.ThemeDB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.MyViewHolder>{
	private Context context;
	private List<Theme> lists=new ArrayList<Theme>();
	private MyItemClickListener mItemClickListener;
	private MyItemLongClickListener mItemLongClickListener;
	
	
	private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.timg)
            .showImageOnFail(R.drawable.timg)
            .showImageForEmptyUri(R.drawable.timg)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    
    public SelectionAdapter(Context context) {
		super();
		this.context=context;
	}
    
    public SelectionAdapter(Context context,List<Theme> objects) {
		super();
		this.lists=objects;
		this.context=context;
	}
    
    public Theme getItem(int position){
    	return lists.get(position);
    }
    
	
	 // 可复用的VH
    public static class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener{
		// 大图
        public ImageView imavPic;
        // 图片url
        public TextView tvUrl;
        
        private MyItemClickListener mListener;
        
        private MyItemLongClickListener mLongClickListener;

        public MyViewHolder(View itemView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
            super(itemView);
            imavPic = (ImageView) itemView.findViewById(R.id.imavPic);
            tvUrl = (TextView) itemView.findViewById(R.id.tvUrl);
            this.mListener=listener;
            this.mLongClickListener=longClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

		

		@Override
		public void onClick(View v) {
			if(mListener!=null){
				mListener.onItemClick(v, getPosition());
			}
			
		}

		@Override
		public boolean onLongClick(View v) {
			if(mLongClickListener!=null){
				mLongClickListener.onItemLongClick(v, getPosition());
			}
			
			return true;
		}
    }
    
    @Override
	public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View v = LayoutInflater.from(arg0.getContext()).inflate(R.layout.recycler_view_item, arg0, false);
        MyViewHolder vh=new MyViewHolder(v, mItemClickListener, mItemLongClickListener);
		return vh;
	}

	@Override
	public void onBindViewHolder(MyViewHolder viewHolder, int arg1) {
		Theme theme = lists.get(arg1);
		
		imageLoader.displayImage(theme.getThumbnail(), viewHolder.imavPic, options);
		viewHolder.tvUrl.setText(theme.getName());
	}

	
	 @Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return lists.size();
		}
	
	@SuppressLint("NewApi")
	public void refreshNewsList(List<Theme> selectionList) {
		lists.clear();
        lists.addAll(selectionList);
        
        for (Theme theme : selectionList) {
        	Log.i("TAG3", theme.getName()+":"+theme.getId());
        	ThemeDB.getInstance(context).saveThemeNews(theme);
		}
        
    }
	
	
	
	public void setOnItemClickListener(MyItemClickListener listener){
		this.mItemClickListener=listener;
	}
	
	public void setOnItemLongClickListener(MyItemLongClickListener listener){
		this.mItemLongClickListener=listener;
	}

}
