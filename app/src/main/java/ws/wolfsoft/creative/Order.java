package ws.wolfsoft.creative;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

public class Order extends AppCompatActivity {


    ImageButton vehciletype ;
    AutoCompleteTextView make ;
    EditText model ;
    EditText year ;
    EditText COLOR ;
    EditText Registration ;

    String maketext ;
    String modeltext ;
    String yeartext ;
    String colortxt ;
    String registrationtxt;

    TextView submit;

    FirebaseAuth fd ;
    DatabaseReference df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        make = (AutoCompleteTextView)findViewById(R.id.makedit);
        model = (EditText)findViewById(R.id.modeledit);
        year = (EditText)findViewById(R.id.year);
        COLOR = (EditText)findViewById(R.id.color);
        Registration  = (EditText)findViewById(R.id.registration);
         fd =FirebaseAuth.getInstance();
        df = FirebaseDatabase.getInstance().getReference();
        vehciletype = (ImageButton)findViewById(R.id.vehicleimg);
        if(Share.twowheeler==true){
            vehciletype.setImageResource(R.drawable.bike);
        }else {
            vehciletype.setImageResource(R.drawable.fourwheeler);
        }

        String[] cars ={"Audi","Bentley","BMW","Chevrolet","Fiat","Ford","Honda","Hyundai","Jaguar","Land Rover","Mahindra","Maruti Suzuki","Mercedes Benz","Mitsubishi","Nissan","Porche","Range Rover","Renault","Rolls-Royce","Skoda","Tata Motors","Toyota","Volkswagen","Volvo"};



        ArrayAdapter ad = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_dropdown_item_1line);
        make.setAdapter(ad);


        submit = (TextView)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maketext = make.getText().toString();
                modeltext = model.getText().toString();
                yeartext = year.getText().toString();
                colortxt = COLOR.getText().toString();
                registrationtxt = Registration.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ; dd/MM");
                String currentDateandTime = sdf.format(new Date());


                ProgressDialog pd = new ProgressDialog(Order.this);
                pd.setTitle("Sending your Request !");
                pd.show();
                String key = df.push().getKey();

                try {

                    SharedPreferences db = getSharedPreferences("user_details", MODE_PRIVATE);
                    SharedPreferences.Editor editor = db.edit();

                    Share.mobileno = db.getString("mobileno", "0");
                    Log.d("mobileeeeeeeeeeeeeeee",""+Share.mobileno);
                    NewOrder(maketext, modeltext, yeartext, colortxt, registrationtxt, currentDateandTime, key, Share.address, Share.usernames, Share.mobileno, Share.latitude, Share.longitude, Share.service_type);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlertDialog(Order.this, "Error Occured",
                            "Please check your connection", false);

                }


            }


    });
    }

    private void NewOrder(String maketxt, String modeltext, String yeartext, String colortxt, String registrationtxt, String time, String key, CharSequence address, String usernames, String mobileno,Double latitude , Double longitude ,String servicetype) {
        Orders order = new Orders(maketxt, modeltext, yeartext, colortxt, registrationtxt, time, key, address, usernames, mobileno,latitude,longitude,servicetype);
  Query qf = df.child("order").orderByChild(time);

//        df.child("orders").child(usernames).setValue(order);
//        df.push().setValue(order);
        df.child("order").setValue(order).addOnSuccessListener(Order.this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                LayoutInflater layoutInflater = LayoutInflater.from(Order.this);
                View promptView = layoutInflater.inflate(R.layout.success, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Order.this);
                alertDialogBuilder.setView(promptView);


                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Order.this, Nav_drawer.class);
                                startActivity(i);
                            }
                        });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        })  ;


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
    private void showAlertDialog(Context context, String title, String message, Boolean status) {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
