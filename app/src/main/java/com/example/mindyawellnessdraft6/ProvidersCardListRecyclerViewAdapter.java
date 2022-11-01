package com.example.mindyawellnessdraft6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProvidersCardListRecyclerViewAdapter extends RecyclerView.Adapter<ProvidersCardListRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Provider> providers = new ArrayList<>();
    private Context context;

    public ProvidersCardListRecyclerViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_provider_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.providerCardProviderName.setText(providers.get(position).getProviderName());
        holder.providerCardTherapistType.setText(providers.get(position).getTherapistType());

        holder.providerCardListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to Provider Profile Page Card View
                Intent intent = new Intent(context, CustomerProviderProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return providers.size();
    }

    public void setProviders(ArrayList<Provider> providers){
        this.providers = providers;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView providerCardListCardView;
        private ImageView providerCardImageView;
        private TextView providerCardProviderName;
        private TextView providerCardTherapistType;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            providerCardListCardView = itemView.findViewById(R.id.providerCardListCardView);
            providerCardImageView = itemView.findViewById(R.id.providerCardImageView);
            providerCardProviderName = itemView.findViewById(R.id.providerCardProviderName);
            providerCardTherapistType = itemView.findViewById(R.id.providerCardTherapistType);


        }
    }
}
