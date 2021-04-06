package com.example.rattlerconnect.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.rattlerconnect.LoginActivity;
import com.example.rattlerconnect.R;
import com.example.rattlerconnect.Post;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends HomeFragment {

    private final String TAG = "ProfileFragment";
    private Button btnLogout;
    private TextView tvUser;
    private ImageView ivProfile;
    private RecyclerView rvPosts;
    private com.example.rattlerconnect.EndlessRecyclerViewScrollListener scrollListener;
    private SwipeRefreshLayout swipeRefreshLayout;
    protected List< Post> mPosts;

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile_tab, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_profile, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        btnLogout = (Button) view.findViewById(R.id.btnLogout);
        tvUser = (TextView) view.findViewById(R.id.tvName);
        ivProfile = (ImageView) view.findViewById(R.id.ivProfile);

        tvUser.setText(ParseUser.getCurrentUser().getUsername());

         btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutAction();
            }
        });

         /*

        loadTopPosts();
        //find RecyclerView
        rvPosts = (RecyclerView) view.findViewById(R.id.rvUserPosts);
        //init arraylist
        ArrayList<Object> posts = new ArrayList<>();
        //construct adapter
        postAdapter = new ProfilePostAdapter(posts);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3);
        rvPosts.setLayoutManager(linearLayoutManager);
        scrollListener = new com.example.rattlerconnect.EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadTopPosts();
            }
        };


        rvPosts.addOnScrollListener(scrollListener);
        //set the adapter
        rvPosts.setAdapter(postAdapter);

         */
        //set swipe refresh layout

        /*@Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_settings:  {
                    // navigate to settings screen
                    return true;
                }
                case R.id.action_done: {
                    // save profile changes
                    return true;
                }
                default:
                    return super.onOptionsItemSelected(item);
            }

        }

         */



//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadTopPosts();
//            }
//        });

//        // Configure the refreshing colors
//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
    }

    public void onLogoutAction() {
        ParseUser.logOut();
        final Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    /*public void loadTopPosts() {

        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, com.parse.ParseException e) {
                if(e == null) {
                    postAdapter.clear();
                    for(int i = 0; i < objects.size(); i++) {
                        mPosts.add(objects.get(i));
                        postAdapter.notifyItemInserted(mPosts.size() - 1);
//                        Log.i("HomeFragment", "Post " + i + " " + objects.get(i).getDescription());
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to query posts", Toast.LENGTH_SHORT).show();
                }
//                swipeRefreshLayout.setRefreshing(false);
            }

        });
    }

     */
}
