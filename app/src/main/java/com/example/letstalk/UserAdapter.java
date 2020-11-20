package com.example.letstalk;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ListAdapter;
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

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context context;
    private final List<user> users;

    private FirebaseUser firebaseUser;

    public UserAdapter(Context context, List<user> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            final user User = users.get(position);

            holder.Add_Friend.setVisibility(View.VISIBLE);


            holder.username.setText(User.getUsername());

       // Glide.with(context).load(User.getImageUrl()).into(holder.profile_image);
        isFriend(User.getId(), holder.Add_Friend);

        if (User.getId().equals(firebaseUser.getUid())){
            holder.Add_Friend.setVisibility(View.GONE);
        }

        //sets on clicklistners on items(profiles)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = context.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid", User.getId());
                editor.apply();

                ///opens new fragment

                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

            }
        });
        holder.Add_Friend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.Add_Friend.getText().toString().equals("Add Friend")){
                            FirebaseDatabase.getInstance().getReference().child("Add_Friend")
                                    .child(firebaseUser.getUid()).child("Friends").child(User.getId()).setValue(true);

                            FirebaseDatabase.getInstance().getReference().child("Add_Friend")
                                    .child(User.getId()).child("Friends").child(firebaseUser.getUid()).setValue(true);

                        } else{
                            FirebaseDatabase.getInstance().getReference().child("Add_Friend")
                                    .child(firebaseUser.getUid()).child("Friends").child(User.getId()).removeValue();

                            FirebaseDatabase.getInstance().getReference().child("Add_Friend")
                                    .child(User.getId()).child("Friends").child(firebaseUser.getUid()).removeValue();
                        }
                    }
                }
        );

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public CircleImageView profile_image;
        public Button Add_Friend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.Username);
            profile_image = itemView.findViewById(R.id.profile_image);
            Add_Friend = itemView.findViewById(R.id.Add_Friend);
        }
    }

    private void isFriend(final String usrid, final Button button){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Add_Friend").child(firebaseUser.getUid()).child("Friends");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(usrid).exists()){
                    button.setText("Friends");
                }else{
                    button.setText("Add Friend");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
