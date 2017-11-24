package com.example.gridviewdemo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity{
	private static final int IP = 0x00;
	private static final int NET = 0x01;
	private static final int UP_DATE = 0x02;
	private static final int SHOW_DIALOG = 0x03;
	private String path="http://192.168.1.231:8080/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
	//private String post="{\"TrafficLightId\":" +1+ "}";
	private ProgressDialog mDialog;
	private Handler mHandler;
	private DataAdapter mAdapter;
	private Button button;
	private ListView listView;
	private Integer count=0;
	private Thread mThread;
	private String mResultString; 
	private List<ItemBean> mList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getDialog();
		handleMessage();
		iniData();
		initView();
		setListener();
		setListView();
		
		setThread();
		mThread.start();
	}
	private void initView(){
		button=(Button)findViewById(R.id.button);
		listView=(ListView)findViewById(R.id.listview);
		
	}
	private void setListener() {
		button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					count++;
					Log.i("点击次数",count+"");
					if(count%2==0){
						sort2();
					}else{
						sort1();
					}
					
					mAdapter.notifyDataSetChanged();
				}
			});
		}
	private void setThread() {
		mThread = new Thread() {
			@Override
			public void run() {
				super.run();
			   int count =1 ;
			   while(count<=5){
						mHandler.sendEmptyMessage(SHOW_DIALOG);
						mResultString = JsonTool.sendMessage(path,"{\"TrafficLightId\":" +count+ "}");
						mHandler.sendEmptyMessage(UP_DATE);
						try {
						JSONObject mjson = new JSONObject(mResultString);
						String jsonn = mjson.getString("serverinfo");
						JSONObject jsons = new JSONObject(jsonn);
						Log.i("dd", mjson.toString());
						ItemBean mBean = new ItemBean();
						
						mBean.setId(count);
						mBean.setRed(jsons.getInt("RedTime"));
						mBean.setGreen(jsons.getInt("GreenTime"));
						mBean.setYellow(jsons.getInt("YellowTime"));
						mList.add(mBean);
						
							//parseJason();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						   count++;
			}
			
			}
		};
	}
	private void getDialog() {
		mDialog = new ProgressDialog(this);
		mDialog.setMessage("请稍候");
	}
	private void handleMessage() {
		mHandler = new Handler(new Handler.Callback() {

			public boolean handleMessage(Message msg) {
				mDialog.dismiss();
				switch (msg.what) {
				case IP:
					Toast.makeText(MainActivity.this, "请输入ip地址",
					Toast.LENGTH_SHORT).show();
					break;
				case NET:
					Toast.makeText(MainActivity.this, "理解失败",
							Toast.LENGTH_SHORT).show();
				case UP_DATE:
					mAdapter.notifyDataSetChanged();
					break;
				case SHOW_DIALOG:
					mDialog.show();
					break;
				}
				return false;
			}
		});
	}
	private void iniData() {
		mList = new ArrayList<ItemBean>();
	}
	private void parseJason() throws JSONException {
		JSONObject mjson = new JSONObject(mResultString);
		String jsonn = mjson.getString("serverinfo");
		JSONObject jsons = new JSONObject(jsonn);
		Log.i("dd", mjson.toString());
		ItemBean mBean = new ItemBean();
		
		mBean.setId(1);
		mBean.setRed(jsons.getInt("RedTime"));
		mBean.setGreen(jsons.getInt("GreenTime"));
		mBean.setYellow(jsons.getInt("YellowTime"));
		mList.add(mBean);
		Log.i("数据",mList.size()+"");
	}
	private void setListView() {
		mAdapter = new DataAdapter(MainActivity.this, mList);
		listView.setAdapter(mAdapter);
	}
	private void sort1(){
		Collections.sort(mList,new Comparator<ItemBean>() {

			@Override
			public int compare(ItemBean bean1, ItemBean bean2) {
				if(bean1.getId()>bean2.getId()){
					return -1;
				}else{
					return 0;
				}
			}
		});
	}
	private void sort2(){
		Collections.sort(mList,new Comparator<ItemBean>() {

			@Override
			public int compare(ItemBean bean1, ItemBean bean2) {
				//return bean1.getmCo2().compareTo(bean2.getmCo2());
				if(bean1.getId()<bean2.getId()){
					return -1;
				}else{
					return 0;
				}
			}
		});
	}
} 

	


