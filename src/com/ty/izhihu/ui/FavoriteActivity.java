package com.ty.izhihu.ui;

import com.ty.izhihu.R;
import com.ty.izhihu.adapter.NewsAdapter;
import com.ty.izhihu.db.DailyNewsDB;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

/*
 * 长按删除一个item后，画面没有及时更新（被删除的item仍然存在，在 退出重新进入后才没有）
 * 已解决！
 */
public class FavoriteActivity extends BaseActivity implements OnItemClickListener,OnItemLongClickListener{
	
	private static final String NEWS = "news";
	private static final String IS_FAVORITE = "is_favorite";
	private static final String DELETE_FAVORITE = "删除该收藏";
	private boolean isFavorite;
	private ListView lv;
	private NewsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle(getResources().getString(R.string.my_favortie));
		setContentView(R.layout.activity_myfavorite);
		lv = (ListView) findViewById(R.id.lv_favorite);
		adapter = new NewsAdapter(this, R.layout.listview_item, R.id.lv, DailyNewsDB.getInstance(this).LoadAllFavoriteNews());
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent = new Intent(FavoriteActivity.this,NewsDetailActivity.class);
		isFavorite=DailyNewsDB.getInstance(this).isFavoriteSaved(adapter.getItem(position));
		intent.putExtra(IS_FAVORITE, isFavorite);
		intent.putExtra(NEWS, adapter.getItem(position));
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
		Builder builder = new AlertDialog.Builder(FavoriteActivity.this);
		builder.setItems(new String[]{DELETE_FAVORITE}, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				DailyNewsDB.getInstance(FavoriteActivity.this).deleteFavoriteNews(adapter.getItem(position));
				adapter.refreshNewsList(DailyNewsDB.getInstance(FavoriteActivity.this).LoadAllFavoriteNews());
				adapter.notifyDataSetInvalidated();
				showMsg(getString(R.string.delete_success));
			}
		}).show();
		
		return true;
	}
	
	
}
