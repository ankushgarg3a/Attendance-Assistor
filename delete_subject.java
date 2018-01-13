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

public class delete_subject extends Activity {
	
	   TextView name ;
	   TextView code;
	   private DbHelper_subject mydb ;
	   int id_To_Update = 0;

	
	 protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.delete_subject_info);
	      name = (TextView) findViewById(R.id.editTextSubject);
	      code = (TextView) findViewById(R.id.editTextCode);
	      
	      
	      mydb = new DbHelper_subject(this);

	      Bundle extras = getIntent().getExtras();
	      
	   
	      if(extras !=null)
	      {
	         int Value = extras.getInt("id");
	         
	         if(Value>0){
	            //means this is the view part not the add contact part.
	            Cursor rs = mydb.getData(Value);
	            id_To_Update = Value;
	            rs.moveToFirst();
	            
	            String nam = rs.getString(rs.getColumnIndex(DbHelper_subject.SUBJECTS_COLUMN_NAME));
	            String cod = rs.getString(rs.getColumnIndex(DbHelper_subject.SUBJECTS_COLUMN_CODE));
	            
	            
	            
	            if (!rs.isClosed()) 
	            {
	               rs.close();
	            }
	            Button b = (Button)findViewById(R.id.button2);
	            b.setVisibility(View.INVISIBLE);

	            name.setText((CharSequence)nam);
	            name.setFocusable(false);
	            name.setClickable(false);

	            code.setText((CharSequence)cod);
	            code.setFocusable(false); 
	            code.setClickable(false);


	         	}
	      }
	 }
	      
	      public boolean onCreateOptionsMenu(Menu menu) {
		      // Inflate the menu; this adds items to the action bar if it is present.
		      Bundle extras = getIntent().getExtras(); 
		      
		      if(extras !=null)
		      {
		         int Value = extras.getInt("id");
		         if(Value>0){
		            getMenuInflater().inflate(R.menu.displaysubject, menu);
		         }
		         
		         else{
		            getMenuInflater().inflate(R.menu.subject__list, menu);
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
		      case R.id.Edit_Subject: 
		      Button b = (Button)findViewById(R.id.button2);
		      b.setVisibility(View.VISIBLE);
		      name.setEnabled(true);
		      name.setFocusableInTouchMode(true);
		      name.setClickable(true);

		      code.setEnabled(true);
		      code.setFocusableInTouchMode(true);
		      code.setClickable(true);

		      

		      

		      return true;
		      
		      //delete
		      case R.id.Delete_Subject:

		      AlertDialog.Builder builder = new AlertDialog.Builder(this);
		      builder.setMessage(R.string.deleteSubject)
		      .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int id) {
		        	 Bundle extras = getIntent().getExtras();
		            mydb.deleteSubject(id_To_Update);
		            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
		            extras.putInt("i", 1);
		            Intent intent = new Intent(getApplicationContext(),Subject_List.class);
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
		   

public void onclick(View view)
{	
   Bundle extras = getIntent().getExtras();
   if(extras !=null)
   {
      int Value = extras.getInt("id");
      if(Value>0){
         if(mydb.updateSubject(id_To_Update,name.getText().toString(), code.getText().toString())){
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            extras.putInt("i", 1);
            Intent intent = new Intent(getApplicationContext(),Subject_List.class);
            intent.putExtras(extras);
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
         extras.putInt("i", 1);
         Intent intent = new Intent(getApplicationContext(),Subject_List.class);
         intent.putExtras(extras);
         startActivity(intent);
      }
   }
}
}
