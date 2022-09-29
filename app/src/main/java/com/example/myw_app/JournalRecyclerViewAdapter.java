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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class JournalRecyclerViewAdapter extends RecyclerView.Adapter<JournalRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Journal> journals = new ArrayList<>();
    private Context context;

    public JournalRecyclerViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_item_view, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titleTextView.setText(journals.get(position).getJournalTitle());
        holder.writeHereTextView.setText(journals.get(position).getJournalDescription());

        holder.journalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, journals.get(position).getJournalTitle() + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return journals.size();
    }

    public void setJournals(ArrayList<Journal> journals){
        this.journals = journals;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView journalCardView;

        private TextView titleTextView;
        private TextView writeHereTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            journalCardView = itemView.findViewById(R.id.journalCardView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            writeHereTextView = itemView.findViewById(R.id.writeHereTextView);
        }
    }
}
