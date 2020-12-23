package com.simcom.kahut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public FirebaseDatabase database;
    public String kahutId;
    public boolean exists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
    }
    public void join(View view){
        setContentView(R.layout.join);

        DatabaseReference myRef = database.getReference("message");

    }
    public void getId(View view){
        EditText editText = findViewById(R.id.kahutId);
        String input=editText.getText().toString();
        System.out.println(input);
        if(idVerify(input)){
            setContentView(R.layout.activity_main);
        }
    }
    public boolean idVerify(String id){
        DatabaseReference tRef=database.getReference(id);
        tRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                   exists=true;
                }else{
                    exists=false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               exists=false;
            }
        });
        return exists;
    }
}
