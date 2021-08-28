package com.example.ugvac;

import android.content.Intent;
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

public class FirrstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firrst_screen);








        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("patients").orderByChild("type").equalTo("astra zeneca");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    long count = ds.child("comments").getChildrenCount();
                    Log.d("TAG", "Count: " + count);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Bug", databaseError.getMessage());
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);




    }

    public void Searchclick (View v){

        Intent intent = new Intent(FirrstScreen.this, Search.class);
        startActivity(intent);
    }

    public void addPatient (View v) {

        Intent intent = new Intent(FirrstScreen.this, register.class);
        startActivity(intent);

    }
}