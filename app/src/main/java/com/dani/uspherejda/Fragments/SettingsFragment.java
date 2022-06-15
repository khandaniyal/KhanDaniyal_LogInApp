package com.dani.uspherejda.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dani.uspherejda.DB.SatFromHelper;
import com.dani.uspherejda.LogIn;
import com.dani.uspherejda.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //
    private SatFromHelper dbHelper;
    private SQLiteDatabase db;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public SettingsFragment(SatFromHelper dbHelper, SQLiteDatabase db, Context context) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_settings, container, false);
        dbHelper = new SatFromHelper(getContext());
        //Buttons
        Button deleteSharedPrefs = view.findViewById(R.id.btnClearSharedPrefs);
        Button deleteDB = view.findViewById(R.id.btnDeleteDB);
        //Button listeners
        deleteSharedPrefs.setOnClickListener(this::onClick);
        deleteDB.setOnClickListener(this::onClick);
        return view;
    }
    //onClick method when the following buttons are pressed
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClearSharedPrefs: //clears de shared preferences
                LogIn.prefs.edit().clear().commit();
                Toast.makeText(getContext(), getString(R.string.prefs_cleared), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDeleteDB: //deletes the db, just to add something else
                dbHelper.onDelete(db);
                Toast.makeText(getContext(), getString(R.string.db_deleted), Toast.LENGTH_SHORT).show();
        }
    }
}