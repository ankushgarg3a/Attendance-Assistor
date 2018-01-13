package com.example.attendance_assistor;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper_subject extends SQLiteOpenHelper {

	   public static final String DATABASE_NAME = "Subjects.db";
	   public static final String SUBJECTS_TABLE_NAME = "subjects";
	   public static final String SUBJECTS_COLUMN_ID = "id";
	   public static final String SUBJECTS_COLUMN_NAME = "name";
	   public static final String SUBJECTS_COLUMN_CODE = "code";
	  

	   public DbHelper_subject(Context context)
	   {
	      super(context, DATABASE_NAME , null, 1);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	      // TODO Auto-generated method stub
	      db.execSQL(
	      "create table subjects " +
	      "(id integer primary key, name text,code text)"
	      );
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      // TODO Auto-generated method stub
	      db.execSQL("DROP TABLE IF EXISTS subjects");
	      onCreate(db);
	   }

	   public boolean insertSubject  (String name, String code)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("code", code);	
	      db.insert("subjects", null, contentValues);
	      return true;
	   }
	   
	   public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from subjects where id="+id+"", null );
	      return res;
	   }
	   
	   public int numberOfRows(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, SUBJECTS_TABLE_NAME);
	      return numRows;
	   }
	   
	   public boolean updateSubject (Integer id, String name, String code)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("code", code);
	     
	      db.update("subjects", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
	      return true;
	   }

	   public Integer deleteSubject (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("subjects", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
	   
	   public ArrayList<String> getAllSubjects()
	   {
	      ArrayList<String> array_list = new ArrayList<String>();
	      
	      
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from subjects", null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex(SUBJECTS_COLUMN_NAME)));
	         res.moveToNext();
	      }
	   return array_list;
	   }
	   
	   public Cursor getSubject(int id){
		      SQLiteDatabase db = this.getReadableDatabase();
		      Cursor res =  db.rawQuery( "select name from subjects where id="+id+"", null );
		      return res;
		   }
	}
