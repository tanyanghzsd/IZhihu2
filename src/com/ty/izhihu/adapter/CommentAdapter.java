package com.ty.izhihu.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ty.izhihu.R;
import com.ty.izhihu.bean.Comments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentAdapter extends ArrayAdapter<Comments>{
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

	public CommentAdapter(Context context, int resource) {
		super(context, resource);
		this.mLayoutInflater=LayoutInflater.from(context);
		this.resource=resource;
	}
	
	public CommentAdapter(Context context, int resource, int textViewResourceId, List<Comments> objects) {
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
			holder.name=(TextView) convertView.findViewById(R.id.comment_name);
			holder.content=(TextView) convertView.findViewById(R.id.comment_content);
			holder.avatar=(ImageView) convertView.findViewById(R.id.comment_avatar);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		Comments comment = getItem(position);
		holder.name.setText(comment.getAuthor());
		holder.content.setText(comment.getContent());
		/*
		 * 使用universal-image-loader对图片进行处理
		 */
		imageLoader.displayImage(comment.getAvatar(), holder.avatar, options);
		return convertView;
	}
	
	class ViewHolder{
		private TextView name;
		private TextView content;
		private ImageView avatar;
	}

	@SuppressLint("NewApi")
	public void refreshNewsList(List<Comments> list) {
        clear();
        addAll(list);
        notifyDataSetChanged();
    }
}
