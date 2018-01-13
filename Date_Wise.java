package com.example.attendance_assistor;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Date_Wise extends Activity{
	
	database db;
	ListView obj;
	public String student,subject;
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.date_wise);
	      
	      Bundle extra = getIntent().getExtras();
	      if(extra != null){
	      student = extra.getString("student");
	      subject = extra.getString("subject");
	      
	      }
	      db =new database(this);
	      
	      ArrayList array_list = db.getAttendance(student,subject);
		     
	      ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
	      obj = (ListView)findViewById(R.id.list2);
	      obj.setAdapter(arrayAdapter);
	      
	      ArrayList array = db.getDate(student,subject);
		     
	      ArrayAdapter Adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array);
	      obj = (ListView)findViewById(R.id.list1);
	      obj.setAdapter(Adapter);
	      
}
}