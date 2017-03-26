package ws.wolfsoft.creative;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class vehicletype extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicletype);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vehicle Type");
        RelativeLayout bikerl = (RelativeLayout)findViewById(R.id.twowheeler);
        RelativeLayout carrl = (RelativeLayout)findViewById(R.id.four_wheeler);


        bikerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share.twowheeler=true;
                Share.car=false;
                Intent i = new Intent(vehicletype.this,Order.class);
                startActivity(i);

            }
        });

        carrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share.car=true;
                Share.twowheeler=false;
                Intent i = new Intent(vehicletype.this,Order.class);
                startActivity(i);
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
}
