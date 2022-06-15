package com.dani.uspherejda.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dani.uspherejda.DB.SatFromHelper;
import com.dani.uspherejda.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Cards
    private CardView satList, addSat, satType, satCountries, aboutMe, goCrazy;
    //SQL
    private SatFromHelper dbHelper;
    private SQLiteDatabase db = null;

    public HomeFragment() {
        // Required empty public constructor
    }
    public HomeFragment(SatFromHelper dbHelper, SQLiteDatabase db){
        this.dbHelper = dbHelper;
        this.db = db;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        dbHelper = new SatFromHelper(getContext());
        db = dbHelper.getWritableDatabase();
        //Card views.
        satList = (CardView) view.findViewById(R.id.crdList);
        addSat = (CardView) view.findViewById(R.id.crdForm);
        satType = (CardView) view.findViewById(R.id.crdSatTypes);
        satCountries = (CardView) view.findViewById(R.id.crdCountries);
        aboutMe = (CardView) view.findViewById(R.id.crdAboutMe);
        goCrazy = (CardView) view.findViewById(R.id.crdGoCrayCraaaaay);
        //cardView Listeners
        satList.setOnClickListener(this::onClick);
        addSat.setOnClickListener(this::onClick);
        satType.setOnClickListener(this::onClick);
        satCountries.setOnClickListener(this::onClick);
        aboutMe.setOnClickListener(this::onClick);
        goCrazy.setOnClickListener(this::onClick);
        return view;
    }
    //Click listeners
    @Override
    public void onClick(View v){
        ImageView imgLogo = v.findViewById(R.id.imgLogo);
        switch (v.getId()){
            case R.id.crdList: getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment(dbHelper, db)).commit();
                break;
            case R.id.crdForm: getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddFragment(dbHelper, db)).commit();
                break;
            case R.id.crdSatTypes: Toast.makeText(getContext(), getString(R.string.toast_comingSonn), Toast.LENGTH_SHORT).show();
                break;
            case R.id.crdCountries: Toast.makeText(getContext(), getString(R.string.toast_comingSonn), Toast.LENGTH_SHORT).show();
                break;
            case R.id.crdAboutMe: Toast.makeText(getContext(),  getString(R.string.about_me), Toast.LENGTH_SHORT).show();
                break;
            case R.id.crdGoCrayCraaaaay: Toast.makeText(getContext(),  getString(R.string.go_crazy), Toast.LENGTH_SHORT).show();
            break;
        }
    }

}