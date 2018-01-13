package com.example.attendance_assistor;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Subject_List extends Activity {
   
  public String sub;
   private ListView obj;
   DbHelper_subject mydb;
   public int id_To_Search;
   public int i;
   
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_subject__list);
      
      
      Bundle extras = getIntent().getExtras();
      if(extras != null){
      i= extras.getInt("i");
      }
      mydb = new DbHelper_subject(this);
      ArrayList array_list = mydb.getAllSubjects();
      ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
      
      
      //List
      
      obj = (ListView)findViewById(R.id.listViewSubjects);
      obj.setAdapter(arrayAdapter);
      obj.setOnItemClickListener(new OnItemClickListener(){
         @Override
         public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            // TODO Auto-generated method stub
            id_To_Search = arg2 + 1;
            
           
            
            sub =(String)arg0.getItemAtPosition(arg2);
            Bundle data = new Bundle();
            data.putString("sub1", sub);
          
           if(i==0)
           {
            Intent intent = new Intent(getApplicationContext(),Calendar_View.class);
           
            intent.putExtras(data);
            startActivity(intent);
           }
           
           if(i==1 )
           {
        	   data.putInt("i", 1);
            Intent intent = new Intent(getApplicationContext(),Student_List.class);
           
            intent.putExtras(data);
            startActivity(intent);
           }
            
          // Toast.makeText(getApplicationContext(), sub, Toast.LENGTH_SHORT).show();
           if(i==2 )
           {
        	   data.putInt("i", 2);
            Intent intent = new Intent(getApplicationContext(),Student_List.class);
           
            intent.putExtras(data);
            startActivity(intent);
           }
      
         }
      });
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
	   if(i==1){
      getMenuInflater().inflate(R.menu.subject__list, menu);
	   }
      return true;
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item){
      super.onOptionsItemSelected(item);
      
      switch(item.getItemId())
      {
         case R.id.item:Bundle dataBundle = new Bundle();
         dataBundle.putInt("id", 0);
         dataBundle.putInt("i", 1);
         Intent intent = new Intent(getApplicationContext(),subject_info.class);
         intent.putExtras(dataBundle);
         
         startActivity(intent);
         return true;
         
         case R.id.itm: Bundle data = new Bundle();
         data.putInt("id", id_To_Search);
         data.putInt("i", 1);
        	 Intent i = new Intent(getApplicationContext(),delete_subject_list.class);
        	 i.putExtras(data);
        	 startActivity(i);
         default:
         return super.onOptionsItemSelected(item);
      }
   }
   
   
   

   
}