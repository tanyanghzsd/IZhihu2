package com.ty.izhihu.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.ty.izhihu.bean.Comments;
import com.ty.izhihu.bean.HistoryNews;
import com.ty.izhihu.bean.HotNews;
import com.ty.izhihu.bean.News;
import com.ty.izhihu.bean.NewsDetail;
import com.ty.izhihu.bean.Theme;

public class JsonHelper {
	
	public static List<News> parseJsonToList(String json) throws JSONException {
        JSONObject newsContent = new JSONObject(json);
        JSONArray newsArray = newsContent.getJSONArray("stories");
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < newsArray.length(); i++) {
            JSONObject newsInJson = newsArray.getJSONObject(i);
            int id = newsInJson.optInt("id");
            String title = newsInJson.optString("title");
            String image = "";
            if (newsInJson.has("images")) {
                image = (String) newsInJson.getJSONArray("images").get(0);

            }
            News news = new News(id, title, image);
            newsList.add(news);
        }
        return newsList;
    }

    public static NewsDetail parseJsonToDetail(String json) throws JSONException {
        Gson gson = new Gson();
        return gson.fromJson(json, NewsDetail.class);
    }
    
    public static List<HistoryNews> parseJsonToHistoryNews(List<String> jsons) throws JSONException{
    	List<HistoryNews> lists=new ArrayList<HistoryNews>();
    	for(String json:jsons){
    		JSONObject newsContent = new JSONObject(json);
        	
        	String date = newsContent.optString("date");
        	
            JSONArray newsArray = newsContent.getJSONArray("stories");
            JSONObject newsInJson = newsArray.getJSONObject(0);
            String images = "";
            if (newsInJson.has("images")) {
                images = (String) newsInJson.getJSONArray("images").get(0);
            }
            
            HistoryNews historyNews = new HistoryNews(date, images);
            lists.add(historyNews);
    	}
    	
        return lists;
    }
    
    public static List<News> parseJsonToHistoryNewsForList(String json) throws JSONException{
    	JSONObject newsContent = new JSONObject(json);
        JSONArray newsArray = newsContent.getJSONArray("stories");
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < newsArray.length(); i++) {
            JSONObject newsInJson = newsArray.getJSONObject(i);
            int id = newsInJson.optInt("id");
            String title = newsInJson.optString("title");
            String image = "";
            if (newsInJson.has("images")) {
                image = (String) newsInJson.getJSONArray("images").get(0);

            }
            News news = new News(id, title, image);
            newsList.add(news);
        }
        return newsList;
    }
    
    public static String parseJsonToStartImage(String json) throws JSONException{
    	JSONObject newsContent = new JSONObject(json);
    	return newsContent.getString("img");
    }
    
    public static List<Comments> parseJsonToComments(String json) throws JSONException{
    	JSONObject newsContent = new JSONObject(json);
    	JSONArray newsArray = newsContent.getJSONArray("comments");
    	ArrayList<Comments> lists = new ArrayList<Comments>();
    	for (int i = 0; i < newsArray.length(); i++) {
    		
            JSONObject newsInJson = newsArray.getJSONObject(i);
            String author = newsInJson.optString("author");
            String content = newsInJson.optString("content");
            String avatar = newsInJson.optString("avatar");
            long id=newsInJson.optLong("id");
            int likes = newsInJson.optInt("likes");
            long time = newsInJson.optLong("time");
            Comments comments = new Comments(author, id, content, likes, time, avatar);
            lists.add(comments);
    	}
    	return lists;
    }	
    
    public static List<Theme> parseJsonToThemeList(String json) throws JSONException{
    	JSONObject newsContent = new JSONObject(json);
    	JSONArray newsArray = newsContent.getJSONArray("others");
    	ArrayList<Theme> lists = new ArrayList<Theme>();
    	for (int i = 0; i < newsArray.length(); i++) {
    		
            JSONObject newsInJson = newsArray.getJSONObject(i);
            String thumbnail = newsInJson.optString("thumbnail");
            String description = newsInJson.optString("description");
            String name = newsInJson.optString("name");
            int id=newsInJson.optInt("id");
            Theme theme = new Theme(name, id, thumbnail, description);
            lists.add(theme);
    	}
    	return lists;
    }
    
    public static List<Theme> parseJsonToSelectList(String json) throws JSONException{
    	JSONObject newsContent = new JSONObject(json);
    	JSONArray newsArray = newsContent.getJSONArray("data");
    	ArrayList<Theme> lists = new ArrayList<Theme>();
    	for (int i = 0; i < newsArray.length(); i++) {
    		
            JSONObject newsInJson = newsArray.getJSONObject(i);
            String thumbnail = newsInJson.optString("thumbnail");
            String description = newsInJson.optString("description");
            String name = newsInJson.optString("name");
            int id=newsInJson.optInt("id");
            Theme theme = new Theme(name, id, thumbnail, description);
            lists.add(theme);
    	}
    	return lists;
    }
    
    public static List<HotNews> parseJsonToHotNews(String json) throws JSONException{
    	JSONObject newsContent = new JSONObject(json);
    	JSONArray newsArray = newsContent.getJSONArray("recent");
    	ArrayList<HotNews> lists = new ArrayList<HotNews>();
    	for (int i = 0; i < newsArray.length(); i++) {
    		
            JSONObject newsInJson = newsArray.getJSONObject(i);
            String thumbnail = newsInJson.optString("thumbnail");
            String url = newsInJson.optString("url");
            String title = newsInJson.optString("title");
            int news_id=newsInJson.optInt("news_id");
            HotNews hotNews = new HotNews(news_id, url, thumbnail, title);
            lists.add(hotNews);
    	}
    	return lists;
    }
    
//    public static List<Selection> parseJsonToSelectionDetail(String json) throws JSONException {
//        JSONObject newsContent = new JSONObject(json);
//        JSONArray newsArray = newsContent.getJSONArray("stories");
//        List<Selection> newsList = new ArrayList<Selection>();
//        for (int i = 0; i < newsArray.length(); i++) {
//            JSONObject newsInJson = newsArray.getJSONObject(i);
//            int id = newsInJson.optInt("id");
//            String title = newsInJson.optString("title");
//            String image = "";
//            if (newsInJson.has("images")) {
//                image = (String) newsInJson.getJSONArray("images").get(0);
//
//            }
//            Selection news = new Selection(image, "", "", id, title);
//            newsList.add(news);
//        }
//        return newsList;
//    }
}
