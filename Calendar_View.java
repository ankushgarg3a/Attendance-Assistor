package com.example.attendance_assistor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class Calendar_View extends Activity{
	
	CalendarView cal;
	public String s;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        
        Bundle extras = getIntent().getExtras();
        if(extras != null){
        	s= extras.getString("sub1");
        }
        initialize();
    }
        public void initialize(){
        	
        
        cal = (CalendarView) findViewById(R.id.calendarView1);
        
       
         long a = cal.getDate();
         a=a+86400000;
           cal.setMaxDate(a);
        
        cal.setOnDateChangeListener(new OnDateChangeListener() {
			
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
		
			month = month+1;
			Toast.makeText(getBaseContext(),"Selected Date is\n\n"
				+dayOfMonth+" : "+month+" : "+year , 
				Toast.LENGTH_LONG).show();
			
			Bundle dataBundle = new Bundle();
			dataBundle.putString("sub1", s);
			dataBundle.putInt("day", dayOfMonth);
			dataBundle.putInt("month", month);
			dataBundle.putInt("year", year);
			 Intent intent = new Intent(getApplicationContext(),Student_List.class);
			 intent.putExtras(dataBundle);
			 startActivity(intent);
		}
	});
        }

}
