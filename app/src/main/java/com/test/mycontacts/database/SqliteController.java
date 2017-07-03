package com.test.mycontacts.database;



import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SqliteController {

	private AdminDatabaseHelper dbHelper;
	private Context ourcontext;
	private SQLiteDatabase database;

	public SqliteController(Context c) {
		ourcontext = c;
	}

	public SqliteController open() throws SQLException {
		dbHelper = new AdminDatabaseHelper(ourcontext);
		database = dbHelper.getWritableDatabase();
		database = dbHelper.getReadableDatabase();
		return this;

	}

	public void close() {
		dbHelper.close();
	}

	private SQLiteDatabase getWritableDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

}
