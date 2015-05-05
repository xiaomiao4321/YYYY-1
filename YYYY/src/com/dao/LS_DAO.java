package com.dao;

//import android.app.Activity;
import com.activity.Index_Activity;

import android.content.Context;
import android.database.Cursor;

public class LS_DAO {
	Context context;
	String tableNameString = "test1"; // 流水表名 test1

	public LS_DAO(Context context) {
		this.context = context;

	}

	/**
	 * 查询所有流水账
	 * 
	 * @param manager
	 * @param dataBase
	 * @return
	 */
	public Cursor selectAllAccount(String dateString) {
//		SQLiteDatabase db = dataBase.getReadableDatabase();
		String sql = "select consume, kind, date, inorout from test1 where date >= '"
				+ dateString
				+ "' and date < date('"
				+ dateString
				+ "', '+1 month') order by date desc";
		System.out.println("sql 语句是" + sql);
		Cursor cursor = Index_Activity.db.rawQuery(sql, null);
		return cursor;
	}
}
