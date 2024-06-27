package com.example.project_kreditmotor_farihahamaliafikriafridaerviana;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

@SuppressLint("NewApi")
public class DataPetugasActivity extends Activity implements OnClickListener{
	
	Petugas petugas = new Petugas();
	
	TableLayout tbPetugas;
	
	Button btTambahPetugas, btRefreshDataPetugas;
	
	ArrayList<Button> buttonEdit = new ArrayList<Button>();
	ArrayList<Button> buttonDelete = new ArrayList<Button>();
	
	JSONArray arrayPetugas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_petugas);
		
		if(android.os.Build.VERSION.SDK_INT > 9){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		tbPetugas = (TableLayout) findViewById(R.id.tbpetugas);
		btTambahPetugas = (Button) findViewById(R.id.btTambahPetugas);
		btRefreshDataPetugas = (Button) findViewById(R.id.btRefreshDataPetugas);
		
		tampildataPetugas();
	}
	
	public void KlikbtTambahPetugas(View v){
		tambahPetugas();
	}
	
	public void klikRefreshDataMahasiswa(View v){
		finish();
		startActivity(getIntent());
	}
	
	public void tampildataPetugas(){
		TableRow barisTabel = new TableRow(this);
		barisTabel.setBackgroundColor(Color.BLACK);
		
		//id header
		TextView viewHeaderKdPetugas = new TextView(this);
		TextView viewHeaderNama = new TextView(this);
		TextView viewHeaderJabatan = new TextView(this);
		TextView viewHeaderAction = new TextView(this);
		
		//memberi nama kolom haeder
		viewHeaderKdPetugas.setText("KdPetugas");
		viewHeaderNama.setText("Nama");
		viewHeaderJabatan.setText("Jabatan");
		viewHeaderAction.setText("Action");
		
		viewHeaderKdPetugas.setTextColor(Color.WHITE);
		viewHeaderNama.setTextColor(Color.WHITE);
		viewHeaderJabatan.setTextColor(Color.WHITE);
		viewHeaderAction.setTextColor(Color.WHITE);
		
		//viewHeaderId.setPadding(5, 1, 5, 1);(left, top, right, bottom)
		viewHeaderKdPetugas.setPadding(5, 1, 5, 1);
		viewHeaderNama.setPadding(5, 1, 5, 1);
		viewHeaderJabatan.setPadding(5, 1, 5, 1);
		viewHeaderAction.setPadding(5, 1, 5, 1);
		
		//barisTabel.addView(viewHeaderId);
		barisTabel.addView(viewHeaderKdPetugas);
		barisTabel.addView(viewHeaderNama);
		barisTabel.addView(viewHeaderJabatan);
		barisTabel.addView(viewHeaderAction);
		
		tbPetugas.addView(barisTabel, new
		TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		LayoutParams.WRAP_CONTENT));
		try {
			arrayPetugas = new JSONArray(petugas.tampilPetugas());
			
			for(int i = 0; i < arrayPetugas.length(); i++){
				JSONObject jsonChildNode = arrayPetugas.getJSONObject(i);
				
				//ambil data dr nama table
				String idpetugas = jsonChildNode.optString("idpetugas");
				String kdpetugas = jsonChildNode.optString("kdpetugas");
				String nama = jsonChildNode.optString("nama");
				String jabatan = jsonChildNode.optString("jabatan");
				
				System.out.println("idpetugas :" + idpetugas);
				System.out.println("kdpetugas :" + kdpetugas);
				System.out.println("nama :" + nama);
				System.out.println("jabatan :" + jabatan);
						
				barisTabel = new TableRow(this);
				
				if(i % 2 == 0){
					barisTabel.setBackgroundColor(Color.LTGRAY);
				}
				
				TextView viewKdPetugas = new TextView(this);
				viewKdPetugas.setText(kdpetugas);
				viewKdPetugas.setPadding(5, 1, 5, 1);
				barisTabel.addView(viewKdPetugas);
				
				TextView viewNama = new TextView(this);
				viewNama.setText(nama);
				viewNama.setPadding(5, 1, 5, 1);
				barisTabel.addView(viewNama);
				
				TextView viewJabatan = new TextView(this);
				viewJabatan.setText(jabatan);
				viewJabatan.setPadding(5, 1, 5, 1);
				barisTabel.addView(viewJabatan);
				
				buttonEdit.add(i, new Button(this));
				buttonEdit.get(i).setId(Integer.parseInt(idpetugas));
				buttonEdit.get(i).setTag("Edit");
				buttonEdit.get(i).setText("Edit");
				buttonEdit.get(i).setOnClickListener(this);
				barisTabel.addView(buttonEdit.get(i));
				
				buttonDelete.add(i, new Button(this));
				buttonDelete.get(i).setId(Integer.parseInt(idpetugas));
				buttonDelete.get(i).setTag("Delete");
				buttonDelete.get(i).setText("Delete");
				buttonDelete.get(i).setOnClickListener(this);
				barisTabel.addView(buttonDelete.get(i));
				
				tbPetugas.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	
	public void deletePetugas(int idpetugas){
		petugas.deletePetugas(idpetugas);
		finish();
		startActivity(getIntent());
	}
	
	public void getPetugasByKdpetugas(int idpetugas){
		String idpetugasEdit = null;
		String kdpetugasEdit = null;
		String namaEdit = null;
		String jabatanEdit = null;
		JSONArray arrayPersonal;
		
		try{
			arrayPersonal = new JSONArray(petugas.getPetugasByKdpetugas(idpetugas));
			for(int i = 0; i < arrayPersonal.length(); i++){
				JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
				idpetugasEdit = jsonChildNode.optString("idpetugas");
				kdpetugasEdit = jsonChildNode.optString("kdpetugas");
				namaEdit = jsonChildNode.optString("nama");
				jabatanEdit = jsonChildNode.optString("jabatan");
			}
		}catch (JSONException e){
			e.printStackTrace();
		}
		
		LinearLayout layoutInput = new LinearLayout(this);
		layoutInput.setOrientation(LinearLayout.VERTICAL);
		
		//id tersembunyi di alertbuilder
		final TextView viewKdpetugas = new TextView(this);
		viewKdpetugas.setText("Kode ="+String.valueOf(kdpetugasEdit));
		viewKdpetugas.setBackgroundColor(Color.TRANSPARENT);
		viewKdpetugas.setTextColor(Color.WHITE);
		viewKdpetugas.setTextSize(20);
		layoutInput.addView(viewKdpetugas);
		
		//membuat edit text dialertbuilder
		final EditText editIdPetugas = new EditText(this);
		editIdPetugas.setText(idpetugasEdit);
		//layoutInput.addView(editKdPetugas);
		//membuat edit text dialertbuilder
		final EditText editNama = new EditText(this);
		editNama.setText(namaEdit);
		layoutInput.addView(editNama);
		
		//membuat edit text dialert
		final EditText editJabatan = new EditText(this);
		editJabatan.setText(jabatanEdit);
		layoutInput.addView(editJabatan);
		
		AlertDialog.Builder builderEditPetugas = new AlertDialog.Builder(this);
		builderEditPetugas.setTitle("Update Petugas");
		builderEditPetugas.setView(layoutInput);
		builderEditPetugas.setPositiveButton("Update", new
				DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String idpetugas = editIdPetugas.getText().toString();
						String kdpetugas = viewKdpetugas.getText().toString();
						String nama = editNama.getText().toString();
						String jabatan = editJabatan.getText().toString();
						
						System.out.println("IdPetugas : " + idpetugas + " KdPetugas : " + kdpetugas +
								" Nama : " + nama + " Jabatan : " + jabatan);
						String laporan = petugas.updatePetugas(idpetugas, kdpetugas, nama, jabatan);
						
						Toast.makeText(DataPetugasActivity.this, laporan, Toast.LENGTH_SHORT).show();
						
						// restart
						finish();
						startActivity(getIntent());
					}
				});
		//membuat Button Cancel
		builderEditPetugas.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builderEditPetugas.show();
	}
	
	public void tambahPetugas(){
		/* layout akan ditampilkan pada alert */
		LinearLayout layoutInput = new LinearLayout(this);
		layoutInput.setOrientation(LinearLayout.VERTICAL);
		final EditText editKdPetugas = new EditText(this);
		editKdPetugas.setHint("KdPetugas");
		layoutInput.addView(editKdPetugas);
		
		final EditText editNama = new EditText(this);
		editNama.setHint("Nama");
		layoutInput.addView(editNama);
		
		final EditText editJabatan = new EditText(this);
		editJabatan.setHint("jabatan");
		layoutInput.addView(editJabatan);
		
		AlertDialog.Builder builderInsertPetugas = new AlertDialog.Builder(this);
		builderInsertPetugas.setTitle("Insert Petugas");
		builderInsertPetugas.setView(layoutInput);
		builderInsertPetugas.setPositiveButton("Insert", new
				DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String kdpetugas = editKdPetugas.getText().toString();
						String nama = editNama.getText().toString();
						String jabatan = editJabatan.getText().toString();
						
						System.out.println("KdPetugas : " + kdpetugas + " Nama : " +
						nama + " Jabatan : " + jabatan);
						
						String laporan = petugas.insertPetugas(kdpetugas, nama, jabatan);
						
						Toast.makeText(DataPetugasActivity.this, laporan, Toast.LENGTH_SHORT).show();
						
						finish();
						startActivity(getIntent());
					}
				});
		builderInsertPetugas.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builderInsertPetugas.show();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_petugas, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		for (int i = 0; i < buttonEdit.size(); i++){
			String kdpetugas;
			
			if(view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")){
				int idpetugas = buttonEdit.get(i).getId();
				
				getPetugasByKdpetugas(idpetugas);
			}else if(view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")){
				int idpetugas = buttonDelete.get(i).getId();
				deletePetugas(idpetugas);
			}
		}
	}
	
}