package aacci.sourabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private Button mFirebasebutton;
    private DatabaseReference mDatabase;
    private EditText mPassword;
    private EditText mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebasebutton = (Button) findViewById(R.id.login_button);
        mPassword = (EditText) findViewById(R.id.pass_Text);
        mId = (EditText) findViewById(R.id.id_text);

        mFirebasebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Logging In...",Toast.LENGTH_SHORT).show();

                final String mID = mId.getText().toString();
                final String mPass = mPassword.getText().toString();

                mDatabase = FirebaseDatabase.getInstance().getReference().child("LOGIN");

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                            if(mID.equals(childDataSnapshot.getKey().toString())){
                                Log.v("USER ID",""+ childDataSnapshot.getKey());        //displays the key for the node
                                Log.v("USER PASS",""+ childDataSnapshot.getValue());   //gives the value for given keyname
                                if(mPass.equals(childDataSnapshot.getValue())){
                                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"LOGIN FAILED: INVALID ID OR PASSWORD",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        mId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mId.setText("");
            }
        });


    }
}
