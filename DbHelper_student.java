package com.example.attendance_assistor;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DbHelper_student extends SQLiteOpenHelper{

	   public static final String DATABASE_NAME = "MyDBName.db";
	   
	   public static final String CONTACTS_COLUMN_ID = "id";
	   public static final String CONTACTS_COLUMN_NAME = "name";
	   public static final String CONTACTS_COLUMN_EMAIL = "email";
	   public static final String CONTACTS_COLUMN_PHONE = "phone";
	   public static final String CONTACTS_COLUMN_SUBJECT = "subject";
	   public static final String CONTACTS_COLUMN_ATTENDANCE = "attendance";
	   public static final String CONTACTS_COLUMN_CLASSES = "classes";
	   public   String CONTACTS_TABLE_NAME = "contacts";
	   
	 
	   
	   
	   
	   public DbHelper_student(Context context)
	   {
	      super(context, DATABASE_NAME , null, 1);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	      // TODO Auto-generated method stub
	      db.execSQL(
	      "create table " + "contacts" +
	      "(id integer primary key, name text, phone text,email text,subject text,attendance text,classes text)"
	      );
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      // TODO Auto-generated method stub
		   
		
		   
	      db.execSQL("DROP TABLE IF EXISTS"+ "contacts");
	      onCreate(db);
	   }

	   public boolean insertContact  (String name, String phone, String email, String subject)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("phone", phone);
	      contentValues.put("email", email);
	      contentValues.put("subject", subject);
	      contentValues.put("attendance", "0");
	      contentValues.put("classes", "0");
	      db.insert("contacts", null, contentValues);
	      return true;
	   }
	   
	   public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from " + "contacts" +" where id="+id+"", null );
	      return res;
	   }
	   
	   public int numberOfRows(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
	      return numRows;
	   }
	   
	   public boolean updateContact (Integer id, String name, String phone, String email)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("phone", phone);
	      contentValues.put("email", email);
	     
	      db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
	      return true;
	   }

	   public Integer deleteContact (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("contacts", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
	   
	   public ArrayList<String> getAllCotacts(String sub5)
	   {
		   
	      ArrayList<String> array_list = new ArrayList<String>();
	      
	      
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from"+" contacts" + " where subject='"+sub5+"'" , null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
	         res.moveToNext();
	      }
	   return array_list;
	   }
	   
	  public Cursor attendance(String student,String subject){
		  SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select attendance from " + "contacts" +" where name='" +student+"'" + " and subject='" + subject +"'", null );
	      return res;
	  }
	  public boolean insertAttendance(String student,String subject,String attendance){
		  SQLiteDatabase ip = this.getWritableDatabase();
	     ip.execSQL("UPDATE contacts SET attendance ='" + attendance +"'" + "where name LIKE'" +student+"'" + " and subject LIKE'" + subject +"'");
		  return true;
	  }
	  
	  
	  public Cursor classes(String student,String subject){
		  SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select classes from " + "contacts" +" where name='" +student+"'" + " and subject='" + subject +"'", null );
	      return res;
	  }
	  public boolean insertClasses(String student,String subject,String classes){
		  SQLiteDatabase ip = this.getWritableDatabase();
	     ip.execSQL("UPDATE contacts SET classes ='" + classes +"'" + "where name LIKE'" +student+"'" + " and subject LIKE'" + subject +"'");
		  return true;
	  }
	  public boolean deleteStudent (String student,String subject)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	       db.execSQL("DELETE FROM contacts" + " WHERE name='" + student + "'" + " AND subject='" + student+"'");
	       db.close();
	       return true;
	   }
	  
	 
	}


