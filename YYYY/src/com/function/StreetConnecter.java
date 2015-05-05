package com.function;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.activity.Street_Activity;
import com.bean.SrStreetBeanList;
import com.bean.StreetMessageBean;

import android.os.StrictMode;

//import android.os.StrictMode;

public class StreetConnecter{
	private String adrees = "中国石油大学";
	
	public void connect() {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL("http://192.168.191.1:8080/Bill/servlet/StreetServlet");

			// 链接

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			System.out.println("开始连接");
			URLConnection con = url.openConnection();
			System.out.println("打开连接");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(5000);
			System.out.println("尝试连接");
			con.connect();
			System.out.println("链接成功!");
			// 发送数据
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(con.getOutputStream()));
			objectOutputStream.writeObject(adrees);// 发送地址
			objectOutputStream.flush();
			objectOutputStream.close();
			System.out.println("发送成功");
			
			//读取返回结果信息
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(con.getInputStream()));
			Object obj = objectInputStream.readObject();
			
			SrStreetBeanList streetBean = (SrStreetBeanList)obj;//转化为序列化对象
			ArrayList<StreetMessageBean> messageBeans = streetBean.getList();//提取列表
			System.out.println("收到返回消息");
			objectInputStream.close();
			
			//显示到界面
			GUIManager guiManager = new GUIManager();
			guiManager.createStreetGUI(messageBeans);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StreetGUI streetGUI = new StreetGUI(Street_Activity.rootlinearLayout);
			streetGUI.createErrorGUI();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
