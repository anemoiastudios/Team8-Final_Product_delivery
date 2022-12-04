package com.example.mindyawellnessdraft6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class CustomerProvidersCardListRecyclerViewAdapter extends RecyclerView.Adapter<CustomerProvidersCardListRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Provider> providers = new ArrayList<>();
    private Context context;

    public CustomerProvidersCardListRecyclerViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customer_provider_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.providerCardProviderName.setText(providers.get(position).getProviderName());
        holder.providerCardTherapistType.setText(providers.get(position).getTherapistType());

        String providerUid = providers.get(position).getProviderUid();
        // Log.d("ProviderUid", providerUid);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(providerUid);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("users").child(providerUid).child(providerUid + "pp.jpg");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("profileInfo").child("profileImageUri").getValue().toString().equals("")){
                    try {
                        File localFile = File.createTempFile("tempfile", ".jpg");

                        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                holder.providerCardImageView.setImageBitmap(bitmap);

                            }
                        });


                    } catch(Exception e){
                        e.printStackTrace();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.providerCardListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set on click listener for each card where it changes the profile information depending on the provider picked
                Bundle bundle = new Bundle();
                bundle.putString("uid", providerUid);
                // Go to Provider Profile Page Card View and send the providerUid to the CustomerProviderProfileActivity
                Intent intent = new Intent(context, CustomerProviderProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
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
