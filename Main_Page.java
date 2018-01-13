package com.example.attendance_assistor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main_Page extends Activity {
	
	Button b,c,d;
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.main_page);
	      
	      b= (Button)findViewById(R.id.mark);
	      b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Bundle dataBundle = new Bundle();
				dataBundle.putInt("i", 0);
				Intent i = new Intent(getApplicationContext(),Subject_List.class);
				i.putExtras(dataBundle);
				startActivity(i);
			}
	    	  
	      });
	      
	      c= (Button)findViewById(R.id.info);
	      c.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle dataBundle = new Bundle();
				dataBundle.putInt("i", 1);
				Intent i = new Intent(getApplicationContext(),Subject_List.class);
				i.putExtras(dataBundle);
				startActivity(i);
			}
	    	  
	      });
	      
	      d= (Button)findViewById(R.id.check);
	      d.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle dataBundle = new Bundle();
				dataBundle.putInt("i", 2);
				Intent i = new Intent(getApplicationContext(),Subject_List.class);
				i.putExtras(dataBundle);
				startActivity(i);
			}
	    	  
	      });
}
}
