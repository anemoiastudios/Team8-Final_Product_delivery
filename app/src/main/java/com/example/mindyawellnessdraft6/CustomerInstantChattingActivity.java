package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerInstantChattingActivity extends AppCompatActivity {

    private FirebaseListAdapter<Message> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_instant_chatting);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText input = findViewById(R.id.message);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance().getReference().child("24-7 Chat").push().setValue(new Message(input.getText().toString(), FirebaseAuth.getInstance() .getCurrentUser().getDisplayName())  );

                // Clear the input
                input.setText("");
            }
        });
        displayChatMessages();
    }
    private void displayChatMessages() {
        ListView listOfMessages = (ListView) findViewById(R.id.listMessages);

        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.message, FirebaseDatabase.getInstance().getReference().child("24-7 Chat")) {
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