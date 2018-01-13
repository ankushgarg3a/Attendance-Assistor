package com.example.attendance_assistor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class delete_subject_list extends Activity{
	
	DbHelper_subject mydb;
	ListView obj;
	public int id_To_Search;

	
	 protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_subject__list);
	      
	      mydb = new DbHelper_subject(this);
	      ArrayList array_list = mydb.getAllSubjects();
	      ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
	      
	      obj = (ListView)findViewById(R.id.listViewSubjects);
	      obj.setAdapter(arrayAdapter);
	      obj.setOnItemClickListener(new OnItemClickListener(){
	         @Override
	         public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	            // TODO Auto-generated method stub
	            id_To_Search = arg2 + 1;
	            
	            Bundle data = new Bundle();
	            data.putInt("id", id_To_Search);

	            Intent intent = new Intent(getApplicationContext(),delete_subject.class);
	            intent.putExtras(data);
	            startActivity(intent);
	         	}
	      }
	      );
	 }
}
