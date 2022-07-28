package com.akb.siposyandu;

import android.app.*;
import android.os.*;
import com.androidnetworking.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;
import android.widget.*;


public class MainActivity extends Activity{
	
	static final String BASE_URL = "http://192.168.43.179/Android/FAN-Example/";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		AndroidNetworking.initialize(getApplicationContext());
		
		AndroidNetworking.post(BASE_URL+"create.php")
			.addBodyParameter("nama_mahasiswa", "Dims")
			.addBodyParameter("nim_mahasiswa", "10119306")
			.addBodyParameter("kelas_mahasiswa", "IF8")
			.setTag("test")
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONObject(new JSONObjectRequestListener() {
				@Override
				public void onResponse(JSONObject response) {
					// do anything with response
					Toast.makeText(MainActivity.this,"Sukses",Toast.LENGTH_LONG).show();
				}
				@Override
				public void onError(ANError error) {
					// handle error
					Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
				
				}
			});
	}

}
