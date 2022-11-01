package com.example.mindyawellnessdraft6;

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

import java.util.ArrayList;

public class ProviderRequestRecyclerViewAdapter extends RecyclerView.Adapter<ProviderRequestRecyclerViewAdapter.ViewHolder>{

    private ArrayList<CustomerRequest> customerRequests = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.providerRequestCustomerNameTextView.setText(customerRequests.get(position).getCustomerName());
        holder.providerRequestDateTextView.setText(customerRequests.get(position).getMeetingDate());

        holder.providerRequestCheckImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You accepted", Toast.LENGTH_SHORT).show();
            }
        });

        holder.providerRequestDenyImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You declined", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerRequests.size();
    }

    public void setCustomerRequests(ArrayList<CustomerRequest> customerRequests){
        this.customerRequests = customerRequests;

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
