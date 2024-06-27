package com.example.project_kreditmotor_farihahamaliafikriafridaerviana;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PembayaranAngsuranActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pembayaran_angsuran);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pembayaran_angsuran, menu);
		return true;
	}

}
