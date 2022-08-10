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
import com.akb.siposyandu.models.*;
import android.app.*;


public class TambahKaderFragment extends Fragment implements View.OnClickListener{
	public BerandaActivity activity;
	private View root;
	private TextView txtTitle;
	private Button btnTambah,btnKembali;
	private EditText edtNik,edtNama,edtNoTelepon,edtAlamat;
	private RadioGroup rgJK,rgStatus;
	private RadioButton rbMale,rbFemale,rbAktif,rbTidakAktif;
	private Kader kader;
	private String mode = "";

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
		txtTitle = view.findViewById(R.id.tambah_kader_title);
		btnKembali = view.findViewById(R.id.tambah_kader_btn_kembali);
		btnTambah = view.findViewById(R.id.tambah_kader_btn_tambah);
		edtNik = view.findViewById(R.id.tambah_kader_edt_nik);
		edtNama = view.findViewById(R.id.tambah_kader_edt_nama);
		edtNoTelepon = view.findViewById(R.id.tambah_kader_edt_notel);
		edtAlamat = view.findViewById(R.id.tambah_kader_edt_alamat);
		rgJK = view.findViewById(R.id.tambah_kader_radio_jk);
		rgStatus = view.findViewById(R.id.tambah_kader_radio_status);
		rbMale = view.findViewById(R.id.tambah_kader_radio_male);
		rbFemale = view.findViewById(R.id.tambah_kader_radio_female);
		rbAktif = view.findViewById(R.id.tambah_kader_radio_aktif);
		rbTidakAktif = view.findViewById(R.id.tambah_kader_radio_tidakaktif);

		btnTambah.setOnClickListener(this);
		btnKembali.setOnClickListener(this);

	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		if(mode.equals("VIEW")){
			btnTambah.setVisibility(View.GONE);
			edtNik.setEnabled(false);
			edtNama.setEnabled(false);
			edtAlamat.setEnabled(false);
			edtNoTelepon.setEnabled(false);
			rbFemale.setVisibility(View.GONE);
			rbTidakAktif.setVisibility(View.GONE);
			rbMale.setText(kader.getJenisKelamin());
			rbAktif.setText(kader.getStatus());
			rbMale.setChecked(true);
			rbAktif.setChecked(true);
		}else{
			btnTambah.setVisibility(View.VISIBLE);
			edtNik.setEnabled(true);
			edtNama.setEnabled(true);
			edtAlamat.setEnabled(true);
			edtNoTelepon.setEnabled(true);
			rbFemale.setVisibility(View.VISIBLE);
			rbTidakAktif.setVisibility(View.VISIBLE);
			rbMale.setText("LAKI-LAKI");
			rbAktif.setText("AKTIF");
		}
		if(kader != null){
			edtNik.setText(kader.getNik());
			edtNama.setText(kader.getNama());
			edtNoTelepon.setText(kader.getNoTelepon());
			edtAlamat.setText(kader.getAlamat());
			if(mode.equals("EDIT")){
				edtNik.setEnabled(false);
				txtTitle.setText("Edit Kader");
				btnTambah.setText("Edit");
			}else{
				txtTitle.setText("Data Kader");
			}
		}else{
			edtNik.setText("");
			edtNama.setText("");
			edtNoTelepon.setText("");
			edtAlamat.setText("");
			txtTitle.setText("Tambah Kader");
			btnTambah.setText("Tambah");
			
		}
	}


	@Override
	public void onDetach(){
		super.onDetach();
		kader = null;
		mode = "";
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
				if(nik.isEmpty() || nama.isEmpty() || noTel.isEmpty() || alamat.isEmpty()){
					Toast.makeText(activity.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
				}else{
					tambah(nik, nama, noTel, alamat, jenisKelamin, status);
				}
				break;
			case R.id.tambah_kader_btn_kembali:
				activity.setFragment(DataKaderFragment.class);
				break;
		}
	}

	public void tambah(String nik, String nama, String noTel, String alamat, String jk, String status){
		String action  = "tambah_kader.php";
		if(mode.equals("EDIT")){
			action = "edit_kader.php";
		}
		AndroidNetworking.post(ConstantVariables.API + action)
			.addBodyParameter("nik", nik)
			.addBodyParameter("nama", nama)
			.addBodyParameter("jenis_kelamin", jk)
			.addBodyParameter("no_telepon", noTel)
			.addBodyParameter("alamat", alamat)
			.addBodyParameter("status", status)
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

	public void setKader(Kader kader, String mode){
		this.kader = kader;
		this.mode = mode;
	}

}
