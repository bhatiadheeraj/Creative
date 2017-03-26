package ws.wolfsoft.creative;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import customfonts.MyTextView;

public class signin extends AppCompatActivity {

    TextView create;

    Typeface fonts1;

    EditText email;
    EditText password;
    FirebaseAuth firebaseAuth;
    TextView forgotpassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Firebase.setAndroidContext(this);

        create = (TextView)findViewById(R.id.create);
        email = (EditText)findViewById(R.id.email);
        password= (EditText)findViewById(R.id.password);

        forgotpassword = (TextView)findViewById(R.id.acc);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(signin.this);
                View promptView = layoutInflater.inflate(R.layout.reset_password, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signin.this);
                alertDialogBuilder.setView(promptView);

                final EditText editText = (EditText) promptView.findViewById(R.id.recover_email);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("SEND EMAIL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String feedback_txt = editText.getText().toString();
                                try {

                                firebaseAuth.sendPasswordResetEmail(feedback_txt);

                                }catch (Exception ex){
                                    ex.printStackTrace();


                                }
                            }
                        });


                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();



            }
        });

        firebaseAuth=FirebaseAuth.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signin.this, signup.class);
                startActivity(it);

            }
        });




        fonts1 =  Typeface.createFromAsset(signin.this.getAssets(),
                "fonts/Lato-Regular.ttf");



        TextView t4 =(TextView)findViewById(R.id.create);
        t4.setTypeface(fonts1);

        TextView singin = (TextView)findViewById(R.id.signin1);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = email.getText().toString();
                String passwordtext = password.getText().toString();


                if (emailid == null) {
                    email.setError("You can not leave it blank.");

                }
                if (passwordtext == null) {
                    password.setError("You can not leave it blank . ");
                }



// Force user to fill up the form
                if (emailid.equals("") && passwordtext.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();

                    email.setError("You can not leave it blank.");
                    password.setError("You can not leave it blank.");

                } else {

                    final ProgressDialog rd = new ProgressDialog(signin.this);
                    rd.setTitle("Please Wait !");
                    rd.setMessage("We are setting everything .");
                    rd.show();


                    firebaseAuth.signInWithEmailAndPassword(emailid, passwordtext).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(getApplicationContext(), "Incorrect passoword or email !", Toast.LENGTH_LONG).show();

                            firebaseAuth.signOut();
                            finish();
                        }
                    });
                        firebaseAuth.signInWithEmailAndPassword(emailid, passwordtext).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent i = new Intent(getApplicationContext(), Nav_drawer.class);
                                startActivity(i);
                            }

                        });




//
//                    FirebaseAuth firebaseAuth1 = FirebaseAuth.getInstance();
//                  String UserID = firebaseAuth1.getCurrentUser().getUid();
//                    DatabaseReference useridref = FirebaseDatabase.getInstance().getReference("users");
//                    useridref.child(UserID).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            Map<String, Object> map = (Map<String, Object>)dataSnapshot.getValue();
//                            Share.usernames = String.valueOf(map.get("username"));
//                            Share.mobileno =String.valueOf( map.get("mob"));
//
//                            SharedPreferences db = getSharedPreferences("user_details", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = db.edit();
//                            editor.putString("username",String.valueOf(map.get("username")));
//                            editor.putString("mobileno",String.valueOf( map.get("mob")));
//                            editor.commit();
//
//                        }
//
//                        @Override
//
//                    firebaseAuth.signInWithEmailAndPassword(emailid, passwordtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(Task<AuthResult> task) {
//
//                            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(Task<AuthResult> task) {
//                                }
//                            });
//
//                        }
//                    });
//                }
//
//
//
//
//
//                    final Firebase myref = new Firebase("https://fatafatcare-fc1a9.firebaseio.com");
//                        myref.authWithPassword(emailid,passwordtext, new  Firebase.AuthResultHandler(){
//
//                            @Override
//                            public void onAuthenticated(AuthData authData) {
//                               String UserId = authData.getUid();
//                                DatabaseReference useridref = FirebaseDatabase.getInstance().getReference("users");
//
//                                useridref.child(UserId).addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
//                                        Share.usernames = String.valueOf(map.get("username"));
//                                        Share.mobileno = String.valueOf(map.get("mob"));
//
//                                        SharedPreferences db = getSharedPreferences("user_details", MODE_PRIVATE);
//                                        SharedPreferences.Editor editor = db.edit();
//                                        editor.putString("username", String.valueOf(map.get("username")));
//                                        editor.putString("mobileno", String.valueOf(map.get("mob")));
//                                        editor.commit();
//                                        Intent i = new Intent(getApplicationContext(), Nav_drawer.class);
//                                        startActivity(i);
//                                        finish();
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//
//                                    }
//                                });
//
//
//                    }
//                            @Override
//                            public void onAuthenticationError(FirebaseError firebaseError) {
//                                Toast.makeText(getApplicationContext(), "Incorrect passoword or email !", Toast.LENGTH_LONG).show();
//                                rd.dismiss();
//                            }
//                        });
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });






                        SharedPreferences db = getSharedPreferences("appintro", MODE_PRIVATE);
                        SharedPreferences.Editor editor = db.edit();
                        editor.putBoolean("key_name1", true);
                        editor.commit();


                    }
                }


        });
    }

}

