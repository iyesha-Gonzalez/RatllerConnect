package com.example.rattlerconnect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.Serializable;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Post post = posts.get(position);

        holder.tvUsername.setText(post.getUser().getUsername());
        holder.tvDescription.setText(post.getDescription());
        // holder.tvNumLikes.setText(Integer.toString(post.getNumLikes()));

        holder.tvRelativeTimestamp.setText(post.getRelativeTimeAgo(post.getCreatedAt().toString()));

        if (post.isLiked()) {
            holder.ivHeart.setImageResource(R.drawable.ufi_heart_active);
        }

        holder.ivHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!post.isLiked()) {
                    post.likePost(ParseUser.getCurrentUser());
                    holder.ivHeart.setImageResource(R.drawable.ufi_heart_active);
                    //holder.tvNumLikes.setText(Integer.toString(post.getNumLikes()));


                    post.saveInBackground();

                } else {
                    post.unlikePost(ParseUser.getCurrentUser());
                    holder.ivHeart.setImageResource(R.drawable.ufi_heart);
                    holder.ivHeart.setColorFilter(R.color.orange_700);
                    //holder.tvNumLikes.setText(Integer.toString(post.getNumLikes()));


                    post.saveInBackground();
                }
            }
        });

        Glide.with(context)
                .load(post.getImage().getUrl())
                .bitmapTransform(new RoundedCornersTransformation(context, 10, 0))
                .into(holder.ivImage);

    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivProfileImage;
        public TextView tvUsername;
        public ImageView ivImage;
        public TextView tvDescription;
        public TextView tvRelativeTimestamp;
        public ImageView ivHeart;
       // public TextView tvNumLikes;
        //public ImageView ivComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = (ImageView) itemView.findViewById(R.id.profile);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvRelativeTimestamp = (TextView) itemView.findViewById(R.id.tvDate);
            ivHeart = (ImageView) itemView.findViewById(R.id.ivHeart);
            //ivComment = (ImageView) itemView.findViewById(R.id.ivComment);
            //tvNumLikes = (TextView) itemView.findViewById(R.id.tvNumLikes);

            itemView.setOnClickListener(this);
        }



    public void onClick(View v) {
        //get position of movie clicked
        int position = getAdapterPosition();
        //get movie at the position
        Post post = posts.get(position);
        //create new intent
        Intent intent = new Intent(context, PostDetailActivity.class);
        //pass movie
        intent.putExtra(Post.class.getSimpleName(), (Serializable) post);
        //show the activity
        context.startActivity(intent);
    }
}



    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
}


