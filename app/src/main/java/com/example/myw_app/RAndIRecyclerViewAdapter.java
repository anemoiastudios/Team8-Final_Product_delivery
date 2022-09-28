package com.example.myw_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RAndIRecyclerViewAdapter extends RecyclerView.Adapter<RAndIRecyclerViewAdapter.ViewHolder>{
    private ArrayList<RAndIEntry> entries = new ArrayList<>();
    private Context context;

    public RAndIRecyclerViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.randi_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.rAndITitleTextView.setText(entries.get(position).getrAndITitle());
        holder.rAndIDescriptionTextView.setText(entries.get(position).getrAndIDescription());

        holder.rAndICardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, entries.get(position).getrAndITitle() + " is clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setEntries(ArrayList<RAndIEntry> entries){
        this.entries = entries;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView rAndICardView;

        private TextView rAndITitleTextView;
        private TextView rAndIDescriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rAndICardView = itemView.findViewById(R.id.rAndICardView);

            rAndITitleTextView = itemView.findViewById(R.id.rAndITitleTextView);
            rAndIDescriptionTextView = itemView.findViewById(R.id.rAndIDescriptionTextView);
        }
    }
}
