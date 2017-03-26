package ws.wolfsoft.creative;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Nav_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth fa;
    FirebaseDatabase fd;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Book Service");
        setSupportActionBar(toolbar);


        fa = FirebaseAuth.getInstance();

        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference();


        ProgressDialog pd = new ProgressDialog(Nav_drawer.this);
        pd.setMessage("Getting Your info !");
        pd.show();

        try {
            FirebaseUser fu = fa.getCurrentUser();
            String UID = fu.getUid();


                Log.d("User Id",UID);

                dr.child("users").child(UID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        try {

                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                            Log.e("Values",""+map.values());

                            SharedPreferences db = getSharedPreferences("user_details", MODE_PRIVATE);
                            SharedPreferences.Editor editor = db.edit();
                            editor.putString("username",String.valueOf(map.get("username")));
                            editor.putString("mobileno",String.valueOf( map.get("mob")));
                            editor.commit();

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        }catch (Exception ex){
            ex.printStackTrace();

        }

pd.dismiss();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.usernamersss);


        SharedPreferences db = getSharedPreferences("user_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = db.edit();

        Share.mobileno = db.getString("mobileno", "0");
        Share.usernames = db.getString("username", "");

        txtProfileName.setText(Share.usernames);


        RelativeLayout tyre = (RelativeLayout) findViewById(R.id.tyrerl);
        tyre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Nav_drawer.this, wheel.class);
                startActivity(i);
            }
        });

        RelativeLayout fuelproblems = (RelativeLayout) findViewById(R.id.fuelrl);
        fuelproblems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Nav_drawer.this, fuel.class);
                startActivity(i);
            }
        });

        RelativeLayout batteryprob = (RelativeLayout) findViewById(R.id.batteryrl);
        batteryprob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Nav_drawer.this, location_chooser.class);
                Share.service_type = "battery problems";
                startActivity(i);
            }
        });


        RelativeLayout tow = (RelativeLayout) findViewById(R.id.towrl);
        tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Nav_drawer.this, location_chooser.class);
                Share.service_type = "need a tow ";
                startActivity(i);
            }
        });

        RelativeLayout accidentlayout = (RelativeLayout) findViewById(R.id.acci_rl);
        accidentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), location_chooser.class);
                Share.service_type = "accident";
                startActivity(i);
            }
        });


        final RelativeLayout others = (RelativeLayout) findViewById(R.id.other);

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Other.class);
                startActivity(i);
            }
        });


//        final RelativeLayout keys = (RelativeLayout)findViewById(R.id.);
//        keys.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),key.class);
//                startActivity(i);
//            }
//        });

        final RelativeLayout fuel = (RelativeLayout) findViewById(R.id.fuelrl);
        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ws.wolfsoft.creative.fuel.class);
                startActivity(i);
            }
        });


        final RelativeLayout wheel = (RelativeLayout) findViewById(R.id.tyrerl);
        wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ws.wolfsoft.creative.wheel.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {


            // Handle the camera action
        } else if (id == R.id.nav_slideshow) {
            SharedPreferences db = getSharedPreferences("appintro", MODE_PRIVATE);
            SharedPreferences.Editor editor = db.edit();
            editor.putBoolean("key_name1", false);
            editor.commit();
            FirebaseAuth.getInstance().signOut();

            SharedPreferences db2 = getSharedPreferences("user_details", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = db.edit();
            editor.putString("username", "");
            editor.putString("mobileno","");
            editor.commit();

            Intent i = new Intent(Nav_drawer.this, MainActivity.class);
            i.putExtra("home",true);
            startActivity(i);


        } else if (id == R.id.nav_manage) {


            LayoutInflater layoutInflater = LayoutInflater.from(Nav_drawer.this);
            View promptView = layoutInflater.inflate(R.layout.feedback, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Nav_drawer.this);
            alertDialogBuilder.setView(promptView);

            final EditText editText = (EditText) promptView.findViewById(R.id.fd_tv);
            // setup a dialog window
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String feedback_txt = editText.getText().toString();
                            try {


                                dr.child("feedback").child(Share.usernames).setValue(feedback_txt);
                            }catch (Exception ex){
                                ex.printStackTrace();


                            }
                        }
                    });


            // create an alert dialog
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();
        }


//            LayoutInflater layoutInflater = LayoutInflater.from(Nav_drawer.this);
//            View promptView = layoutInflater.inflate(R.layout.feedback, null);
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Nav_drawer.this);
//            alertDialogBuilder.setView(promptView);
//
//
//            // setup a dialog window
//            alertDialogBuilder.setCancelable(false)
//                    .setPositiveButton("SEND FEEDBACK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            TextView fd_tv = (TextView)findViewById(R.id.fd_tv);
//                            String feedback_txt = fd_tv.getText().toString();
//                            try {
//
//
////                                dr.child("feedback").child(Share.usernames).setValue(feedback_txt);
//                            }catch (Exception ex){
//                                ex.printStackTrace();
//
//
//                            }
//                        }
//                    });
//
//            // create an alert dialog
//            AlertDialog alert = alertDialogBuilder.create();
//            alert.show();
//

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
