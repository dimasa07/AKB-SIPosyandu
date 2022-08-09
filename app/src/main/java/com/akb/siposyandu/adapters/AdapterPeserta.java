package com.akb.siposyandu.adapters;

import android.support.v7.widget.RecyclerView;
import java.util.List;
import com.akb.siposyandu.models.*;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.akb.siposyandu.R;
import java.text.NumberFormat;
import java.util.Locale;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.content.Context;
import android.util.Log;
import com.akb.siposyandu.constants.*;
import com.akb.siposyandu.fragments.*;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import android.os.Environment;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.error.ANError;

public class AdapterPeserta extends RecyclerView.Adapter<AdapterPeserta.ViewHolderBarang> implements View.OnClickListener{

	private List<Peserta> dataList;
	DataPesertaFragment fragment;

	public AdapterPeserta(DataPesertaFragment fragment,List<Peserta> dataList){
		this.dataList = dataList;
		this.fragment = fragment;
		Log.d(getClass().toString(),"Peserta list: "+dataList.size());
	}

	@Override
	public AdapterPeserta.ViewHolderBarang onCreateViewHolder(ViewGroup p1, int p2){
		// TODO: Implement this method
		LayoutInflater inflater = LayoutInflater.from(p1.getContext());
		View view = inflater.inflate(R.layout.card_peserta,p1,false);
		view.setOnClickListener(this);
		return new ViewHolderBarang(view);
	}

	@Override
	public void onBindViewHolder(AdapterPeserta.ViewHolderBarang holder, int position){
		// TODO: Implement this method
		holder.txtNama.setText("Nama : "+dataList.get(position).getNama());
		holder.txtNamaSuami.setText("Nama Suami: "+dataList.get(position).getNamaSuami());
	}

	@Override
	public int getItemCount(){
		// TODO: Implement this method
		return (dataList != null)?dataList.size() : 0;
	}

	@Override
	public void onClick(View p1){
		// TODO: Implement this method
		int itemPosition = fragment.recyclerView.getChildAdapterPosition(p1);
		Toast.makeText(
			fragment.activity.getApplicationContext(),
			dataList.get(itemPosition).getNama(),Toast.LENGTH_SHORT
		).show();
		
	}

	class ViewHolderBarang extends RecyclerView.ViewHolder{

		private TextView txtNama;
		private TextView txtNamaSuami;
		private CardView card_peserta;

		ViewHolderBarang(View itemView){
			super(itemView);
			txtNama = itemView.findViewById(R.id.card_peserta_nama);
			txtNamaSuami = itemView.findViewById(R.id.card_peserta_nama_suami);
			card_peserta = itemView.findViewById(R.id.card_peserta);
		}
	}
}

