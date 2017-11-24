package com.example.gridviewdemo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTool {
	ArrayList<String> list=new ArrayList<String>();
	public JsonTool(){
		
	}
	public static String sendMessage(String url,String postUrl){
		String result="";
		HttpClient httpClient= new DefaultHttpClient();
		HttpPost post=new HttpPost(url);
		HttpResponse httpResponse;
		try {
			post.setEntity(new StringEntity(postUrl));
			httpResponse = httpClient.execute(post);
			if (httpResponse.getStatusLine().getStatusCode()==200) {
				result=EntityUtils.toString(httpResponse.getEntity());
			}
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

}
