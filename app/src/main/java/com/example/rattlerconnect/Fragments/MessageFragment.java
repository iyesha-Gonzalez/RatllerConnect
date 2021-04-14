package com.example.rattlerconnect.Fragments;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rattlerconnect.ChatAdapter;
import com.example.rattlerconnect.Message;
import com.example.rattlerconnect.R;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MessageFragment extends Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
            // Defines the xml file for the fragment
            return inflater.inflate(R.layout.activity_chat, parent, false);
        }

        static final String USER_ID_KEY = "userId";
        static final String BODY_KEY = "body";

        EditText etMessage;
        ImageButton btSend;

        static final String TAG = MessageFragment.class.getSimpleName();

        RecyclerView rvChat;
        ArrayList<Message> mMessages;
        ChatAdapter mAdapter;
        // Keep track of initial load to scroll to the bottom of the ListView
        boolean mFirstLoad;

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            etMessage = (EditText) getView().findViewById(R.id.etMessage);
            btSend = (ImageButton) getView().findViewById(R.id.btSend);
            rvChat = (RecyclerView) getView().findViewById(R.id.rvChat);
            mMessages = new ArrayList<>();
            mFirstLoad = true;
            //final String userId = ParseUser.getCurrentUser().getUsername();
            final String userId = ParseUser.getCurrentUser().getUsername();
            //final String userId = ParseUser.getCurrentUser().getObjectId();
            mAdapter = new ChatAdapter(getActivity(), userId, mMessages);
            rvChat.setAdapter(mAdapter);

            // associate the LayoutManager with the RecylcerView
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setReverseLayout(true);
            rvChat.setLayoutManager(linearLayoutManager);
            refreshMessages();
            // When send button is clicked, create message object on Parse
            btSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = etMessage.getText().toString();
                    //ParseObject message = ParseObject.create("Message");
                    //message.put(Message.USER_ID_KEY, userId);
                    //message.put(Message.BODY_KEY, data);
                    // Using new `Message` Parse-backed model now
                    Message message = new Message();
                    message.setBody(data);
                    message.setUserId(ParseUser.getCurrentUser().getUsername());
                    //message.setUserId(ParseUser.getCurrentUser().getObjectId());
                    message.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(getActivity(), "Successfully created message on Parse",
                                    Toast.LENGTH_SHORT).show();
                            refreshMessages();
                        }
                    });
                    etMessage.setText(null);
                }
            });
        }

        static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;

        // Query messages from Parse so we can load them into the chat adapter
        void refreshMessages() {
            // Construct query to execute
            ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
            // Configure limit and sort order
            query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);

            // get the latest 50 messages, order will show up newest to oldest of this group
            query.orderByDescending("createdAt");
            // Execute query to fetch all messages from Parse asynchronously
            // This is equivalent to a SELECT query with SQL
            query.findInBackground(new FindCallback<Message>() {
                public void done(List<Message> messages, ParseException e) {
                    if (e == null) {
                        mMessages.clear();
                        mMessages.addAll(messages);
                        mAdapter.notifyDataSetChanged(); // update adapter
                        // Scroll to the bottom of the list on initial load
                        if (mFirstLoad) {
                            rvChat.scrollToPosition(0);
                            mFirstLoad = false;
                        }
                    } else {
                        Log.e("message", "Error Loading Messages" + e);
                    }
                }
            });
        }
}