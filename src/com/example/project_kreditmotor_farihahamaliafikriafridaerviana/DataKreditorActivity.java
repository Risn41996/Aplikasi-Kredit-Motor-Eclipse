package com.example.project_kreditmotor_farihahamaliafikriafridaerviana;

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
public class DataKreditorActivity extends Activity implements OnClickListener{
	Kreditor kreditor = new Kreditor();
	
	TableLayout tbKreditor;
	
	Button btTambahKreditor, btRefreshDataKreditor;
	
	ArrayList<Button> buttonEdit = new ArrayList<Button>();
	ArrayList<Button> buttonDelete = new ArrayList<Button>();
	
	JSONArray arrayKreditor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_kreditor);
		
		if(android.os.Build.VERSION.SDK_INT > 9){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		tbKreditor = (TableLayout) findViewById(R.id.tbKreditor);
		btTambahKreditor = (Button) findViewById(R.id.btTambahKreditor);
		btRefreshDataKreditor = (Button) findViewById(R.id.btRefreshDataKreditor);
		
		tampildataKreditor();
	}
	
	public void KlikbtTambahKreditor(View v){
		tambahKreditor();
	}
	
	public void KlikbtRefreshDataKreditor(View v){
		finish();
		startActivity(getIntent());
	}
	
	public void tampildataKreditor(){
		TableRow barisTabel = new TableRow(this);
		barisTabel.setBackgroundColor(Color.BLACK);
		
		//id header
		TextView viewHeaderNama = new TextView(this);
		TextView viewHeaderPekerjaan = new TextView(this);
		TextView viewHeaderTelp = new TextView(this);
		TextView viewHeaderAlamat = new TextView(this);
		TextView viewHeaderAction = new TextView(this);
		
		//memberi nama kolom haeder
		viewHeaderNama.setText("Nama");
		viewHeaderPekerjaan.setText("Pekerjaan");
		viewHeaderTelp.setText("Telp");
		viewHeaderAlamat.setText("Alamat");
		viewHeaderAction.setText("Action");
		
		viewHeaderNama.setTextColor(Color.WHITE);
		viewHeaderPekerjaan.setTextColor(Color.WHITE);
		viewHeaderTelp.setTextColor(Color.WHITE);
		viewHeaderAlamat.setTextColor(Color.WHITE);
		viewHeaderAction.setTextColor(Color.WHITE);
		
		//viewHeaderId.setPadding(5, 1, 5, 1);(left, top, right, bottom)
		viewHeaderNama.setPadding(5, 1, 5, 1);
		viewHeaderPekerjaan.setPadding(5, 1, 5, 1);
		viewHeaderTelp.setPadding(5, 1, 5, 1);
		viewHeaderAlamat.setPadding(5, 1, 5, 1);
		viewHeaderAction.setPadding(5, 1, 5, 1);
		
		//barisTabel.addView(viewHeaderId);
		barisTabel.addView(viewHeaderNama);
		barisTabel.addView(viewHeaderPekerjaan);
		barisTabel.addView(viewHeaderTelp);
		barisTabel.addView(viewHeaderAlamat);
		barisTabel.addView(viewHeaderAction);
		
		tbKreditor.addView(barisTabel, new
		TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		LayoutParams.WRAP_CONTENT));
		try {
			arrayKreditor = new JSONArray(kreditor.tampilKreditor());
			
			for(int i = 0; i < arrayKreditor.length(); i++){
				JSONObject jsonChildNode = arrayKreditor.getJSONObject(i);
				
				//ambil data dr nama table
				String idkreditor = jsonChildNode.optString("idkreditor");
				String nama = jsonChildNode.optString("nama");
				String pekerjaan = jsonChildNode.optString("pekerjaan");
				String telp = jsonChildNode.optString("telp");
				String alamat = jsonChildNode.optString("alamat");
				
				System.out.println("idkreditor :" + idkreditor);
				System.out.println("nama :" + nama);
				System.out.println("pekerjaan :" + pekerjaan);
				System.out.println("telp :" + telp);
				System.out.println("alamat :" + alamat);
						
				barisTabel = new TableRow(this);
				
				if(i % 2 == 0){
					barisTabel.setBackgroundColor(Color.LTGRAY);
				}
				
				TextView viewNama = new TextView(this);
				viewNama.setText(nama);
				viewNama.setPadding(5, 1, 5, 1);
				barisTabel.addView(viewNama);
				
				TextView viewPekerjaan = new TextView(this);
				viewPekerjaan.setText(pekerjaan);
				viewPekerjaan.setPadding(5, 1, 5, 1);
				barisTabel.addView(viewPekerjaan);
				
				TextView viewTelp = new TextView(this);
				viewTelp.setText(telp);
				viewTelp.setPadding(5, 1, 5, 1);
				barisTabel.addView(viewTelp);
				
				TextView viewAlamat = new TextView(this);
				viewAlamat.setText(alamat);
				viewAlamat.setPadding(5, 1, 5, 1);
				barisTabel.addView(viewAlamat);
				
				buttonEdit.add(i, new Button(this));
				buttonEdit.get(i).setId(Integer.parseInt(idkreditor));
				buttonEdit.get(i).setTag("Edit");
				buttonEdit.get(i).setText("Edit");
				buttonEdit.get(i).setOnClickListener(this);
				barisTabel.addView(buttonEdit.get(i));
				
				buttonDelete.add(i, new Button(this));
				buttonDelete.get(i).setId(Integer.parseInt(idkreditor));
				buttonDelete.get(i).setTag("Delete");
				buttonDelete.get(i).setText("Delete");
				buttonDelete.get(i).setOnClickListener(this);
				barisTabel.addView(buttonDelete.get(i));
				
				tbKreditor.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	
	//hapus motor dr id
		public void deleteKreditor(int id){
			kreditor.deleteKreditor(id);
			// restart acrtivity
			finish();
			startActivity(getIntent());
		}
	//edit dan ambil data motor by id
	public void getKreditorByNama(int idkreditor){
		String idkreditorEdit = null;
		String namaEdit = null;
		String pekerjaanEdit = null;
		String telpEdit = null;
		String alamatEdit = null;
		JSONArray arrayPersonal;
		
		try{
			arrayPersonal = new JSONArray(kreditor.getKreditorByIdkreditor(idkreditor));
			for(int i = 0; i < arrayPersonal.length(); i++){
				JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
				idkreditorEdit = jsonChildNode.optString("idkreditor");
				namaEdit = jsonChildNode.optString("nama");
				pekerjaanEdit = jsonChildNode.optString("pekerjaan");
				telpEdit = jsonChildNode.optString("telp");
				alamatEdit = jsonChildNode.optString("alamat");
			}
		}catch (JSONException e){
			e.printStackTrace();
		}
		
		LinearLayout layoutInput = new LinearLayout(this);
		layoutInput.setOrientation(LinearLayout.VERTICAL);
		
		//id tersembunyi di alertbuilder
		//final TextView viewNama = new TextView(this);
		//viewNama.setText("Nama ="+String.valueOf(namaEdit));
		//viewNama.setBackgroundColor(Color.TRANSPARENT);
		//viewNama.setTextColor(Color.WHITE);
		//viewNama.setTextSize(20);
		//layoutInput.addView(viewNama);
		
		//membuat edit text dialertbuilder
		final EditText editIdKreditor = new EditText(this);
		editIdKreditor.setText(idkreditorEdit);
		//layoutInput.addView(editKdPetugas);
		
		//membuat edit text dialertbuilder
		final EditText editNama = new EditText(this);
		editNama.setText(namaEdit);
		layoutInput.addView(editNama);
				
		//membuat edit text dialertbuilder
		final EditText editPekerjaan = new EditText(this);
		editPekerjaan.setText(pekerjaanEdit);
		layoutInput.addView(editPekerjaan);
		
		//membuat edit text dialertbuilder
		final EditText editTelp = new EditText(this);
		editTelp.setText(telpEdit);
		layoutInput.addView(editTelp);
		
		//membuat edit text dialert
		final EditText editAlamat = new EditText(this);
		editAlamat.setText(alamatEdit);
		layoutInput.addView(editAlamat);
		
		AlertDialog.Builder builderEditKreditor = new AlertDialog.Builder(this);
		builderEditKreditor.setTitle("Update Kreditor");
		builderEditKreditor.setView(layoutInput);
		builderEditKreditor.setPositiveButton("Update", new
				DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String idkreditor = editIdKreditor.getText().toString();
						String nama = editNama.getText().toString();
						String pekerjaan = editPekerjaan.getText().toString();
						String telp = editTelp.getText().toString();
						String alamat = editAlamat.getText().toString();
						
						System.out.println("IdKreditor : " + idkreditor + " Nama : " + nama +
								" Pekerjaan : " + pekerjaan + " Telp : " + telp + " Alamat : " + alamat);
						String laporan = kreditor.updateKreditor(editIdKreditor.getText().toString(), editNama.getText().toString(), 
								editPekerjaan.getText().toString(), editTelp.getText().toString(), 
								editAlamat.getText().toString());
						
						Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();
						
						// restart
						finish();
						startActivity(getIntent());
					}
				});
		//membuat Button Cancel
		builderEditKreditor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builderEditKreditor.show();
	}
	
	public void tambahKreditor(){
		/* layout akan ditampilkan pada alert */
		LinearLayout layoutInput = new LinearLayout(this);
		layoutInput.setOrientation(LinearLayout.VERTICAL);
		final EditText editNama = new EditText(this);
		editNama.setHint("Nama");
		layoutInput.addView(editNama);
		
		final EditText editPekerjaan = new EditText(this);
		editPekerjaan.setHint("Pekerjaan");
		layoutInput.addView(editPekerjaan);
		
		final EditText editTelp = new EditText(this);
		editTelp.setHint("Telp");
		layoutInput.addView(editTelp);
		
		final EditText editAlamat = new EditText(this);
		editAlamat.setHint("Alamat");
		layoutInput.addView(editAlamat);
		
		AlertDialog.Builder builderInsertKreditor = new AlertDialog.Builder(this);
		builderInsertKreditor.setTitle("Insert Kreditor");
		builderInsertKreditor.setView(layoutInput);
		builderInsertKreditor.setPositiveButton("Insert", new
				DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String nama = editNama.getText().toString();
						String pekerjaan = editPekerjaan.getText().toString();
						String telp = editTelp.getText().toString();
						String alamat = editAlamat.getText().toString();
						
						System.out.println("Nama : " + nama + " Pekerjaan : " +
						pekerjaan + " Telp : " + telp + " Alamat : " + alamat);
						
						String laporan = kreditor.insertKreditor(nama, pekerjaan, telp, alamat);
						
						Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();
						
						finish();
						startActivity(getIntent());
					}
				});
		builderInsertKreditor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builderInsertKreditor.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_kreditor, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		for (int i = 0; i < buttonEdit.size(); i++){
			
			if(view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")){
				int idkreditor = buttonEdit.get(i).getId();
				
				getKreditorByNama(idkreditor);
			}else if(view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")){
				int idkreditor = buttonDelete.get(i).getId();
				deleteKreditor(idkreditor);
			}
		}
	}

}
