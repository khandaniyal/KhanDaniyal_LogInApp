package com.dani.uspherejda.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani.uspherejda.Model.SatelliteForm;
import com.dani.uspherejda.R;

public class SatelliteInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_satellite_info, container, false);

        TextView satName = view.findViewById(R.id.lblGetSatInfoName);
        TextView countryName = view.findViewById(R.id.lblGetSatCountryInfoName);
        TextView categoryName = view.findViewById(R.id.lblGetSatCategoryInfoName);

        SatelliteForm satInfo = (SatelliteForm) getArguments().getSerializable("Sat");

        satName.setText(satInfo.getName());
        countryName.setText(satInfo.getCategory());
        categoryName.setText(satInfo.getCountry());
        return view;
    }
}