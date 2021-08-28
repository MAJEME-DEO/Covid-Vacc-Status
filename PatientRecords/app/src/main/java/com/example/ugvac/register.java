package com.example.ugvac;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class register extends AppCompatActivity {


    public EditText phone;
    public EditText fullname;
    public EditText cur_didtrict;
    public EditText occupation;
    public  EditText age;
    public Spinner vaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner spinner = (Spinner) findViewById(R.id.vaccine_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public  void  submit(View v){



        vaccine = (Spinner) findViewById(R.id.vaccine_spinner);
        phone = (EditText) findViewById(R.id.phone);
        fullname = (EditText) findViewById(R.id.fullname);
        cur_didtrict =  (EditText) findViewById(R.id.cur_didtrict);
        occupation = (EditText) findViewById(R.id.occupation);
        age = (EditText) findViewById(R.id.age);

        String selectedVaccine  =  vaccine.getSelectedItem().toString();
        String phonenum =  phone.getText().toString().trim();
        String full   = fullname.getText().toString().trim();
        String cur =  cur_didtrict.getText().toString().trim();
        String occupa  = occupation.getText().toString().trim();
        String ag =  age.getText().toString().trim();

        ArrayList <String> patient = new ArrayList<>();
        patient.add(selectedVaccine);
        patient.add(phonenum);
        patient.add(full);
        patient.add(cur);
        patient.add(occupa);
        patient.add(ag);

        Log.i("DEO", patient.toString());
        Firebasehandler s = new Firebasehandler(patient,"patients");
       if(s.savePatient()){
           Toast.makeText(getApplicationContext(),"Saved sucessfully",
                   Toast.LENGTH_SHORT).show();
       }else {
           Log.i("DEO", "failed");
       }






    }
}