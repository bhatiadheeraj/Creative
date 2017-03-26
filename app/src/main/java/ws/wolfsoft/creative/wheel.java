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

public class wheel extends AppCompatActivity {


    RelativeLayout wheel_repair ;
    RelativeLayout spare_wheel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        wheel_repair = (RelativeLayout)findViewById(R.id.wheelrepair_or_patch_rl);
        spare_wheel = (RelativeLayout)findViewById(R.id.sparewheel_rl);


        wheel_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(wheel.this , location_chooser.class);
                Share.service_type = "wheel repair or patch";
                startActivity(i);
            }
        });

        spare_wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(wheel.this , location_chooser.class);
                Share.service_type = "Spare Wheel";
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
