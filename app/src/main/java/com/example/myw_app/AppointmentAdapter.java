package com.example.myw_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private ArrayList<Appointment> appointmentList;

    public AppointmentAdapter(ArrayList<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_page_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
        holder.day_month_year_Text.setText(appointmentList.get(position).getDayDateYear());
        holder.cityText.setText(appointmentList.get(position).getLocation());
        holder.nameText.setText(appointmentList.get(position).getName());
        holder.timeText.setText(appointmentList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public void setEntries(ArrayList<Appointment> appointmentList){
        this.appointmentList = appointmentList;
        notifyDataSetChanged();

    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView day_month_year_Text;
        private TextView timeText;
        private TextView nameText;
        private TextView cityText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            day_month_year_Text = itemView.findViewById(R.id.day_month_year_Text);
            timeText = itemView.findViewById(R.id.timeText);
            nameText = itemView.findViewById(R.id.nameText);
            cityText = itemView.findViewById(R.id.cityText);

        }
    }
}


