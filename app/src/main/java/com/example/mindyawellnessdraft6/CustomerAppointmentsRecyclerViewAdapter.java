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

public class CustomerAppointmentsRecyclerViewAdapter extends RecyclerView.Adapter<CustomerAppointmentsRecyclerViewAdapter.ViewHolder>{

    ArrayList<Appointment> customerAppointments = new ArrayList<>();
    private Context context;

    public CustomerAppointmentsRecyclerViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customer_appointments_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.customerAppointmentTitleTextView.setText(customerAppointments.get(position).getAppointmentTitle());
        holder.customerAppointmentDateTextView.setText(customerAppointments.get(position).getAppointmentDate());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(customerAppointments.get(position).getProviderUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String providerName = snapshot.child("privateInfo").child("fullName").getValue().toString();
                holder.customerAppointmentProviderTextView.setText(providerName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.customerAppointmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Have a Bundle here that passes in the appointmentId
                Bundle bundle = new Bundle();
                bundle.putString("appointmentId", customerAppointments.get(position).getAppointmentId());
                Intent intent = new Intent(context, AppointmentEntryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerAppointments.size();
    }

    public void setCustomerAppointments(ArrayList<Appointment> customerAppointments){
        this.customerAppointments = customerAppointments;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView customerAppointmentCardView;
        private TextView customerAppointmentTitleTextView;
        private TextView customerAppointmentDateTextView;
        private TextView customerAppointmentProviderTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            customerAppointmentCardView = itemView.findViewById(R.id.customerAppointmentCardView);
            customerAppointmentTitleTextView = itemView.findViewById(R.id.customerAppointmentTitleTextView);
            customerAppointmentDateTextView = itemView.findViewById(R.id.customerAppointmentDateTextView);
            customerAppointmentProviderTextView = itemView.findViewById(R.id.customerAppointmentProviderTextView);


        }
    }

}
