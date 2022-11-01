package com.example.mindyawellnessdraft6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomerProviderProfileDiscussionListRecyclerViewAdapter extends RecyclerView.Adapter<CustomerProviderProfileDiscussionListRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Discussion> discussions = new ArrayList<>();
    private Context context;

    public CustomerProviderProfileDiscussionListRecyclerViewAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customer_provider_profile_discussion, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.customerProviderProfileDiscussionTitle.setText(discussions.get(position).getDiscussionTitle());
        holder.customerProviderProfileDiscussionDate.setText(discussions.get(position).getDiscussionDate());

        holder.customerProviderProfileDiscussionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You joined this discussion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return discussions.size();
    }

    public void setDiscussions(ArrayList<Discussion> discussions){
        this.discussions = discussions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView customerProviderProfileDiscussionCardView;
        private TextView customerProviderProfileDiscussionTitle;
        private TextView customerProviderProfileDiscussionDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            customerProviderProfileDiscussionCardView = itemView.findViewById(R.id.customerProviderProfileDiscussionCardView);
            customerProviderProfileDiscussionTitle = itemView.findViewById(R.id.customerProviderProfileDiscussionTitle);
            customerProviderProfileDiscussionDate = itemView.findViewById(R.id.customerProviderProfileDiscussionDate);

        }
    }
}
