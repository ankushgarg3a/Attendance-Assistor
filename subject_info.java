package com.example.attendance_assistor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class subject_info extends Activity {
	   
	   private DbHelper_subject mydb ;
	   
	   TextView name ;
	   TextView code;
	   int id_To_Update = 0;
	   
	   String sub4;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_subject_info);
	      name = (TextView) findViewById(R.id.editTextSubject);
	      code = (TextView) findViewById(R.id.editTextCode);
	      
	     
	      mydb = new DbHelper_subject(this);
	   }
	      
	   public void click(View view)
	   {	
	      Bundle extras = getIntent().getExtras();
	      if(extras !=null)
	      {
	         int Value = extras.getInt("id");
	         int i = extras.getInt("i");
	         if(Value>0){
	            if(mydb.updateSubject(id_To_Update,name.getText().toString(), code.getText().toString())){
	               Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();	
	               Bundle dataBundle = new Bundle();
	               dataBundle.putInt("i",i);
	               Intent intent = new Intent(getApplicationContext(),Subject_List.class);
	               intent.putExtras(dataBundle);
	               startActivity(intent);
	            }		
	            else{
	               Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();	
	            }
	         }
	         else{
	            if(mydb.insertSubject(name.getText().toString(), code.getText().toString())){
	               Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
	            }		
	            
	            else{
	               Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
	            }
	            Bundle dataBundle = new Bundle();
	               dataBundle.putInt("i",i);
	            Intent intent = new Intent(getApplicationContext(),Subject_List.class);
	            intent.putExtras(dataBundle);
	            startActivity(intent);
	         }
	      }
	   }
	}
