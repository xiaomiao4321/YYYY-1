package com.activity;

/**
 * 1.添加测试+按钮事件，用于测试
 * 2.添加确定按钮
 * @author wcj
 * @time 15-3-31晚
 */

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.dao.DataBase;
import com.dao.YS_DAO;
import com.model.BackgroundColor;
import com.model.CloudSendHelper;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JZ_Activity extends Activity {

	public static TextView budgetRemain;
	private TextView kind;
	public TextView consume;
	private TextView setting;
	private Button number_1;
	private Button number_2;
	private Button number_3;
	private Button number_4;
	private Button number_5;
	private Button number_6;
	private Button number_7;
	private Button number_8;
	private Button number_9;
	private Button number_0;
	private Button syButton; // 测试按钮
	private TextView number_in;
	private TextView number_out;
	private Button button_ok;
	private Button number_float;
	private Button number_clear;
	private String consumString = "";
	public static TextView consumed;
	public static LinearLayout linearLayout;
	private int inOrOut = 0; // 0代表支出，1代表收入，默认支出
	public static int consumekind = 2; // 消费类别参数（默认为食）
	private ArrayList<String> kindList = new ArrayList<String>();
	public static Activity jzActivity;
	private TextView zyj;
	private TextView zq;
	private ProgressDialog pd;

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("hehe调用了Resume");
	};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jz1);
		jzActivity = this;
		System.out.println("JZ被创建");
		System.out.println("JZ线程:" + Thread.currentThread().getId());
		/**
		 * 加载组件
		 */
		consume = (TextView) this.findViewById(R.id.consume);
		budgetRemain = (TextView) this.findViewById(R.id.budgetRemain);
		number_0 = (Button) this.findViewById(R.id.number_0);
		number_1 = (Button) this.findViewById(R.id.number_1);
		number_2 = (Button) this.findViewById(R.id.number_2);
		number_3 = (Button) this.findViewById(R.id.number_3);
		number_4 = (Button) this.findViewById(R.id.number_4);
		number_5 = (Button) this.findViewById(R.id.number_5);
		number_6 = (Button) this.findViewById(R.id.number_6);
		number_7 = (Button) this.findViewById(R.id.number_7);
		number_8 = (Button) this.findViewById(R.id.number_8);
		number_9 = (Button) this.findViewById(R.id.number_9);
		number_in = (TextView) this.findViewById(R.id.button_in);
		number_out = (TextView) this.findViewById(R.id.button_out);
		number_float = (Button) this.findViewById(R.id.number_float);
		number_clear = (Button) this.findViewById(R.id.number_clear);
		button_ok = (Button) this.findViewById(R.id.ok);
		consumed = (TextView) this.findViewById(R.id.comsumed);
		linearLayout = (LinearLayout) this.findViewById(R.id.background);
		zyj = (TextView)this.findViewById(R.id.zyj);
		setting  = (TextView)this.findViewById(R.id.setting);
		zq = (TextView)this.findViewById(R.id.zq);
		// 测试按钮
		syButton = (Button) this.findViewById(R.id.sy);
		kind = (TextView) this.findViewById(R.id.kind);

		kindList.add("酒足饭饱");
		kindList.add("穿金戴银");
		kindList.add("酒足饭饱");
		kindList.add("斯是陋室");
		kindList.add("踏破铁鞋");
		
		
		/**
		 * 攒钱目标测试
		 */
		zq.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("点击了攒钱目标");
				pd =	ProgressDialog.show(JZ_Activity.this, "自爆", "自爆装置启动中，请稍后……");
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.currentThread().sleep(5000);
							pd.dismiss();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		
		
		/**
		 * 设置按钮事件
		 */
		setting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(jzActivity, User_Activity.class);
				startActivity(intent);
			}
		});
		
		
		/**
		 *攒友街 
		 */
		zyj.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JZ_Activity.this, Street_Activity.class);
				startActivity(intent);
			}
		});
		
		/**
		 * 测试同步按钮
		 */
		syButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DataBase dataBase = new DataBase(JZ_Activity.this, "user.db");
				CloudSendHelper cloudSendHelper = new CloudSendHelper(dataBase);
				try {
					try {
						if(cloudSendHelper.checkAndSend()){
//							Toast.makeText(JZ_Activity.jzActivity, "同步成功!",
//									Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(JZ_Activity.jzActivity, "同步前请登录哦！",
									Toast.LENGTH_LONG).show();
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		/**
		 * 点击类型事件
		 */
		kind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(JZ_Activity.this,
						SelectPicPopupWindow.class));
			}
		});

		/**
		 * 收入的事件
		 */
		number_in.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_in.setTextColor(Color.RED);
				number_out.setTextColor(Color.WHITE);
				inOrOut = 1;
				consume.setTextColor(Color.GREEN);
			}
		});

		/**
		 * 支出的事件
		 */
		number_out.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_in.setTextColor(Color.WHITE);
				number_out.setTextColor(Color.RED);
				inOrOut = 0;
				consume.setTextColor(Color.RED);
				System.out.println("切换到支出类别：inOrOut应该=0，实际为" + inOrOut);
			}
		});

		/**
		 * 按钮0的事件
		 */
		number_0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "0";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});

		/**
		 * 按钮1的事件
		 */
		number_1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "1";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮2的事件
		 */
		number_2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "2";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮3的事件
		 */
		number_3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "3";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮4的事件
		 */
		number_4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "4";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮5的事件
		 */
		number_5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "5";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮6的事件
		 */
		number_6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "6";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮7的事件
		 */
		number_7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "7";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮8的事件
		 */
		number_8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "8";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮9的事件
		 */
		number_9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // 检测输入是否合法
					consumString += "9";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * 按钮.的事件
		 */
		number_float.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) {
					if (consumString.length() > 0
							&& (consumString.indexOf(".") < 0)) {
						consumString = consume.getText().toString() + ".";
						consume.setText(consumString.toCharArray(), 0,
								consumString.length());
					}
				}
			}
		});
		/**
		 * 按钮C的事件
		 */
		number_clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = "";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});

		/**
		 * 按钮ok的事件
		 */
		button_ok.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (consumString.length() > 0) {
					ContentValues values = new ContentValues();
					// 获得时间
					SimpleDateFormat sDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					String date = sDateFormat.format(new java.util.Date());
					// 字符串转换为浮点数
					float consume1 = Float.parseFloat(consumString);
					// 插入数据， 注意值的类型要匹配
					values.put("consume", consume1);
					// 从ArrayList里面找到类型的中文描述
					if (inOrOut == 0)
						values.put("kind", kindList.get(consumekind));
					else {
						values.put("kind", "收入");
					}
					values.put("date", date);
					values.put("inorout", inOrOut);
					Index_Activity.db.insert("test1", null, values);
					// 插入后清空输入框
					consumString = "";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
					YS_DAO ys_DataBaseHelper = new YS_DAO(
							JZ_Activity.this);

					// 如果是支出则更新预算表，收入则更新收入表
					if (inOrOut == 0) {
						// 同时更新数据库2张预算表
						ys_DataBaseHelper.update(consume1, consumekind);
						// 更新显示余额
						Float remain = Float.parseFloat(budgetRemain.getText()
								.toString());
						remain = remain - consume1;
						budgetRemain.setText(remain.toString());
						System.out.println("插入成功");
						// db.close();
					} else {
						// 更新收入表
						ys_DataBaseHelper.updatein(consume1);
						System.out.println("收入成功");
						// db.close();
					}
				}
				BackgroundColor backgroundColor = new BackgroundColor();
				backgroundColor.refreshback();
				// 更新消费
				String consumed = new DecimalFormat("0.0")
						.format(Index_Activity.budget - Index_Activity.remain);
				JZ_Activity.consumed.setText(consumed);
				Toast.makeText(JZ_Activity.this, "成功记入一笔!", Toast.LENGTH_LONG)
						.show();
			}
		});
		/*
		 * 按钮设置预算的按钮
		 */
		budgetRemain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(JZ_Activity.this, YS_Activity.class);
				startActivity(intent);
			}
		});

	}

	/**
	 * 检测输入是否合法
	 * 
	 * @param consumeString
	 *            编辑栏字符串
	 * @return
	 */
	public boolean check(String consumeString) {
		boolean TAG = false;
		// 浮点数
		if (consumeString.contains(".")) {
			if (consumeString.indexOf(".") < 4
					|| (consumeString.length() - consumeString.indexOf(".")) < 3) {
				TAG = true;
			}
		} else {// 整数
			if (consumeString.length() < 6) {
				TAG = true;
			}
		}
		return TAG;
	}
}
