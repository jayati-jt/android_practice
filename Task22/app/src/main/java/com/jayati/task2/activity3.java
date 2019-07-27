package com.jayati.task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class activity3 extends AppCompatActivity {
    private DatabaseReference MDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);

        Button button;
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ValueEventListener UserListen = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get Post object and use the values to update the UI
                        MainActivity.User user = dataSnapshot.getValue(MainActivity.User.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("hii", "loadPost:onCancelled", databaseError.toException());
                    }
                };
                MDB.addValueEventListener(UserListen);
            }
             });
            }
    }
