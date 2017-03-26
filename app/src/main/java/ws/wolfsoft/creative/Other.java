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

public class Other extends AppCompatActivity {



    RelativeLayout notstarting ;
    RelativeLayout brakeproblems ;
    RelativeLayout clutch ;
    RelativeLayout steering;
    RelativeLayout lights ;
    RelativeLayout kEey ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notstarting = (RelativeLayout)findViewById(R.id.not_starting_rl);
        brakeproblems = (RelativeLayout)findViewById(R.id.brake_disc_rl);
        clutch = (RelativeLayout)findViewById(R.id.clutch_rl);
        steering = (RelativeLayout)findViewById(R.id.steeringrl);
        lights = (RelativeLayout)findViewById(R.id.lights_rl);
        kEey = (RelativeLayout)findViewById(R.id.keyincar_rl);


        kEey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Other.this,key.class);
                startActivity(i);
            }
        });


        notstarting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Other.this , location_chooser.class);
                Share.service_type = "vehicle not starting";
                startActivity(i);
            }
        });

        brakeproblems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Other.this , location_chooser.class);
                Share.service_type = "brake problems";
                startActivity(i);
            }
        });

        clutch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Other.this , location_chooser.class);
                Share.service_type = "clutch ";
                startActivity(i);
            }
        });

        steering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Other.this , location_chooser.class);
                Share.service_type = "Steering problems";
                startActivity(i);
            }
        });

        lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Other.this , location_chooser.class);
                Share.service_type = "lights problems";
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
