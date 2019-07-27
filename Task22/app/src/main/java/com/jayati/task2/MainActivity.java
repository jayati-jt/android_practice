package com.jayati.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference MDB;

    public class User {

        public String username;
        public String reg_no;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String name, String regnum) {
            username = name;
            reg_no = regnum;
        }

    }

    private EditText mname;
    private EditText mrnum;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "Hello Javatpoint", Toast.LENGTH_SHORT).show();

        MDB = FirebaseDatabase.getInstance().getReference();
        mname=findViewById(R.id.name);
        mrnum=findViewById(R.id.regno);

        Button button;
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* // Read from the database
                MDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("name", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("name", "Failed to read value.", error.toException());
                    }
                });*/
               submitinfo();
                Intent i = new Intent(getApplicationContext(), activity2.class);
                //Bundle bundle= new Bundle();
                String rnum = mrnum.getText().toString();
                //bundle.putString("reg_no",rnum);
                i.putExtra("reg_no",rnum);

                startActivity(i);



            }

        });
    }
    private void submitinfo(){
        String user_name = mname.getText().toString();
        String user_regnum = mrnum.getText().toString();
        if (TextUtils.isEmpty(user_name)) {
            Toast.makeText(getApplicationContext(), "ENTER NAME", Toast.LENGTH_SHORT).show();
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(user_regnum)) {
            Toast.makeText(getApplicationContext(), "ENTER REGISTRATION NUMBER", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(user_name, user_regnum);
        MDB.child("users").child(user_regnum).setValue(user);




    }
}
