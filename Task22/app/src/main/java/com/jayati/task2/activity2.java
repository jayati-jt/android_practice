package com.jayati.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity2 extends AppCompatActivity {

    private EditText memail;
    private DatabaseReference MDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        MDB = FirebaseDatabase.getInstance().getReference();
        memail=findViewById(R.id.email);
        Button next;
        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = memail.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "ENTER EMAIL", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = getIntent().getExtras();
                String rnum = bundle.getString("reg_no");
                MDB.child("users").child(rnum).child("email").setValue(email);

                Intent i = new Intent(getApplicationContext(), activity3.class);
                startActivity(i);
            }

            });
    }

}
