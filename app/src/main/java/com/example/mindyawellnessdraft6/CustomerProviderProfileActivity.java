package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class CustomerProviderProfileActivity extends AppCompatActivity {

    private MaterialToolbar customerProviderProfileMaterialToolbar;

    private ImageButton customerProviderProfileBookmarkImageButton;
    private ImageButton customerProviderProfileAppointmentImageButton;
    private ImageButton customerProviderProfileMessageImageButton;

    private RecyclerView customerProviderProfileDiscussionsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_provider_profile);

        customerProviderProfileMaterialToolbar = findViewById(R.id.customerProviderProfileMaterialToolbar);

        customerProviderProfileMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerProviderProfileBookmarkImageButton = findViewById(R.id.customerProviderProfileBookmarkImageButton);
        customerProviderProfileAppointmentImageButton = findViewById(R.id.customerProviderProfileAppointmentImageButton);
        customerProviderProfileMessageImageButton = findViewById(R.id.customerProviderProfileMessageImageButton);

        customerProviderProfileBookmarkImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomerProviderProfileActivity.this, "You saved this provider", Toast.LENGTH_SHORT).show();

                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_bookmark_24, null);

                customerProviderProfileBookmarkImageButton.setImageDrawable(d);

            }
        });

        customerProviderProfileAppointmentImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerProviderProfileActivity.this, CustomerSetAppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        customerProviderProfileMessageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerProviderProfileActivity.this, CustomerChatWithProviderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


        customerProviderProfileDiscussionsRecyclerView = findViewById(R.id.customerProviderProfileDiscussionsRecyclerView);

        ArrayList<Discussion> discussions = new ArrayList<>();

        discussions.add(new Discussion("Alcoholics Anonymous", "10/4/22"));
        discussions.add(new Discussion("Help with Mental", "10/2/22"));
        discussions.add(new Discussion("Help with Sadness :(", "10/10/22"));

        CustomerProviderProfileDiscussionListRecyclerViewAdapter adapter = new CustomerProviderProfileDiscussionListRecyclerViewAdapter(CustomerProviderProfileActivity.this);
        adapter.setDiscussions(discussions);

        customerProviderProfileDiscussionsRecyclerView.setAdapter(adapter);
        customerProviderProfileDiscussionsRecyclerView.setLayoutManager(new LinearLayoutManager(CustomerProviderProfileActivity.this, LinearLayoutManager.HORIZONTAL, false));

    }
}