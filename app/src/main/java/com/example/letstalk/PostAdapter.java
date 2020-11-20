package com.example.letstalk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    List<post> Post;

    FirebaseUser firebaseUser;

    public PostAdapter(Context context, List<post> post) {
        this.context = context;
        this.Post = post;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.post_here, parent, false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        post mpost = Post.get(position);

        holder.status.setText(mpost.getStatus());
        System.out.println(mpost.getStatus());


        author(holder.profile_image, holder.username, holder.postauthor, mpost.getPostauthor());



    }

    @Override
    public int getItemCount() {
        return Post.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView profile_image, like;
        public TextView status, likes, postauthor, username;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            like = itemView.findViewById(R.id.like);
            status = itemView.findViewById(R.id.status);
            likes = itemView.findViewById(R.id.likes);
            postauthor = itemView.findViewById(R.id.postauthor);
            username = itemView.findViewById(R.id.username);
        }
    }
    private void author(ImageView profile_image, TextView username, TextView postauthor, String id){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user muser = snapshot.getValue(user.class);
                Glide.with(context).load(muser.getImageUrl()).into(profile_image);
                username.setText(muser.getUsername());
                postauthor.setText(muser.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
