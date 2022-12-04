package com.example.mindyawellnessdraft6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProviderRequestRecyclerViewAdapter extends RecyclerView.Adapter<ProviderRequestRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Appointment> customerRequests = new ArrayList<>();
    private Context context;

    public ProviderRequestRecyclerViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_provider_request_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(customerRequests.get(position).getCustomerUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstName = snapshot.child("privateInfo").child("firstName").getValue().toString();
                String lastName = snapshot.child("privateInfo").child("lastName").getValue().toString();
                String fullName = firstName +  " " + lastName;
                holder.providerRequestCustomerNameTextView.setText(fullName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.providerRequestDateTextView.setText(customerRequests.get(position).getAppointmentDate() + " " + customerRequests.get(position).getAppointmentTime());

        holder.providerRequestCheckImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If accepts, change value to 1 and have it displayed on customer side and display on provider side
                Toast.makeText(context, "You accepted", Toast.LENGTH_SHORT).show();

                String appointmentId = customerRequests.get(position).getAppointmentId();
                DatabaseReference appointmentDatabaseReference = FirebaseDatabase.getInstance().getReference().child("appointments").child(appointmentId);

                appointmentDatabaseReference.child("providerAccepted").setValue("1");

                notifyDataSetChanged();
            }
        });

        holder.providerRequestDenyImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If denied, delete the appointment from appointment folder
                Toast.makeText(context, "You declined", Toast.LENGTH_SHORT).show();

                String appointmentId = customerRequests.get(position).getAppointmentId();
                DatabaseReference appointmentDatabaseReference = FirebaseDatabase.getInstance().getReference().child("appointments").child(appointmentId);

                appointmentDatabaseReference.removeValue();

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerRequests.size();
    }

    public void setCustomerRequests(ArrayList<Appointment> customerRequests){
        this.customerRequests = customerRequests;

        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView providerRequestCard;
        private MaterialTextView providerRequestCustomerNameTextView;
        private MaterialTextView providerRequestDateTextView;
        private ImageButton providerRequestCheckImageButton;
        private ImageButton providerRequestDenyImageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            providerRequestCard = itemView.findViewById(R.id.providerRequestCard);
            providerRequestCustomerNameTextView = itemView.findViewById(R.id.providerRequestCustomerNameTextView);
            providerRequestDateTextView = itemView.findViewById(R.id.providerRequestDateTextView);
            providerRequestCheckImageButton = itemView.findViewById(R.id.providerRequestCheckImageButton);
            providerRequestDenyImageButton = itemView.findViewById(R.id.providerRequestDenyImageButton);

        }
    }

}
