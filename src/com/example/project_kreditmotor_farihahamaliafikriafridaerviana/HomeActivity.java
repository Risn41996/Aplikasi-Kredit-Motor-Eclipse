package com.example.project_kreditmotor_farihahamaliafikriafridaerviana;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {
	Button btDataPetugasHome,btDataMotorHome,btDataKreditorHome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		btDataPetugasHome = (Button) findViewById(R.id.btDataPetugasHome);
		btDataMotorHome = (Button) findViewById(R.id.btDataMotorHome);
		btDataKreditorHome = (Button) findViewById(R.id.btDataKreditorHome);
	}
	
	public void KlikbtDataPetugasHome(View v){
		Intent i=new Intent(getApplicationContext(),
				DataPetugasActivity.class);
		startActivity(i);
	}
	
	public void KlikbtDataMotorHome(View v){
		Intent i=new Intent(getApplicationContext(),
				DataMotorActivity.class);
		startActivity(i);
	}
	
	public void KlikbtDataKreditorHome(View v){
		Intent i=new Intent(getApplicationContext(),
				DataKreditorActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}