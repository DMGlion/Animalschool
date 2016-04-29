package com.example.davidmgarcia6.animalschool;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OceanView extends AppCompatActivity {

    private MediaPlayer mp;
    // The following are used for the shake detection
    // Taken from http://jasonmcreynolds.com/?p=388
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean_view);

        Button shark = (Button) findViewById(R.id.sharkbutton);
        assert shark != null;
        shark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(OceanView.this, R.raw.incorrect);
                mp.start();
            }
        });

        Button crab = (Button) findViewById(R.id.crabbutton);
        assert crab != null;
        crab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(OceanView.this, R.raw.incorrect);
                mp.start();
            }
        });

        Button dolphin =(Button) findViewById(R.id.dolphinbutton);
        assert dolphin != null;
        dolphin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(OceanView.this, R.raw.incorrect);
                mp.start();
            }
        });

        Button orc =(Button) findViewById(R.id.orcbutton);
        assert orc != null;
        orc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(OceanView.this, R.raw.winnero);
                mp.start();


                //Code for redirecting to Facts screen is:
                //http://stackoverflow.com/questions/6304035/how-to-display-an-activity-automatically-after-5-seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent mainIntent = new Intent(OceanView.this, OceanResultFactsActivity.class);
                        OceanView.this.startActivity(mainIntent);
                        OceanView.this.finish();
                    }
                }, 1000);
            }
        });




        // This needs to be inserted into the correct answer onClickListener
//        if (UserScore.getQuizScore() < 2){
//            UserScore.setQuizScore(2);
//            Toast.makeText(getApplicationContext(), "UserScore is now: " + UserScore.getQuizScore(), Toast.LENGTH_SHORT).show();
//        }

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                stopPlaying();
                mp = MediaPlayer.create(OceanView.this, R.raw.wale);
                mp.start();
                handleShakeEvent(count);
            }

            private void handleShakeEvent(int count) {

                Toast.makeText(getApplicationContext(), "Shake Event Triggered " + count + " times", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
    private void stopPlaying(){
        if(mp!=null){
            mp.stop();
            mp.reset();
            mp.release();
            mp=null;
        }
    }

}
