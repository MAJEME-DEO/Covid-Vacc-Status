package com.example.ugvac;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button login =  null;
    EditText username = null;
    EditText password =  null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonClick(View view) {
//        Intent intent = new Intent(MainActivity.this, FirrstScreen.class);
//        startActivity(intent);


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        String usr = username.getText().toString().trim();
        String psr = password.getText().toString().trim();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Get map of users in datasnapshot
                        Log.i("DEO", dataSnapshot.getValue().toString());
                        String user =(String) dataSnapshot.child("email").getValue();
                        String pass = (String) dataSnapshot.child("username").getValue();
                        Log.i("DEO", usr);
                        if(usr.equals(user) && psr.equals(pass)){
                            Intent intent = new Intent(MainActivity.this, FirrstScreen.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"Incorrect passoed and/or password",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });



    }
}