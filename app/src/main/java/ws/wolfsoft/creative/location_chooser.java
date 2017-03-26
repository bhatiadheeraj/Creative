package ws.wolfsoft.creative;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

public class location_chooser extends AppCompatActivity {

    Boolean isInternetPresent = false;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    TextView gps;
    TextView manual;

    // Connection detector class
    ConnectionDetector cde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_chooser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






        manual = (TextView)findViewById(R.id.manual);

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(location_chooser.this);
                View promptView = layoutInflater.inflate(R.layout.input, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(location_chooser.this);
                alertDialogBuilder.setView(promptView);

                final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Share.address = editText.getText().toString();
                                Intent i = new Intent(getApplicationContext(),vehicletype.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        TextView gpts = (TextView)findViewById(R.id.gps);

        gpts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cde = new ConnectionDetector(getApplicationContext());
                isInternetPresent = cde.isConnectingToInternet();
                if (isInternetPresent) {


                    ProgressDialog pd = new ProgressDialog(location_chooser.this);
                    pd.setMessage("Let us setup the connection ...");
                    pd.show();

                    Intent intent =
                            null;
                    try {
                        PlacePicker.IntentBuilder intentBuilder =
                                new PlacePicker.IntentBuilder();
                        intent = intentBuilder.build(location_chooser.this);
                        // Start the intent by requesting a result,
                        // identified by a request code.
                        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }



                    // Internet Connection is Present
                    // make HTTP requests
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(getApplicationContext(), "No Internet Connection",
                            "You don't have internet connection.", false);

                }

            }
        });

    }

    @Override
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {


                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("" + place.getAddress());
                Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
                LatLng lng = place.getLatLng();
                Share.latitude = lng.latitude;
                Share.longitude = lng.longitude;
                Share.address = place.getAddress();

                Intent i = new Intent(getApplicationContext(),vehicletype.class);
                startActivity(i);

            }
        }


    }
}