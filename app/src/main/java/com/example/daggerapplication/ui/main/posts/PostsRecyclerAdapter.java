package com.example.daggerapplication.ui.main.posts;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerapplication.R;
import com.example.daggerapplication.ui.main.posts.model.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.ViewHolder> {

    List<Post> postList ;

    @Inject
    public PostsRecyclerAdapter(){
        postList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.postTitleTextView.setText(post.getTitle());
        holder.postDescTextView.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView postTitleTextView , postDescTextView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitleTextView = itemView.findViewById(R.id.post_title);
            postDescTextView = itemView.findViewById(R.id.post_description);
        }
    }

    public void setPostList(List<Post> postList){
        this.postList = postList ;
        notifyDataSetChanged();
    }
}
