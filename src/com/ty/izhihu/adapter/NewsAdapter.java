package com.ty.izhihu.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ty.izhihu.R;
import com.ty.izhihu.bean.News;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News>{
	private LayoutInflater mLayoutInflater;
	private int resource;
	private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.timg)
            .showImageOnFail(R.drawable.timg)
            .showImageForEmptyUri(R.drawable.timg)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();

	public NewsAdapter(Context context, int resource) {
		super(context, resource);
		this.mLayoutInflater=LayoutInflater.from(context);
		this.resource=resource;
	}
	
	public NewsAdapter(Context context, int resource, int textViewResourceId, List<News> objects) {
		super(context, resource, textViewResourceId, objects);
		this.mLayoutInflater=LayoutInflater.from(context);
		this.resource=resource;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=mLayoutInflater.inflate(resource, null);
			holder.title=(TextView) convertView.findViewById(R.id.news_title);
			holder.images=(ImageView) convertView.findViewById(R.id.news_image);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		News news = getItem(position);
		holder.title.setText(news.getTitle());
		/*
		 * 使用universal-image-loader对图片进行处理
		 */
		imageLoader.displayImage(news.getImages(), holder.images, options);
		return convertView;
	}
	
	class ViewHolder{
		private TextView title;
		private ImageView images;
	}

	@SuppressLint("NewApi")
	public void refreshNewsList(List<News> newsList) {
        clear();
        addAll(newsList);
        notifyDataSetChanged();
    }
}
