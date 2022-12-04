package com.example.mindyawellnessdraft6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProviderAppointmentsRecyclerViewAdapter extends RecyclerView.Adapter<ProviderAppointmentsRecyclerViewAdapter.ViewHolder>{

    ArrayList<Appointment> providerAppointments = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(providerAppointments.get(position).getCustomerUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.providerAppointmentTitleTextView.setText(providerAppointments.get(position).getAppointmentTitle());

                String firstName = snapshot.child("privateInfo").child("firstName").getValue().toString();
                String lastName = snapshot.child("privateInfo").child("lastName").getValue().toString();

                holder.providerAppointmentClientTextView.setText(firstName + " " + lastName);
                holder.providerAppointmentDateTextView.setText(providerAppointments.get(position).getAppointmentDate() + " at " + providerAppointments.get(position).getAppointmentTime());
                holder.providerAppointmentCustomerTextView.setText(providerAppointments.get(position).getAppointmentDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        holder.providerAppointmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("appointmentId", providerAppointments.get(position).getAppointmentId());

                Intent intent = new Intent(context, AppointmentEntryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return providerAppointments.size();
    }

    public void setProviderAppointments(ArrayList<Appointment> providerAppointments) {
        this.providerAppointments = providerAppointments;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView providerAppointmentCardView;
        private TextView providerAppointmentTitleTextView;
        private TextView providerAppointmentClientTextView;
        private TextView providerAppointmentDateTextView;
        private TextView providerAppointmentCustomerTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            providerAppointmentCardView = itemView.findViewById(R.id.providerAppointmentCardView);
            providerAppointmentTitleTextView = itemView.findViewById(R.id.providerAppointmentTitleTextView);
            providerAppointmentClientTextView = itemView.findViewById(R.id.providerAppointmentClientTextView);
            providerAppointmentDateTextView = itemView.findViewById(R.id.providerAppointmentDateTextView);
            providerAppointmentCustomerTextView = itemView.findViewById(R.id.providerAppointmentCustomerTextView);

        }
    }
}
