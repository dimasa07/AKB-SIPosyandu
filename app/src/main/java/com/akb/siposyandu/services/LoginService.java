package com.akb.siposyandu.services;
import com.akb.siposyandu.models.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import com.androidnetworking.error.*;
import org.json.*;
import android.util.*;
import com.akb.siposyandu.activities.*;
import android.content.*;
import android.widget.*;

public class LoginService implements JSONObjectRequestListener,JSONArrayRequestListener
{
	
	private LoginActivity activity;
	
	public LoginService(LoginActivity activity){
		this.activity = activity;
	}

	@Override
	public void onResponse(JSONArray p1){
		// TODO: Implement this method
	}

	@Override
	public void onResponse(JSONObject jsonObject){
		// TODO: Implement this method
		String status = "";
		String message = "";
		String username ="";
		String password = "";
		String level = "";

		try{
			status = jsonObject.getString("status");
			message = jsonObject.getString("message");
			if(status.equals("sukses")){
				JSONObject value = jsonObject.getJSONObject("value");
				username = value.getString("username");
				password = value.getString("password");
				level = value.getString("level");
				SharedPreferences.Editor editPref = ConstantVariables.appEdit;
				editPref.putString("username",username);
				editPref.putString("password",password);
				editPref.putString("level",level);
				editPref.commit();
				activity.startActivity(
					new Intent(activity.getApplicationContext(),BerandaActivity.class));
				activity.finish();
			}else{
				Toast.makeText(activity.getApplicationContext(),message,Toast.LENGTH_LONG).show();
			}
		}catch(JSONException e){
			Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onError(ANError p1){
		// TODO: Implement this method
	}
	
	
	public void login(String username, String password){
		Log.d(ConstantVariables.LOG_TAG,"Sedang login...");
		AndroidNetworking.post(ConstantVariables.API+"login.php")
		.addBodyParameter("username",username)
		.addBodyParameter("password",password)
		.setPriority(Priority.MEDIUM)
		.build()
		.getAsJSONObject(this);
	}

}
