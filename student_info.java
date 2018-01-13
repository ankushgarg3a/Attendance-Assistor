package com.example.attendance_assistor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class student_info extends Activity {
	  
	   private DbHelper_student mydb ;
	  
	   TextView name ;
	   TextView phone;
	   TextView email;
	   int id_To_Update = 0;
	   
	   
	   public  String sub4;
	   
	  
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_student_info);
	      
	     
	      mydb = new DbHelper_student(this);
	     
	      Bundle extras = getIntent().getExtras(); 
	      if(extras != null)
	      {
		   sub4 = extras.getString("sub3");
	      }   
		  // Toast.makeText(getApplicationContext(), sub4, Toast.LENGTH_SHORT).show();
		   
	      name = (TextView) findViewById(R.id.editTextName);
	      phone = (TextView) findViewById(R.id.editTextPhone);
	      email = (TextView) findViewById(R.id.editTextEmail);
	     
	     

	     
	      if(extras !=null)
	      {
	         int Value = extras.getInt("id2");
	         
	         if(Value>0){
	            //means this is the view part not the add contact part.
	            Cursor rs = mydb.getData(Value);
	            id_To_Update = Value;
	            rs.moveToFirst();
	            
	            String nam = rs.getString(rs.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_NAME));
	            String phon = rs.getString(rs.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_PHONE));
	            String emai = rs.getString(rs.getColumnIndex(DbHelper_student.CONTACTS_COLUMN_EMAIL));
	            
	            
	            if (!rs.isClosed()) 
	            {
	               rs.close();
	            }
	            Button b = (Button)findViewById(R.id.button1);
	            b.setVisibility(View.INVISIBLE);

	            name.setText((CharSequence)nam);
	            name.setFocusable(false);
	            name.setClickable(false);

	            phone.setText((CharSequence)phon);
	            phone.setFocusable(false); 
	            phone.setClickable(false);

	            email.setText((CharSequence)emai);
	            email.setFocusable(false);
	            email.setClickable(false);

	           
	         }
	      }
	   }
	   
	   
	   //menu
	   
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      Bundle extras = getIntent().getExtras(); 
	      
	      if(extras !=null)
	      {
	         int Value = extras.getInt("id2");
	         if(Value>0){
	            getMenuInflater().inflate(R.menu.displaystudent, menu);
	         }
	         
	         else{
	            getMenuInflater().inflate(R.menu.student__list, menu);
	         }
	      }
	      return true;
	   }

	   
	 //save and edit
	   public boolean onOptionsItemSelected(MenuItem item) 
	   { 
	      super.onOptionsItemSelected(item); 
	      switch(item.getItemId()) 
	   { 
	      case R.id.Edit_Student: 
	      Button b = (Button)findViewById(R.id.button1);
	      b.setVisibility(View.VISIBLE);
	      name.setEnabled(true);
	      name.setFocusableInTouchMode(true);
	      name.setClickable(true);

	      phone.setEnabled(true);
	      phone.setFocusableInTouchMode(true);
	      phone.setClickable(true);

	      email.setEnabled(true);
	      email.setFocusableInTouchMode(true);
	      email.setClickable(true);

	      

	      return true;
	      
	      //delete
	      case R.id.Delete_Student:

	      AlertDialog.Builder builder = new AlertDialog.Builder(this);
	      builder.setMessage(R.string.deleteSubject)
	      .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int id) {
	            mydb.deleteContact(id_To_Update);
	            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show(); 
	            Bundle extras = getIntent().getExtras();
	            extras.putString("sub1", sub4);
	            extras.putInt("i", 1);
	            Intent intent = new Intent(getApplicationContext(),Student_List.class);
	            intent.putExtras(extras);
	            startActivity(intent);
	         }
	      })
	      .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int id) {
	            // User cancelled the dialog
	         }
	      });
	      AlertDialog d = builder.create();
	      d.setTitle("Are you sure");
	      d.show();

	      return true;
	      default: 
	      return super.onOptionsItemSelected(item); 

	      } 
	   } 
	   
	   
	//save
	   public void run(View view)
	   {	
	      Bundle extras = getIntent().getExtras();
	      if(extras !=null)
	      {
	         int Value = extras.getInt("id2");
	         if(Value>0){
	            if(mydb.updateContact(id_To_Update,name.getText().toString(), phone.getText().toString(), email.getText().toString())){
	               Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
	               extras.putString("sub1", sub4);
	               extras.putInt("i", 1);
	               Intent intent = new Intent(getApplicationContext(),Student_List.class);
	               intent.putExtras(extras);
	               startActivity(intent);
	            }		
	            else{
	               Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();	
	            }
	         }
	         else{
	            if(mydb.insertContact(name.getText().toString(), phone.getText().toString(), email.getText().toString(),sub4)){
	               Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
	               
	              
	            }		
	            
	            else{
	               Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
	            }
	            
	            extras.putString("sub1", sub4);
	            extras.putInt("i", 1);
	            Intent intent = new Intent(getApplicationContext(),Student_List.class);
	            intent.putExtras(extras);
	            startActivity(intent);
	         }
	      }
	   }
	}
