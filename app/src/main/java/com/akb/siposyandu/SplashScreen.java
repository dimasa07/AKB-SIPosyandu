package com.akb.siposyandu;

import android.support.v7.app.*;
import android.os.*;
import android.content.*;
import com.akb.siposyandu.activities.*;
import com.akb.siposyandu.constants.*;


public class SplashScreen extends AppCompatActivity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		SharedPreferences pref = getSharedPreferences(ConstantVariables.APP_PREFERENCESS,MODE_PRIVATE);
		ConstantVariables.initPreferences(pref);
		boolean isLoggedIn = pref.getBoolean("isLoggedIn",false);

		Intent berandaIntent = new Intent(this,BerandaActivity.class);
		Intent loginIntent = new Intent(this,LoginActivity.class);
		if(isLoggedIn){
			start(berandaIntent);
		}else{
			start(loginIntent);
		}
	}

	public void start(final Intent intent){
		new Handler().postDelayed(new Runnable(){

				@Override
				public void run(){
					// TODO: Implement this method
					startActivity(intent);
					finish();
				}
			}, 2000);
	}

}
