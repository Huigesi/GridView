package com.example.gridviewdemo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.HttpClient;

import android.app.Activity;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SimpleAdapter;




public class MainActivity extends Activity{
	private GridView gridView;
	private SimpleAdapter mAdapter;
	private ArrayList<HashMap<String, String>> list;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridView=(GridView)findViewById(R.id.gridview);
		/*mAdapter=new SimpleAdapter(this, list, R.layout.item, 
				new String[] { "TextView" }, 
				new int[] { R.id.TextView });
		gridView.setAdapter(mAdapter);
	    addHeader();
	    addData();*/ 
		new MyTask().execute("http://192.168.1.243:8080/transportservice/type/jason/action/GetTrafficLightConfigAciton.do");
	}
	
	public class MyTask extends AsyncTask<String, Void, List<String>>{

		@Override
		protected List<String> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<String> list=null;
			String jsonString = HttpUtils.sendPostMessage(params[0], "utf-8");
			list=JsonTools.parseList(jsonString);
			Log.i("gjggjgj", jsonString);
			return list;
		}
		
	}
		
	  

} 

	


