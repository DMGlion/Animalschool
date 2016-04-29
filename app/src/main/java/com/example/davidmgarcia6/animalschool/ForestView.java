package com.example.davidmgarcia6.animalschool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForestView extends AppCompatActivity {

    private MediaPlayer mp;
    // The following are used for the shake detection
    // Taken from http://jasonmcreynolds.com/?p=388
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest_view);

        final TextView textview= (TextView) findViewById(R.id.updateresponse);

        // Auto plays the Audio file
        stopPlaying();
        mp = MediaPlayer.create(ForestView.this, R.raw.monkey);
        mp.start();

        Button koala = (Button) findViewById(R.id.koalabutton);
        assert koala != null;
        koala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(ForestView.this, R.raw.incorrect);
                textview.setText("Try again");
//                reference http://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
                textview.setBackgroundColor(Color.argb(255,221,44,0));
                mp.start();
            }
        });


        Button monkey = (Button) findViewById(R.id.monkeybutton);
        assert monkey != null;
        monkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(ForestView.this, R.raw.winnero);
                textview.setText("Correct");
//                reference http://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
                textview.setBackgroundColor(Color.argb(255,100,221,23));
                mp.start();

                //Code for redirecting to Facts screen is:
                //http://stackoverflow.com/questions/6304035/how-to-display-an-activity-automatically-after-5-seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent mainIntent = new Intent(ForestView.this, ForestResultFactsActivity.class);
                        ForestView.this.startActivity(mainIntent);
                        ForestView.this.finish();
                    }
                }, 1000);

            }
        });

        Button bear = (Button) findViewById(R.id.bearbutton);
        assert bear != null;
        bear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(ForestView.this, R.raw.incorrect);
                textview.setText("Try again");
//                reference http://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
                textview.setBackgroundColor(Color.argb(255,221,44,0));
                mp.start();
            }
        });

        Button bat =(Button) findViewById(R.id.batbutton);
        assert bat != null;
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                mp = MediaPlayer.create(ForestView.this, R.raw.incorrect);
                textview.setText("Try again");
//                reference http://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
                textview.setBackgroundColor(Color.argb(255,221,44,0));
                mp.start();
            }
        });



        // This needs to be inserted into the correct answer onClickListener
//        if (UserScore.getQuizScore() < 3){
//            UserScore.setQuizScore(3);
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
                mp = MediaPlayer.create(ForestView.this, R.raw.monkey);
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

