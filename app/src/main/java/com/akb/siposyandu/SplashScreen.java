package com.akb.siposyandu;

import android.support.v7.app.*;
import android.os.*;
import android.content.*;
import com.akb.siposyandu.activities.*;


public class SplashScreen extends AppCompatActivity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		SharedPreferences userPref = getSharedPreferences("UserPref",MODE_PRIVATE);
		boolean isLoggedIn = userPref.getBoolean("isLoggedIn",true);

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
