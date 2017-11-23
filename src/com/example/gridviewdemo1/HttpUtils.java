package com.example.gridviewdemo1;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;






import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class HttpUtils {
	public HttpUtils(){
		
	}
	/*public int requestHttp(String url,String strjson) {
		String result="";
		DefaultHttpClient mHttpClient = new DefaultHttpClient();
		HttpPost mPost = new HttpPost(url);
		try {
			//mPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
			mPost.setEntity(new StringEntity(strjson));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpResponse response = mHttpClient.execute(mPost);
			int res = response.getStatusLine().getStatusCode();			
			if (res == 200) {				
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity);
					*//***********************//*
					//将响应的json字符串转换为JSONObject对象
					JSONObject jsonObject;
					try {
						jsonObject = new JSONObject(info);
						info = jsonObject.getString("serverinfo");
						System.out.println("serverinf的值："+info);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*//***********************//*
					
				}
		return result;
	}*/
	public static String sendPostMessage(String path,String encode){
	/*	DefaultHttpClient mHttpClient = new DefaultHttpClient();
		HttpPost mPost = new HttpPost(path);
		String strJson = "{\"TrafficLightId\":" +  1 + "}";
		String result="";
		try {
			mPost.setEntity(new StringEntity(strJson));
			HttpResponse response = mHttpClient.execute(mPost);
			if (response.getStatusLine().getStatusCode()==200) {
				result=EntityUtils.toString(response.getEntity());
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			
		String result="";
		
		HttpClient httpClient= new DefaultHttpClient();
		HttpGet post=new HttpGet(path);
		
		HttpResponse httpResponse;
		try {
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
