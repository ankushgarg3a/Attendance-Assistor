package com.example.attendance_assistor;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
	
	public static String DATABASE_NAME="database.db";

	public database(Context context) {
		 super(context, DATABASE_NAME , null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table " + "attendance" +"(student text,subject text,day text,month text,year text,present text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS"+ "attendance");
	      onCreate(db);
	}
	
		  
	  public void insertTable(String student,String subject,String day,String month,String year,String present ){
		  SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("student", student);
	      contentValues.put("subject", subject);
	      contentValues.put("day", day);
	      contentValues.put("month", month);
	      contentValues.put("year", year);
	      contentValues.put("present", present);
	      db.insert("attendance", null, contentValues);
		  
	  }
	  
	  public ArrayList<String> getAttendance(String student,String subject)
	   {
		   
	      ArrayList<String> array_list = new ArrayList<String>();
	      
	      
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from"+" attendance" + " where student='" + student +"' AND " +"subject='" + subject +"' ORDER BY month DESC,day DESC" , null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex("present")));
	         res.moveToNext();
	      }
	   return array_list;
	   }
	  
	  public ArrayList<String> getDate(String student,String subject)
	   {
		   int i=0;
	      ArrayList<String> array_list = new ArrayList<String>();
	      ArrayList<String> list = new ArrayList<String>();
	      
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from"+" attendance" + " where student='" + student +"' AND " +"subject='" + subject +"' ORDER BY month DESC,day DESC", null);
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex("day")));
	         array_list.add(res.getString(res.getColumnIndex("month")));
	         array_list.add(res.getString(res.getColumnIndex("year")));
	         String date= array_list.get(i++)+"/"+array_list.get(i++)+"/"+array_list.get(i++);
	         list.add(date);
	         res.moveToNext();
	      }
	   return list;
	   }

}
