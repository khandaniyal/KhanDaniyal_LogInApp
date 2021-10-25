package com.example.recyclerview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uspherejda.Model.SatelliteForm;
import com.example.uspherejda.R;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final ArrayList<SatelliteForm> nameArr;

    public RecyclerViewAdapter(ArrayList<SatelliteForm> arrN){
        nameArr = arrN;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameLabel.setText(nameArr.get(position).getName());
    }

    @Override
    public int getItemCount() { return nameArr.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameLabel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.userName);
        }
    }
}