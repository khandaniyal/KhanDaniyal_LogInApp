package com.example.uspherejda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.recyclerview.RecyclerViewAdapter;
import com.example.uspherejda.DB.SatFromHelper;
import com.example.uspherejda.Model.SatelliteForm;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

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

    public ListFragment() {
        // Required empty public constructor
    }

    public ListFragment(SatFromHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(SatelliteForm param1, SatelliteForm param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, String.valueOf(param1));
        args.putString(ARG_PARAM2, String.valueOf(param2));
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

    //@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listView = inflater.inflate(R.layout.fragment_list, container, false);
        dbHelper = new SatFromHelper(getContext());
        db = dbHelper.getWritableDatabase();
        RecyclerView recyclerView = listView.findViewById(R.id.recyclerView);
        Button deleteEntries = listView.findViewById(R.id.btnDeleteEntries);
        ArrayList<SatelliteForm> arraySatelite = dbHelper.getAllData(db);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(arraySatelite);
        //Auxiliar ItemTouchHelper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack1 = null;
        //Swipe on delete, it can be either right or left
        ItemTouchHelper.Callback itemTouchHelperCallback = itemTouchHelper(itemTouchHelperCallBack1, arraySatelite, dbHelper,
                                                            db, adapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        //need to add toast for confirmation
        deleteEntries.setOnClickListener(e -> showAlert("Delete", "Are you sure you want to delete all the current entries?", arraySatelite, db));
        return listView;
    }
    //this method deletes all the current entries
    public void deleteEntries(ArrayList<?> arraySatelite, SQLiteDatabase db) {
        if (arraySatelite != null && db != null) { //first checks if the list and the are not empty
            dbHelper.onDelete(db);
            arraySatelite = new ArrayList<>();
        }
        //After deleting the current entries refresh the current activity calling the same fragment
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment(dbHelper, db)).commit();
    }
    //this method will remove a specific satellite when swiped
    public ItemTouchHelper.SimpleCallback itemTouchHelper(ItemTouchHelper.SimpleCallback itemTouchHelperCallBack,
                                                          ArrayList<SatelliteForm> arraySatelite, SatFromHelper dbHelper,
                                                          SQLiteDatabase db,
                                                          RecyclerViewAdapter adapter){
        itemTouchHelperCallBack =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int deletedObject = viewHolder.getAdapterPosition();
                        Log.i("removeob", "" + deletedObject);
                        Log.i("removeob", "" + arraySatelite.get(deletedObject).getId());
                        dbHelper.removeSatelite(db, arraySatelite.get(deletedObject).getId());
                        arraySatelite.remove(deletedObject);//remove swiped item
                        adapter.notifyDataSetChanged();
                    }
                };
        return itemTouchHelperCallBack;
    }
    //this method will alert with a dialog when the delte button is pressed
    public void showAlert(String title, String message, ArrayList<?> arraySatelite, SQLiteDatabase db){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(message)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //KO METHOD
            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteEntries(arraySatelite, db);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}