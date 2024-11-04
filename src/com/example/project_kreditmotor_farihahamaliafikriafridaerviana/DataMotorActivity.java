package com.example.project_kreditmotor_farihahamaliafikriaRisnawulansari;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DataMotorActivity extends Activity implements OnClickListener{
	
	Motor motor = new Motor();
	
	TableLayout tbMotor;
	
	Button btTambahMotor, btRefreshDataMotor;
	
	ArrayList<Button> buttonEdit = new ArrayList<Button>();
	ArrayList<Button> buttonDelete = new ArrayList<Button>();
	
	JSONArray arrayMotor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_motor);
		
		if(android.os.Build.VERSION.SDK_INT > 9){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		tbMotor = (TableLayout) findViewById(R.id.tbMotor);
		btTambahMotor = (Button) findViewById(R.id.btTambahMotor);
		btRefreshDataMotor = (Button) findViewById(R.id.btRefreshDataMotor);
		
		tampildataMotor();
	}
	
	public void KlikbtTambahMotor(View v){
		tambahMotor();
	}
	
	public void KlikbtRefreshDataMotor(View v){
		finish();
		startActivity(getIntent());
	}
	
	public void tampildataMotor(){
		TableRow barisTabel = new TableRow(this);
		barisTabel.setBackgroundColor(Color.BLACK);
		
		//id header
		TextView viewHeaderKdMotor = new TextView(this);
		TextView viewHeaderNama = new TextView(this);
		TextView viewHeaderHarga = new TextView(this);
		TextView viewHeaderAction = new TextView(this);
		
		//memberi nama kolom haeder
		viewHeaderKdMotor.setText("KdMotor");
		viewHeaderNama.setText("Nama");
		viewHeaderHarga.setText("Harga");
		viewHeaderAction.setText("Action");
		
		viewHeaderKdMotor.setTextColor(Color.WHITE);
		viewHeaderNama.setTextColor(Color.WHITE);
		viewHeaderHarga.setTextColor(Color.WHITE);
		viewHeaderAction.setTextColor(Color.WHITE);
		
		//viewHeaderId.setPadding(5, 1, 5, 1);(left, top, right, bottom)
		viewHeaderKdMotor.setPadding(5, 1, 5, 1);
		viewHeaderNama.setPadding(5, 1, 5, 1);
		viewHeaderHarga.setPadding(5, 1, 5, 1);
		viewHeaderAction.setPadding(5, 1, 5, 1);
		
		//barisTabel.addView(viewHeaderId);
		barisTabel.addView(viewHeaderKdMotor);
		barisTabel.addView(viewHeaderNama);
		barisTabel.addView(viewHeaderHarga);
		barisTabel.addView(viewHeaderAction);
		
		tbMotor.addView(barisTabel, new
		TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		LayoutParams.WRAP_CONTENT));
				try {
					arrayMotor = new JSONArray(motor.tampilMotor());
							
					for(int i = 0; i < arrayMotor.length(); i++){
					JSONObject jsonChildNode = arrayMotor.getJSONObject(i);
								
					//ambil data dr nama table
					String idmotor = jsonChildNode.optString("idmotor");
					String kdmotor = jsonChildNode.optString("kdmotor");
					String nama = jsonChildNode.optString("nama");
					String harga = jsonChildNode.optString("harga");
								
					System.out.println("idmotor :" + idmotor);
					System.out.println("kdmotor :" + kdmotor);
					System.out.println("nama :" + nama);
					System.out.println("harga :" + harga);
										
					barisTabel = new TableRow(this);
								
					if(i % 2 == 0){
						barisTabel.setBackgroundColor(Color.LTGRAY);
					}
								
					TextView viewKdMotor = new TextView(this);
					viewKdMotor.setText(kdmotor);
					viewKdMotor.setPadding(5, 1, 5, 1);
					barisTabel.addView(viewKdMotor);
								
					TextView viewNama = new TextView(this);
					viewNama.setText(nama);
					viewNama.setPadding(5, 1, 5, 1);
					barisTabel.addView(viewNama);
								
					TextView viewHarga = new TextView(this);
					viewHarga.setText(harga);
					viewHarga.setPadding(5, 1, 5, 1);
					barisTabel.addView(viewHarga);
								
					//membuat button edit
					buttonEdit.add(i, new Button(this));
					buttonEdit.get(i).setId(Integer.parseInt(idmotor));
					buttonEdit.get(i).setTag("Edit");
					buttonEdit.get(i).setText("Edit");
					buttonEdit.get(i).setOnClickListener(this);
					barisTabel.addView(buttonEdit.get(i));
								
					//button delete dibaris
					buttonDelete.add(i, new Button(this));
					buttonDelete.get(i).setId(Integer.parseInt(idmotor));
					buttonDelete.get(i).setTag("Delete");
					buttonDelete.get(i).setText("Delete");
					buttonDelete.get(i).setOnClickListener(this);
					barisTabel.addView(buttonDelete.get(i));
								
					tbMotor.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				}
			}catch(JSONException e){
				e.printStackTrace();
		}
	}
	
	//hapus motor dr id
	public void deleteMotor(int id){
		motor.deleteMotor(id);
		// restart acrtivity
		finish();
		startActivity(getIntent());
	}
	
	//ambil data motor by id
	public void getMotorByKdmotor(int idmotor){
		String idmotorEdit = null;
		String kdmotorEdit = null;
		String namaEdit = null;
		String hargaEdit = null;
		JSONArray arrayPersonal;
		
		try{
			arrayPersonal = new JSONArray(motor.getMotorByKdmotor(idmotor));
			for(int i = 0; i < arrayPersonal.length(); i++){
				JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
				idmotorEdit = jsonChildNode.optString("idmotor");
				kdmotorEdit = jsonChildNode.optString("kdmotor");
				namaEdit = jsonChildNode.optString("nama");
				hargaEdit = jsonChildNode.optString("harga");
			}
		}catch (JSONException e){
			e.printStackTrace();
		}
		
		LinearLayout layoutInput = new LinearLayout(this);
		layoutInput.setOrientation(LinearLayout.VERTICAL);
		
		//id tersembunyi di alertbuilder
		final TextView viewKdmotor = new TextView(this);
		viewKdmotor.setText("Kode Motor ="+String.valueOf(kdmotorEdit));
		viewKdmotor.setBackgroundColor(Color.TRANSPARENT);
		viewKdmotor.setTextColor(Color.WHITE);
		viewKdmotor.setTextSize(20);
		layoutInput.addView(viewKdmotor);
		
		//membuat edit text dialertbuilder
		final EditText editIdMotor = new EditText(this);
		editIdMotor.setText(idmotorEdit);
		//layoutInput.addView(editKdMotor);
		//membuat edit text dialertbuilder
		final EditText editNama = new EditText(this);
		editNama.setText(namaEdit);
		layoutInput.addView(editNama);
		
		//membuat edit text dialert
		final EditText editHarga = new EditText(this);
		editHarga.setText(hargaEdit);
		layoutInput.addView(editHarga);
		
		AlertDialog.Builder builderEditMotor = new AlertDialog.Builder(this);
		builderEditMotor.setTitle("Update Motor");
		builderEditMotor.setView(layoutInput);
		builderEditMotor.setPositiveButton("Update", new
				DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String idmotor = editIdMotor.getText().toString();
						String kdmotor = viewKdmotor.getText().toString();
						String nama = editNama.getText().toString();
						String harga = editHarga.getText().toString();
						
						System.out.println("IdMotor : " + idmotor + " KdMotor : " + kdmotor +
								" Nama : " + nama + " Harga : " + harga);
						String laporan = motor.updateMotor(editIdMotor.getText().toString(), viewKdmotor.getText().toString(),
								editNama.getText().toString(), editHarga.getText().toString());
						
						Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();
						
						// restart
						finish();
						startActivity(getIntent());
					}
				});
		//membuat Button Cancel
		builderEditMotor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builderEditMotor.show();
	}
	
	//metode tambah motor
	public void tambahMotor(){
		/* layout akan ditampilkan pada alert */
		LinearLayout layoutInput = new LinearLayout(this);
		layoutInput.setOrientation(LinearLayout.VERTICAL);
		final EditText editKdMotor = new EditText(this);
		editKdMotor.setHint("KdMotor");
		layoutInput.addView(editKdMotor);
		
		final EditText editNama = new EditText(this);
		editNama.setHint("Nama");
		layoutInput.addView(editNama);
		
		final EditText editHarga = new EditText(this);
		editHarga.setHint("harga");
		layoutInput.addView(editHarga);
		
		AlertDialog.Builder builderInsertMotor = new AlertDialog.Builder(this);
		builderInsertMotor.setTitle("Insert Motor");
		builderInsertMotor.setView(layoutInput);
		builderInsertMotor.setPositiveButton("Insert", new
				DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String kdmotor = editKdMotor.getText().toString();
						String nama = editNama.getText().toString();
						String harga = editHarga.getText().toString();
						
						System.out.println("KdMotor : " + kdmotor + " Nama : " +
						nama + " Harga : " + harga);
						
						String laporan = motor.insertMotor(kdmotor, nama, harga);
						
						Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();
						
						finish();
						startActivity(getIntent());
					}
				});
		builderInsertMotor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builderInsertMotor.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_motor, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		for (int i = 0; i < buttonEdit.size(); i++){
			
			if(view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")){
				int idmotor = buttonEdit.get(i).getId();
				
				getMotorByKdmotor(idmotor);
			}else if(view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")){
				int idmotor = buttonDelete.get(i).getId();
				deleteMotor(idmotor);
			}
		}
	}

}
