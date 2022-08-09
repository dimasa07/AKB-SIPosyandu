package com.akb.siposyandu.fragments;

import com.akb.siposyandu.R;
import android.support.v4.app.Fragment;
import com.akb.siposyandu.activities.*;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.*;
import android.widget.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;
import com.androidnetworking.common.*;


public class TambahKaderFragment extends Fragment implements View.OnClickListener
{
	public BerandaActivity activity;
	private View root;
	private EditText edtNik,edtNama,edtNoTelepon,edtAlamat;
	private RadioGroup rgJK,rgStatus;

	public TambahKaderFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_tambah_kader, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		root = view;
		Button btnKembali = view.findViewById(R.id.tambah_kader_btn_kembali);
		Button btnTambah = view.findViewById(R.id.tambah_kader_btn_tambah);
		edtNik = view.findViewById(R.id.tambah_kader_edt_nik);
		edtNama = view.findViewById(R.id.tambah_kader_edt_nama);
		edtNoTelepon = view.findViewById(R.id.tambah_kader_edt_notel);
		edtAlamat = view.findViewById(R.id.tambah_kader_edt_alamat);
		rgJK = view.findViewById(R.id.tambah_kader_radio_jk);
		rgStatus = view.findViewById(R.id.tambah_kader_radio_status);
		
		btnTambah.setOnClickListener(this);
		btnKembali.setOnClickListener(this);
		
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onClick(View p1){
		switch(p1.getId()){
			case R.id.tambah_kader_btn_tambah:
				String nik = edtNik.getText().toString();
				String nama = edtNama.getText().toString();
				String noTel = edtNoTelepon.getText().toString();
				String alamat = edtAlamat.getText().toString();	
				int jkId = rgJK.getCheckedRadioButtonId();
				int statusId = rgStatus.getCheckedRadioButtonId();
				String jenisKelamin = ((RadioButton)root.findViewById(jkId)).getText().toString();
				String status = ((RadioButton)root.findViewById(statusId)).getText().toString();
				if(nik.isEmpty()||nama.isEmpty()||noTel.isEmpty()||alamat.isEmpty()){
					Toast.makeText(activity.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
				}else{
					tambah(nik,nama,noTel,alamat,jenisKelamin,status);
				}
				break;
			case R.id.tambah_kader_btn_kembali:
				activity.setFragment(DataKaderFragment.class);
				break;
		}
	}
	
	public void tambah(String nik, String nama, String noTel, String alamat, String jk, String status){
		AndroidNetworking.post(ConstantVariables.API + "tambah_kader.php")
			.addBodyParameter("nik",nik)
			.addBodyParameter("nama", nama)
			.addBodyParameter("jenis_kelamin", jk)
			.addBodyParameter("no_telepon", noTel)
			.addBodyParameter("alamat",alamat)
			.addBodyParameter("status",status)
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONObject(new JSONObjectRequestListener(){

				@Override
				public void onResponse(JSONObject p1){
					try{
						int value = p1.getInt("value");
						String message = p1.getString("message");

						Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
						if(value == 1){
							activity.setFragment(DataKaderFragment.class);
						}
					}catch(JSONException e){
						Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}

				@Override
				public void onError(ANError e){
					Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

				}
			});
	}

}
