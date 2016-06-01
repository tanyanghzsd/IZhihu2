package com.ty.izhihu.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ty.izhihu.R;
import com.ty.izhihu.bean.Theme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThemeAdapter extends  ArrayAdapter<Theme>{
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

	public ThemeAdapter(Context context, int resource) {
		super(context, resource);
		this.mLayoutInflater=LayoutInflater.from(context);
		this.resource=resource;
	}
	
	public ThemeAdapter(Context context, int resource, int textViewResourceId, List<Theme> objects) {
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
			holder.name=(TextView) convertView.findViewById(R.id.theme_name);
			holder.description=(TextView) convertView.findViewById(R.id.theme_description);
			holder.images=(ImageView) convertView.findViewById(R.id.theme_img);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		Theme theme = getItem(position);
		holder.name.setText(theme.getName());
		holder.description.setText(theme.getDescription());
		/*
		 * 使用universal-image-loader对图片进行处理
		 */
		imageLoader.displayImage(theme.getThumbnail(), holder.images, options);
		return convertView;
	}
	
	class ViewHolder{
		private TextView name;
		private TextView description;
		private ImageView images;
	}

	@SuppressLint("NewApi")
	public void refreshNewsList(List<Theme> newsList) {
        clear();
        addAll(newsList);
        notifyDataSetChanged();
    }
}
