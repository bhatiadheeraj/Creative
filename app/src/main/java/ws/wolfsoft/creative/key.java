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

public class key extends AppCompatActivity {


    RelativeLayout keyincar ;
    RelativeLayout lost_key ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        keyincar = (RelativeLayout)findViewById(R.id.keyincar_rl);
        lost_key = (RelativeLayout)findViewById(R.id.lostkey_rl);

        keyincar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(key.this , location_chooser.class);
                Share.service_type = "key in car" ;
                startActivity(i);
            }
        });

        lost_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(key.this,location_chooser.class);
                Share.service_type = "lost key";
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
