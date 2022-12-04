package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CustomerChatWithProviderActivity extends AppCompatActivity {

    private MaterialToolbar customerChatWithProviderMaterialToolbar;
    private TextInputEditText message;
    private FloatingActionButton fab;
    private FirebaseListAdapter<Message> adapter;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_chat_with_provider);

        customerChatWithProviderMaterialToolbar = findViewById(R.id.customerChatWithProviderMaterialToolbar);
        fab = findViewById(R.id.fab);
        message = findViewById(R.id.message);

        Bundle bundle = getIntent().getExtras();
        auth = FirebaseAuth.getInstance();

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference().child("1on1Chats").push().setValue(new Message(message.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                    message.setText("");
                }
            });
            displayChatMessages();

            customerChatWithProviderMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
    }
        private void displayChatMessages () {
            ListView listOfMessages = (ListView) findViewById(R.id.listMessages);

                adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.message, FirebaseDatabase.getInstance().getReference().child("oneOnOneMessages")) {
                    @Override
                    protected void populateView(View v, Message model, int position) {
                        // Get references to the views of message.xml
                        TextView messageText = (TextView) v.findViewById(R.id.user_message);
                        TextView messageUser = (TextView) v.findViewById(R.id.user_email);
                        TextView messageTime = (TextView) v.findViewById(R.id.user_message_datetime);

                        // Set their text
                        messageText.setText(model.getMessageText());
                        messageUser.setText(model.getMessageUser());

                        // Format the date before showing it
                        messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm)", model.getMessageTime()));
                    }

                };
                listOfMessages.setAdapter(adapter);
            }
}