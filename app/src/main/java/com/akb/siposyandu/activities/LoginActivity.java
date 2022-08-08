package com.akb.siposyandu.activities;

import android.support.v7.app.*;
import android.os.*;
import com.akb.siposyandu.R;
import com.androidnetworking.AndroidNetworking;
import android.widget.*;
import android.view.View;
import com.akb.siposyandu.constants.*;
import android.content.*;
import com.akb.siposyandu.services.*;
import android.util.*;

public class LoginActivity extends AppCompatActivity implements Button.OnClickListener{

	private EditText edtUsername, edtPassword;
	private Button btnLogin, btnPendaftaran;
	private SharedPreferences pref;
	private LoginService loginService;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		edtUsername = findViewById(R.id.login_editText_username);
		edtPassword = findViewById(R.id.login_editText_password);
		btnLogin = findViewById(R.id.login_button_login);
		btnPendaftaran = findViewById(R.id.login_button_pendaftaran);
		pref = getSharedPreferences(ConstantVariables.APP_PREFERENCESS, MODE_PRIVATE);
		loginService = new LoginService(this);
		btnLogin.setOnClickListener(this);
		btnPendaftaran.setOnClickListener(this);

		AndroidNetworking.initialize(getApplicationContext());

	}

	@Override
	public void onClick(View v){
		switch(v.getId()){
			case R.id.login_button_login:
				login();
				break;
			case R.id.login_button_pendaftaran:
				pendaftaran();
				break;
		}
	}

	public void login(){
		String username = edtUsername.getText().toString();
		String password = edtPassword.getText().toString();

		if(username.isEmpty() || password.isEmpty()){
			Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_LONG).show();
		}else{
			Level level = null;
			if(username.equals("admin") && username.equals("admin")){
				level = Level.ADMIN;
				SharedPreferences.Editor editPref = pref.edit();
				editPref.putString("username", username);
				editPref.putString("level", level.toString());
				editPref.commit();
				Log.d(ConstantVariables.LOG_TAG, "Login sukses !");
				startActivity(new Intent(this, BerandaActivity.class));
				finish();
			}else{
				loginService.login(username,password);
			}
		}
	}

	public void pendaftaran(){

	}
}
