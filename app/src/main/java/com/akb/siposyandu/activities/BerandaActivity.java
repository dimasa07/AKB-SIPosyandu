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
import android.support.v4.view.*;


public class BerandaActivity extends AppCompatActivity{

	private SharedPreferences pref;
	private SharedPreferences.Editor editPref;
	private DrawerLayout drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		pref = ConstantVariables.APP_PREF;
		editPref = ConstantVariables.EDIT_PREF;
		
		String username = pref.getString("username","guest");
		String level = pref.getString("level","Guest");
		Toast.makeText(this,"Selamat datang "+username+" ("+level+")",Toast.LENGTH_LONG).show();

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawer = findViewById(R.id.drawer_layout);
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.menu_item_logout:
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setTitle("Logout");
				alert.setMessage("Yakin ingin logout ?");
				alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1, int p2){
							logout();
						}
					});
				alert.setNegativeButton(android.R.string.no,null);
				alert.show();
				break;
			case R.id.menu_item_keluar:
				onBackPressed();
				break;
		}
		return true;
	}

	@Override
	public void onBackPressed(){
		if(drawer.isDrawerOpen(GravityCompat.START)){
			drawer.closeDrawer(GravityCompat.START);
		}else{
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Keluar Aplikasi");
			alert.setMessage("Yakin ingin keluar ?");
			alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2){
						keluar();
					}
				});
			alert.setNegativeButton(android.R.string.no,null);
			alert.show();
		}
	}

	public void logout(){
		editPref.remove("username");
		editPref.commit();
		startActivity(new Intent(this, LoginActivity.class));
		finish();
	}
	
	public void keluar(){
		super.onBackPressed();
	}

	
}
