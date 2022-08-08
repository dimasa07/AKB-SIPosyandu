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
import com.androidnetworking.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;
import android.content.*;
import com.akb.siposyandu.constants.*;


public class BerandaActivity extends AppCompatActivity{

	private SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		pref = getSharedPreferences(ConstantVariables.APP_PREFERENCESS,MODE_PRIVATE);
		String username = pref.getString("username","Guest");
		String level = pref.getString("level","Guest");
		Toast.makeText(this,"Selamat datang "+username+" ("+level+")",Toast.LENGTH_LONG).show();

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}


}
