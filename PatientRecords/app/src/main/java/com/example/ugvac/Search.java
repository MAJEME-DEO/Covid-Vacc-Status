package com.example.ugvac;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    public EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



    }

    public void  Search (View v){
        search = (EditText) findViewById(R.id.search);
        String key = search.getText().toString();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("patients").orderByChild("phone").equalTo(key);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    long count = ds.child("comments").getChildrenCount();
//                    Log.d("DEO", );
//                }

                if(dataSnapshot.hasChildren()){
                    Log.i("DEO", dataSnapshot.getValue().toString());

                    Toast.makeText(getApplicationContext(),"User is fine, and vaccinated",
                            Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(getApplicationContext(),"Not registered",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DEOError", databaseError.getMessage());
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);

    }
}