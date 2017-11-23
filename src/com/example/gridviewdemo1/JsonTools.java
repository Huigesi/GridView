package com.example.gridviewdemo1;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTools {
	public JsonTools(){
		
	}
	public static List<String> parseList(String jsonString){
		List<String> list=new ArrayList<String>();
		try {
			JSONObject jsonObject=new JSONObject(jsonString);
			JSONArray jsonArray=jsonObject.getJSONArray("serverinfo");
			for(int i=0;i<jsonArray.length();i++){
				list.add(jsonArray.getString(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	

}
