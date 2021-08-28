package com.example.ugvac;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Firebasehandler {

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> data =  null;


    public Firebasehandler(ArrayList r, String ref){
        database = FirebaseDatabase.getInstance();
        myRef =  database.getReference(ref);
        data =  r;


    }
    public void checkUser (){
        ArrayList<String> user =  new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Get map of users in datasnapshot
                        Log.i("DEO", dataSnapshot.getValue().toString());
                        String usr = dataSnapshot.child("username").getValue(String.class);
                        String psr =  dataSnapshot.child("password").getValue(String.class);
                        Log.i("DEO", usr);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });




    }
    public Boolean savePatient (){


        Log.i("DEO", data.toString());
        Patientdata p = new Patientdata(data.get(0),data.get(1),data.get(2),data.get(3),data.get(4),data.get(5));

        myRef.push().setValue(p);
        Log.i("DEO", "saved");
        return  true;
    }

    public ArrayList getData (String innerRef){

      ArrayList<String> info =  new ArrayList<>();




      return  info;
    }

    @IgnoreExtraProperties
    public class User {

        public String username;
        public String email;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email) {

            this.username = username;
            this.email = email;
        }



    }
    public class Patientdata {
        public String vaccine;
        public String phone;
        public String fullname;
        public String cur_didtrict;
        public String occupation;
        public  String age;

        public  Patientdata (String vac, String phone2, String fullname2, String cur_didtrict2,String occupation2,String age2){

             vaccine = vac;
             phone =  phone2;
             fullname = fullname2;
             cur_didtrict = cur_didtrict2;
             occupation = occupation2;
             age =  age2;
        }

    }

}
