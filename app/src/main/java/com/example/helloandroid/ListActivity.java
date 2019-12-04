package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "FireLog";
    private RecyclerView mMainList;
    private FirebaseFirestore mFirestore;
    private List<Users> usersList ;
    private UserListAapter userListAapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mMainList =(RecyclerView) findViewById(R.id.main_list);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(userListAapter);
        mFirestore = FirebaseFirestore.getInstance();

        usersList = new ArrayList<>();
        userListAapter = new UserListAapter(usersList);

        mFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Log.d(TAG,"Error : "+e.getMessage());
                }
            for(DocumentChange doc :documentSnapshots.getDocumentChanges()){
                 if(doc.getType()== DocumentChange.Type.ADDED){


                     Users users = doc.getDocument().toObject(Users.class);
                     usersList.add(users);
                     userListAapter.notifyDataSetChanged();
                 }
            }
            }
        });




    }
}
