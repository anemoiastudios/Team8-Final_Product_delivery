package com.example.mindyawellnessdraft6;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAppointmentsRecyclerViewAdapter extends RecyclerView.Adapter<CustomerAppointmentsRecyclerViewAdapter.ViewHolder>{

    ArrayList<CustomerAppointment> customerAppointments = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.customerAppointmentTitleTextView.setText(customerAppointments.get(position).getAppointmentTitle());
        holder.customerAppointmentDateTextView.setText(customerAppointments.get(position).getAppointmentDate());
        holder.customerAppointmentProviderTextView.setText(customerAppointments.get(position).getAppointmentProvider());

        holder.customerAppointmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CustomerAppointmentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerAppointments.size();
    }

    public void setCustomerAppointments(ArrayList<CustomerAppointment> customerAppointments){
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
