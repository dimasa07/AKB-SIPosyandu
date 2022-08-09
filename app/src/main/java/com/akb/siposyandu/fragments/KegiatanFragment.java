package com.akb.siposyandu.fragments;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import com.akb.siposyandu.R;

public class KegiatanFragment extends Fragment
{
	private Activity activity;
	
	public KegiatanFragment(Activity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_kegiatan,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
	}
	
	
}
