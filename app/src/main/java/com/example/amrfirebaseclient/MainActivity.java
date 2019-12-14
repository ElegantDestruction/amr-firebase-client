package com.example.amrfirebaseclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //////////////////////////////////////////////////////////////////
        // Begin Retrieval Code
        //////////////////////////////////////////////////////////////////

        //Declare TextView Objects
        final TextView datetime_obj = (TextView) findViewById(R.id.datetime_value);
        final TextView data_obj_1 = (TextView) findViewById(R.id.data_value_1);
        final TextView data_obj_2 = (TextView) findViewById(R.id.data_value_2);
        final TextView data_obj_3 = (TextView) findViewById(R.id.data_value_3);
        final TextView data_obj_4 = (TextView) findViewById(R.id.data_value_4);
        final TextView data_obj_5 = (TextView) findViewById(R.id.data_value_5);
        final TextView data_obj_6 = (TextView) findViewById(R.id.data_value_6);
        final TextView data_obj_7 = (TextView) findViewById(R.id.data_value_7);

        //Find the button and prepare it for actions
        Button ubutton_object = findViewById(R.id.update_button);
        ubutton_object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a little pop-up to indicate the user pressed the button
                Toast.makeText(getApplicationContext(), "Updating Data...", Toast.LENGTH_SHORT)
                        .show();

                //Attempt to retrieve data from the firebase servers
                try {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference database_reference = database.getReference();
                    database_reference.child("orientation sensor").addValueEventListener(new ValueEventListener() {

                        //Method is invoked in the event that the database values change
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String dd_1 = dataSnapshot.child("data").child("Accelerometer Calibration").getValue(String.class);
                            String dd_2 = dataSnapshot.child("data").child("Gyroscope Calibration").getValue(String.class);
                            String dd_3 = dataSnapshot.child("data").child("Heading").getValue(String.class);
                            String dd_4 = dataSnapshot.child("data").child("Magnetometer Calibration").getValue(String.class);
                            String dd_5 = dataSnapshot.child("data").child("Pitch").getValue(String.class);
                            String dd_6 = dataSnapshot.child("data").child("Roll").getValue(String.class);
                            String dd_7 = dataSnapshot.child("data").child("System Calibration").getValue(String.class);
                            String last_update = dataSnapshot.child("data").child("last_update").getValue(String.class);

                            //Now to change TextViews
                            datetime_obj.setText(last_update);
                            data_obj_1.setText(dd_1);
                            data_obj_2.setText(dd_2);
                            data_obj_3.setText(dd_3);
                            data_obj_4.setText(dd_4);
                            data_obj_5.setText(dd_5);
                            data_obj_6.setText(dd_6);
                            data_obj_7.setText(dd_7);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error when connecting to Firebase", Toast.LENGTH_LONG);
                }

            }
        });
    }
}
