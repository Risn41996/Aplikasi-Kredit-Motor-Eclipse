package com.example.project_kreditmotor_farihahamaliafikriafridaerviana;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class TransaksiActivity extends Activity {
	Button btPengajuanKredit,btPembayaranAngsuran,btDataPengajuanKredit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaksi);
		
		btPengajuanKredit = (Button) findViewById(R.id.btPengajuanKredit);
		btPembayaranAngsuran = (Button) findViewById(R.id.btPembayaranAngsuran);
		btDataPengajuanKredit = (Button) findViewById(R.id.btDataPengajuanKredit);
	}
	
	public void KlikbtPengajuanKredit(View v){
		Intent i=new Intent(getApplicationContext(),
				PengajuanKreditActivity.class);
		startActivity(i);
	}
	
	public void KlikbtPembayaranAngsuran(View v){
		Intent i=new Intent(getApplicationContext(),
				PembayaranAngsuranActivity.class);
		startActivity(i);
	}
	
	public void KlikbtDataPengajuanKredit(View v){
		Intent i=new Intent(getApplicationContext(),
				DataPengajuanKreditActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaksi, menu);
		return true;
	}

}