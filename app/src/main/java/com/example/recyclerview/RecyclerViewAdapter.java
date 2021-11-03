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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameLabel.setText(nameArr.get(position).getName());
        holder.countryLabel.setText(nameArr.get(position).getCountry());
        holder.categoryLabel.setText(nameArr.get(position).getCategory());
    }

    @Override
    public int getItemCount() { return nameArr.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameLabel;
        TextView countryLabel;
        TextView categoryLabel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          /*  nameLabel = itemView.findViewById(R.id.lblName);
            countryLabel = itemView.findViewById(R.id.lblCountry);
            categoryLabel = itemView.findViewById(R.id.lblCategory);*/
            nameLabel = itemView.findViewById(R.id.lblSatName);
            countryLabel = itemView.findViewById(R.id.lblSatcountry);
            categoryLabel = itemView.findViewById(R.id.lblSatCategory);
        }
    }
}