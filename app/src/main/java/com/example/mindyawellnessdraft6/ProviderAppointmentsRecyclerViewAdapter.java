package com.example.mindyawellnessdraft6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProviderAppointmentsRecyclerViewAdapter extends RecyclerView.Adapter<ProviderAppointmentsRecyclerViewAdapter.ViewHolder>{

    ArrayList<ProviderAppointment> providerAppointments = new ArrayList<>();
    Context context;

    public ProviderAppointmentsRecyclerViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_provider_appointments_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.providerAppointmentTitleTextView.setText(providerAppointments.get(position).getAppointmentTitle());
        holder.providerAppointmentDateTextView.setText(providerAppointments.get(position).getAppointmentDate());
        holder.providerAppointmentCustomerTextView.setText(providerAppointments.get(position).getAppointmentTitle());

        holder.providerAppointmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProviderAppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return providerAppointments.size();
    }

    public void setProviderAppointments(ArrayList<ProviderAppointment> providerAppointments) {
        this.providerAppointments = providerAppointments;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView providerAppointmentCardView;
        private TextView providerAppointmentTitleTextView;
        private TextView providerAppointmentDateTextView;
        private TextView providerAppointmentCustomerTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            providerAppointmentCardView = itemView.findViewById(R.id.providerAppointmentCardView);
            providerAppointmentTitleTextView = itemView.findViewById(R.id.providerAppointmentTitleTextView);
            providerAppointmentDateTextView = itemView.findViewById(R.id.providerAppointmentDateTextView);
            providerAppointmentCustomerTextView = itemView.findViewById(R.id.providerAppointmentCustomerTextView);

        }
    }
}
