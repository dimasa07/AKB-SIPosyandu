package com.akb.siposyandu.activities;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.widget.Toast;
import com.akb.siposyandu.*;

import android.support.v7.widget.Toolbar;
import android.support.v4.widget.*;
import com.akb.siposyandu.R;
import android.view.*;


public class BerandaActivity extends AppCompatActivity{

	static final String BASE_URL = "http://192.168.43.179/Android/FAN-Example/";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

//		AndroidNetworking.initialize(getApplicationContext());
//
//		AndroidNetworking.post(BASE_URL+"create.php")
//			.addBodyParameter("nama_mahasiswa", "Dims")
//			.addBodyParameter("nim_mahasiswa", "10119306")
//			.addBodyParameter("kelas_mahasiswa", "IF8")
//			.setTag("test")
//			.setPriority(Priority.MEDIUM)
//			.build()
//			.getAsJSONObject(new JSONObjectRequestListener() {
//				@Override
//				public void onResponse(JSONObject response) {
//					// do anything with response
//					Toast.makeText(BerandaActivity.this,"Sukses",Toast.LENGTH_LONG).show();
//				}
//				@Override
//				public void onError(ANError error) {
//					// handle error
//					Toast.makeText(BerandaActivity.this,"Something went wrong :"+error.getMessage(),Toast.LENGTH_LONG).show();
//
//				}
//			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater =getMenuInflater();
		inflater.inflate(R.menu.option_menu,menu);
		return true;
	}
	
	
}
