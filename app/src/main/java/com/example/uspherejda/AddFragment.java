package com.example.uspherejda;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uspherejda.DB.SatFromHelper;
import com.example.uspherejda.Model.SatelliteForm;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final java.lang.String ARG_PARAM1 = "param1";
    private static final java.lang.String ARG_PARAM2 = "param2";
    //SQL
    private SatFromHelper dbHelper;
    private SQLiteDatabase db;

    // TODO: Rename and change types of parameters
    private java.lang.String mParam1;
    private java.lang.String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    public AddFragment(SatFromHelper dbHelper, SQLiteDatabase db){
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(java.lang.String param1, java.lang.String param2) {
        AddFragment fragment = new AddFragment();
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
        View addView = inflater.inflate(R.layout.fragment_add, container, false);
        EditText satName = addView.findViewById(R.id.txtSatName);
        EditText countryName = addView.findViewById(R.id.txtCountry);
        EditText categoryName = addView.findViewById(R.id.txtCategory);
        Button save = addView.findViewById(R.id.btnSubmit);
        TextView saveState = addView.findViewById(R.id.txtSaveState);
        //dbHelper.createTable(db);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(satName.getText().toString().isEmpty() || countryName.getText().toString().isEmpty() || categoryName.getText().toString().isEmpty())){
                    //adds the current names into the constructor
                    SatelliteForm addForm = new SatelliteForm(satName.getText().toString(),
                                                              countryName.getText().toString(),
                                                              categoryName.getText().toString());
                    //Refresh current activity
                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddFragment(dbHelper, db)).commit();
                    //inserts the values into the db
                    dbHelper.insertContact(db, addForm);
                    saveState.setText("Saved!");
                }else{
                    saveState.setText("One or all the fields are empty");
                    Log.i("Campos", "Unos de los campos esta vacio");
                }
            }
        });
        return addView;
    }
}