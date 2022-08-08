package com.akb.siposyandu.constants;
import com.akb.siposyandu.services.*;
import android.content.*;
import com.akb.siposyandu.activities.*;

public class ConstantVariables
{
	public static final String LOG_TAG = "SIPosyandu";
	public static final String APP_PREFERENCESS ="app_preferences";
	public static final String API = "http://192.168.43.179/SIPosyandu/api/";
	
	public static SharedPreferences appPref;
	public static SharedPreferences.Editor appEdit;

	
	public static void initPreferences(SharedPreferences pref){
		appPref = pref;
		appEdit = pref.edit();
	}
}
