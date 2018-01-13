package com.example.attendance_assistor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;



public class Student_List extends Activity {
	  
	   private ListView obj;
	   DbHelper_student mydb;
	   public database db;
	   public String sub2;
	   public String ob,s,t;
	   public int a,i;
	   String r,c;
	   public int d ,m,y; 
	   public String day ,month,year; 
	   public String student;
	   
	   
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_student_list);
	      
	     
		  
	      Bundle extr = getIntent().getExtras();
	      if(extr != null){
		   sub2 = extr.getString("sub1");
		   d = extr.getInt("day");
		   m = extr.getInt("month");
		   y= extr.getInt("year");
		   day = String.valueOf(d);
		   if(d<10){
			   day="0"+day;
		   }
		   month = String.valueOf(m);
		   if(m<10){
			   month="0"+month;
		   }
		   year = String.valueOf(y);
		   
		   i=extr.getInt("i");
	      }
	      
	      db =new database(this);
		   	mydb = new DbHelper_student(this);
		      ArrayList array_list = mydb.getAllCotacts(sub2);
		     
		      ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
		      obj = (ListView)findViewById(R.id.listView1);
		      obj.setAdapter(arrayAdapter);
		      registerForContextMenu(obj);
		     
		      
	      
	     // Toast.makeText(getApplicationContext(), sub2, Toast.LENGTH_SHORT).show();
	      
	      //List
	      
	      
	      obj.setOnItemClickListener(new OnItemClickListener(){
	         @Override
	         public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	            // TODO Auto-generated method stub
	            int id_To_Search = arg2 + 1;
	           student= (String)arg0.getItemAtPosition(arg2);
	            
	            Bundle dataBundle = new Bundle();
	            dataBundle.putInt("id2", id_To_Search);
	            dataBundle.putString("sub3",sub2);
	           if(i==1){
	            Intent intent = new Intent(getApplicationContext(),student_info.class);
	            intent.putExtras(dataBundle);
	            startActivity(intent);
	         }
	         
	         
	           	
	         }
	      });
	   }
	     
	      public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuinfo)
		   {
	    	  
	    	 
	    	  
	    	  AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuinfo;
	    	  ob = (String)obj.getItemAtPosition(acmi.position); 
			   super.onCreateContextMenu(menu,v,menuinfo);
			   menu.setHeaderTitle("Select The Action");
			   if(i==2){
	           menu.add(0, v.getId(), 0, "Total Attendance");//groupId, itemId, order, title 
	           menu.add(0, v.getId(), 0, "Date Wise Attendance");
			   }
			   if(i==0){
	           menu.add(0, v.getId(), 0, "Present");
	           menu.add(0, v.getId(), 0, "Absent");
		   }
		   }
		   public boolean onContextItemSelected(MenuItem item)
		   {    
	           if(item.getTitle()=="Total Attendance")
	           {  
	        	   Cursor rs = mydb.attendance(ob,sub2);
	        	   rs.moveToFirst();
	        	   r = rs.getString(rs.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_ATTENDANCE));
	        	   
	        	   Cursor cr = mydb.classes(ob,sub2);
	        	   cr.moveToFirst();
	        	   c = cr.getString(cr.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_CLASSES));
	        	   Bundle dataBundle = new Bundle();
	        	   dataBundle.putString("r", r);
	        	   dataBundle.putString("c", c);
	        	   Intent in =new Intent(Student_List.this,display_attendance.class);
	        	   in.putExtras(dataBundle);
	        	   startActivity(in);
	        	   
	        	   //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
	        	   
	           }
	           else if(item.getTitle()=="Date Wise Attendance")
	           {
	        	   Bundle dataBun = new Bundle();
	        	   dataBun.putString("student", ob);
	        	   dataBun.putString("subject", sub2);
	        	   
	        	   Intent in =new Intent(Student_List.this,Date_Wise.class);
	        	   in.putExtras(dataBun);
	        	   startActivity(in);
	           }
	           else if(item.getTitle()=="Present")
	           {  
	        	   Cursor rs = mydb.attendance(ob,sub2);
	        	   rs.moveToFirst();
	        	   r = rs.getString(rs.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_ATTENDANCE));
	        	   
	        	   Cursor cr = mydb.classes(ob,sub2);
	        	   cr.moveToFirst();
	        	   c = cr.getString(cr.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_CLASSES));
	        	   int a =Integer.parseInt(r);
	        	   int b =Integer.parseInt(c);
	        	   a++;
	        	   b++;
	        	    s = String.valueOf(a);
	        	    t=	String.valueOf(b);
	        	   mydb.insertAttendance(ob, sub2, s);
	        	   mydb.insertClasses(ob, sub2, t);
	        	   db.insertTable(ob,sub2,day,month,year,"Present");
	        	   Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
	           }
	           else if(item.getTitle()=="Absent")
	           {
	        	   Cursor cr = mydb.classes(ob,sub2);
	        	   cr.moveToFirst();
	        	   c = cr.getString(cr.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_CLASSES));
	        	   int b =Integer.parseInt(c);
	        	   b++;
	        	   t=	String.valueOf(b);
	        	   mydb.insertClasses(ob, sub2, t);
	        	   db.insertTable(ob,sub2,day,month,year,"Absent");
	        	   Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
	           }
	           else 
	           {  
	              return false;  
	           }    
	         return true;    
	     }    
	      
	   
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
		   if(i==1){
	      getMenuInflater().inflate(R.menu.student__list, menu);
	      
	   }
		   return true;
	   }
	   
	   @Override
	   public boolean onOptionsItemSelected(MenuItem item){
	      super.onOptionsItemSelected(item);
	      
	      switch(item.getItemId())
	      {
	         case R.id.ite:Bundle dataBundle = new Bundle();
	         dataBundle.putInt("id2", 0);
	         dataBundle.putString("sub3", sub2);
	         
	         Intent intent = new Intent(getApplicationContext(),student_info.class);
	         intent.putExtras(dataBundle);
	         
	         startActivity(intent);
	         return true;
	         default:
	         return super.onOptionsItemSelected(item);
	      }
	   }
	   
	  
	}