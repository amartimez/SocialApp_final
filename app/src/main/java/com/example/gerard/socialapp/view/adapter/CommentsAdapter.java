package com.example.gerard.socialapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerard.socialapp.GlideApp;
import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.Comment;
import com.example.gerard.socialapp.model.Post;
import com.example.gerard.socialapp.view.activity.PostComentActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class CommentsAdapter extends FirebaseRecyclerAdapter<Comment,CommentsAdapter.CommentsViewHolder > {
    List<Comment> comentarios;
    Context context;
    FirebaseUser mUser;
    DatabaseReference mReference;



    public CommentsAdapter (@NonNull FirebaseRecyclerOptions<Comment> options, Context context, FirebaseUser firebaseUser, DatabaseReference databaseReference){

        super(options);
        this.context = context;
        this.mUser = firebaseUser;
        this.mReference = databaseReference;

    }


    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new CommentsViewHolder(inflater.inflate(R.layout.layout_itemlista, viewGroup, false));
    }




    @Override
    protected void onBindViewHolder(@NonNull CommentsViewHolder holder, int position, @NonNull Comment comment) {
        final String postKey = getRef(position).getKey();

        holder.uidFire.setText(comment.getUidFire());
        holder.comentario.setText(comment.getComment());


    }




    public class CommentsViewHolder extends RecyclerView.ViewHolder{
        TextView uidFire;
        TextView comentario;


        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            uidFire= itemView.findViewById(R.id.autor_cardview);
            comentario = itemView.findViewById(R.id.comentario_cardview);

        }
    }
}