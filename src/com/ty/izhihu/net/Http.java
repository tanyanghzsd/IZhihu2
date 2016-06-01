package com.ty.izhihu.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Http {
	
	public static String NEWSLIST_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static String STORY_VIEW = "http://daily.zhihu.com/story/";
    public static String NEWSDETAIL = "http://news-at.zhihu.com/api/4/news/";
	public static String HISTORYNEWS = "http://news.at.zhihu.com/api/4/news/before/";
    public static String STARTIMAGE="http://news-at.zhihu.com/api/4/start-image/720*1184";
    public static String COMMENTS="http://news-at.zhihu.com/api/4/story/";
	public static String THEMESLIST="http://news-at.zhihu.com/api/4/themes";
    public static String THEMESECONDLIST="http://news-at.zhihu.com/api/4/theme/";
    public static String SELECTION="http://news-at.zhihu.com/api/3/sections";
    public static String SELECTIONDETAIL="http://news-at.zhihu.com/api/3/section/";
    public static String HOTNEWS="http://news-at.zhihu.com/api/3/news/hot";
    
	public static String get(String urlAddr) throws IOException {
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlAddr);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(30*1000);
            con.setReadTimeout(30*1000);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                throw new IOException("Network Error - response code: " + con.getResponseCode());
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
	
	public static String getLastNewsList() throws IOException {
        return get(NEWSLIST_LATEST);
    }
	
	
	
    public static String getNewsDetail(long id) throws IOException {
    	return get(NEWSDETAIL + id);
    }

    public static List<String> getHistoryNewsByDate(List<String> dates) throws IOException {
    	List<String> lists=new ArrayList<String>();
    	for(String date:dates){
    		lists.add(get(HISTORYNEWS + date));
    	}
    	return lists; 
    }
    
    public static String getHistoryNewsByDate(String date) throws IOException {
    	
    	return get(HISTORYNEWS + date); 
    }
    
    public static String getStartImage() throws IOException{
    	return get(STARTIMAGE);
    }
    
    public static String getComments(long id) throws IOException{
    	return get(COMMENTS+id+"/short-comments");
    }
    
    public static String getThemeList() throws IOException{
    	return get(THEMESLIST);
    } 
    
    public static String getSecondThemeList(int id) throws IOException {
        return get(THEMESECONDLIST+id);
    }
    
    public static String getSelectionList() throws IOException{
    	return get(SELECTION);
    } 
    
    public static String getSelectionDetailList(int id) throws IOException{
    	return get(SELECTIONDETAIL+id);
    	
    } 
    
    public static String getHotNews() throws IOException{
    	return get(HOTNEWS);
    } 
}
