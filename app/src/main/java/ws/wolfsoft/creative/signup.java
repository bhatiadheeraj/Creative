package ws.wolfsoft.creative;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class signup extends AppCompatActivity {

    TextView signinhere;

    Typeface fonts1;

    EditText name ;
    EditText emails;
    EditText passwords;
    EditText mobileno;

    TextView singnup ;
    FirebaseAuth auth ;
    DatabaseReference df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Firebase.setAndroidContext(this);

        signinhere = (TextView)findViewById(R.id.signinhere);

        name = (EditText)findViewById(R.id.username);
        emails = (EditText)findViewById(R.id.email);
        passwords = (EditText)findViewById(R.id.password);
        mobileno = (EditText)findViewById(R.id.mobileno);



        final Firebase myref = new Firebase("https://fatafatcare-fc1a9.firebaseio.com");


        auth = FirebaseAuth.getInstance();

        singnup = (TextView)findViewById(R.id.signup1);
        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signup.this, signin.class);
                startActivity(it);
            }
        });
        fonts1 =  Typeface.createFromAsset(signup.this.getAssets(),
                "fonts/Lato-Regular.ttf");
        TextView t1 =(TextView)findViewById(R.id.signinhere);
        t1.setTypeface(fonts1);




        singnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailsingup = emails.getText().toString();
                final String passwordsingup = passwords.getText().toString();
                final String mobile = mobileno.getText().toString();

                Share.mobileno = mobile;

                String puclic = name.getText().toString();
                Share.usernames=puclic;




                if(passwordsingup.length()<5){
                    passwords.setError("Minimum 6 characters required .");
                }

                if (mobile.length()<9){
                    mobileno.setError("Incorrect Mobile Number");
                }
                if (emailsingup.equals("")&&passwordsingup.equals("")&&mobile.equals("")&&name.equals("")){
                    mobileno.setError("You can not leave this field empy");
                    passwords.setError("You can not leave this field empty");
                    name.setError("You can not leave this field empty");
                    emails.setError("You can not leave this field empty");
                }
                else{



                    FirebaseDatabase fd =FirebaseDatabase.getInstance();
                     df = fd.getReference();



                    ProgressDialog rd =new ProgressDialog(signup.this);
                    rd.setTitle("Please Wait .....");
                    rd.show();

//                    myref.createUser(emailsingup, passwordsingup, new Firebase.ValueResultHandler<Map<String, Object>>() {
//                        @Override
//                        public void onSuccess(Map<String, Object> stringObjectMap) {
//                            showAlertDialog(getApplicationContext(),"Congrats !","Now sign in to get access",true);
//                            writeNewUser(String.valueOf(stringObjectMap.get("uid")), Share.usernames, emailsingup, passwordsingup,mobile);
//                        }
//
//                        @Override
//                        public void onError(FirebaseError firebaseError) {
//                            String ac = "";
//                            String dialogmsg = firebaseError.getMessage().toString();
//                            //We are removing ':' and other waste msg ;
//                            int msgindex = dialogmsg.indexOf(":");
//                            String finalstring = dialogmsg.substring(dialogmsg.indexOf(":") + 1);
//
//                            showAlertDialog(signup.this, "Error", "" +finalstring, false);
//                        }
//                    })



                    auth.createUserWithEmailAndPassword(emailsingup,passwordsingup).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {


                            writeNewUser(auth.getCurrentUser().getUid(), Share.usernames, emailsingup, passwordsingup,mobile);
                       if (task.isSuccessful()){

                           showAlertDialog(getApplicationContext(),"Congrats !","Now sign in to get access",true);
                            }
                            else{
                                String ac = "";
                           String dialogmsg = task.getException().toString();
                           //We are removing ':' and other waste msg ;
                          int msgindex = dialogmsg.indexOf(":");
                           String finalstring = dialogmsg.substring(dialogmsg.indexOf(":") + 1);

                           showAlertDialog(signup.this, "Error", "" +finalstring, false);

                            }
                        }
                    });



                ;}
            }
        });


    }

    private void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(signup.this);


        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), signin.class);
                startActivity(i);
            }
        });
        // Setting OK Button

        // Showing Alert Message
        alertDialog.show();
    }
    private void writeNewUser(String userId, String name, String email,String pwd,String mobile) {
        User user = new User(name, email,pwd,mobile);

        df.child("users").child(userId).setValue(user);
    }

}
