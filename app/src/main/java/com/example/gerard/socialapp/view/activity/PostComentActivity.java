package com.example.gerard.socialapp.view.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.Comment;
import com.example.gerard.socialapp.model.Post;
import com.example.gerard.socialapp.view.adapter.CommentsAdapter;
import com.example.gerard.socialapp.model.Item;
import com.example.gerard.socialapp.view.adapter.PostsAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostComentActivity extends AppCompatActivity {

    FloatingActionButton btonFloat;
    private RecyclerView reciclerview;
    private CommentsAdapter commentsAdapter;
    Button btnComent;
    FirebaseUser mUser;
    DatabaseReference mReference;
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    String postKey;
    Comment comment;
    ArrayList<Comment> CommentList;



    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_coment);
        reciclerview = findViewById(R.id.id_recycleview);

        postKey = getIntent().getStringExtra("postKey");
        mReference = FirebaseDatabase.getInstance().getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference().child("posts").child("data").child(postKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);

                // rellenar los view con los datos del post
                // textview.setText(post.author)
                TextView tv1= findViewById(R.id.Tv1);
                tv1.setText(post.author);
                //
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        reciclerview.setLayoutManager( new LinearLayoutManager(this));

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Comment>()
                .setQuery(mReference.child("posts").child("comments").child(postKey), Comment.class)
                .setLifecycleOwner(this)
                .build();

        reciclerview.setAdapter(new CommentsAdapter(options, this, mUser, mReference));





        btnComent= findViewById(R.id.btnEnviar);
        // poner un listener al boton
        // cuando se haga click:

        btnComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // sacar el comment del textview
                EditText coment = findViewById(R.id.comentario);
                String comentEt = coment.getText().toString();
                comment =new Comment(comentEt,FirebaseAuth.getInstance().getCurrentUser().getUid());

                mReference.getDatabase().getReference().child("posts").child("comments").child(postKey).push().setValue(comment);
            }
        });
    }

    private ArrayList<Comment> GetCommentList(){

        ArrayList<Comment> CommentList = new ArrayList<>();

       CommentList.add(comment);


        return CommentList;
    }


}