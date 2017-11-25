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
	private String path="http://192.168.1.243:8080/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
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
	//初始化控件
	private void initView(){
		button=(Button)findViewById(R.id.button);
		listView=(ListView)findViewById(R.id.listview);
		
	}
	//设置按钮监听
	private void setListener() {
		button.setOnClickListener(new OnClickListener() {
				//纪录按钮点击次数
				@Override
				public void onClick(View v) {
					count++;
					Log.i("点击次数",count+"");
					if(count%2==0){
						sort2();
					}else{
						sort1();
					}
					//排序后刷新
					mAdapter.notifyDataSetChanged();
				}
			});
		}
	//初始化线程
	private void setThread() {
		mThread = new Thread() {
			@Override
			public void run() {
				super.run();
			   int count =1 ;
			   //5个灯，执行5次
			   while(count<=5){
						mHandler.sendEmptyMessage(SHOW_DIALOG);
						mResultString = JsonTool.sendMessage(path,"{\"TrafficLightId\":" +count+ "}");
						mHandler.sendEmptyMessage(UP_DATE);
						try {
							//解析数据（本来是parseJason()方法）
						/*JSONObject mjson = new JSONObject(mResultString);
						String jsonn = mjson.getString("serverinfo");
						JSONObject jsons = new JSONObject(jsonn);
						Log.i("dd", mjson.toString());
						ItemBean mBean = new ItemBean();
						
						mBean.setId(count);
						mBean.setRed(jsons.getInt("RedTime"));
						mBean.setGreen(jsons.getInt("GreenTime"));
						mBean.setYellow(jsons.getInt("YellowTime"));
						mList.add(mBean);*/
						
							parseJason(count);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						   count++;
			}
			
			}
		};
	}
	//初始化进度条
	private void getDialog() {
		mDialog = new ProgressDialog(this);
		mDialog.setMessage("请稍候");
	}
	//handle消息发送
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
	//初始化对象表
	private void iniData() {
		mList = new ArrayList<ItemBean>();
	}
	//parseJason()方法，在线程里调用会闪退，所以直接把代码往行程里扔
	private void parseJason(int count) throws JSONException {
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
		Log.i("数据",mList.size()+"");
	}
	//初始化adapter
	private void setListView() {
		mAdapter = new DataAdapter(MainActivity.this, mList);
		listView.setAdapter(mAdapter);
	}
	//排序方法（必背）
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

	


