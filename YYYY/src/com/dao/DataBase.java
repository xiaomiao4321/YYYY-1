package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	/**
	 * 数据库版本
	 */
	private static final int VERSION = 1;

	public DataBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	// 该构造函数只有3个参数，在上面函数 的游标固定为null
	public DataBase(Context context, String name, int verson) {
		this(context, name, null, verson);
	}

	// 该构造函数只有2个参数，在上面函数 的基础山将版本号固定了
	public DataBase(Context context, String name) {
		this(context, name, VERSION);
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("create a sqlite database");

		// 获得当前日期 xxxx-xx
		String currentString;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		currentString = format.format(new Date());

		// 测试表2流水表：包括 消费、类型、时间
		db.execSQL("create table test1(consume float, kind varchar(20), date datetime, inorout int)");
		// 测试表3预算表
		String sql = "create table tablebudget(budget float, kind int, remain float, month date)";
		db.execSQL(sql);
		/**
		 * 以下在预算表中定义默认的表项--衣食住行
		 * 
		 * 新增类别则新增的同时监听执行初始化sql语句
		 * 
		 * 以后每次消费既更新数据即可
		 */
		// 添加衣
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 1, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// 添加食
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 2, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// 添加住
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 3, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// 添加行
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 4, 0,'"
				+ currentString + "')";
		db.execSQL(sql);

		// 测试表4总预算表:应该包含时间（年、月），总预算金额。。测试就略去时间
		sql = "create table tabletotalbudget(totalbudget float,remain float, month date)";
		db.execSQL(sql);
		// 初始化总预算为0
		sql = "insert into tabletotalbudget(totalbudget,remain, month) values (0,0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// 创建表5收入表
		sql = "create table consumein(mony float, month date)";
		db.execSQL(sql);
		sql = "insert into consumein(mony, month) values (0, '" + currentString
				+ "')";
		db.execSQL(sql);
		// 创建时间日期表
		sql = "create table time(lastdate date, sytime datetime)";
		db.execSQL(sql);
		sql = "insert into time(lastdate,sytime) values ('" + currentString
				+ "','2012-01-01 00:00:00')";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("update a sqlite database");
	}

}
