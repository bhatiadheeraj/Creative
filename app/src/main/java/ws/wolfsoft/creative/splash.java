package ws.wolfsoft.creative;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    private TextView txt;
    private Animation fade1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txt = (TextView) findViewById(R.id.namaste);


        //apply animation
        fade1 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        txt.startAnimation(fade1);



        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {


                SharedPreferences db = getSharedPreferences("appintro",MODE_PRIVATE);
                boolean ab=  db.getBoolean("key_name1", false);
                if (ab==false) {

                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(getApplicationContext(), Nav_drawer.class);
                    startActivity(i);
                    finish();
                }

            }
        }.start();
    }
    }

