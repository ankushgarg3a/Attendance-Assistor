package com.example.attendance_assistor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class display_attendance extends Activity{
	 protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.display_attendance);
	      
	      TextView tv = (TextView) findViewById (R.id.var);
	      TextView tv2 = (TextView) findViewById (R.id.var2);
	      TextView tv3 = (TextView) findViewById (R.id.var3);
	      
	      
	      Bundle extras = getIntent().getExtras();
	     String b = extras.getString("r");
	     String c = extras.getString("c");
			  tv.setText(b);
			  tv2.setText(c);
			  
			  double present =Integer.parseInt(b);
			  double classes =Integer.parseInt(c);
			  if(classes != 0){
			  double percent = (present/classes)*100;
			  
			  String percent2 =Double.toString(percent);
			  tv3.setText(percent2);
			  }
			  else{
				  tv3.setText("0");
			  }
	 }
}
