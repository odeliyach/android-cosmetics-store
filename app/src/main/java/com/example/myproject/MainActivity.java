package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private TextView mBatteryLevelText;
    private ProgressBar mBatteryLevelProgress;
    private BroadcastReceiver mReceiver;
    ProgressBar splashProgress;
    int SPLASH_TIME = 3000; //This is 3 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBatteryLevelText = (TextView) findViewById(R.id.textView);
        mBatteryLevelProgress = (ProgressBar) findViewById(R.id.progressBar);

        mReceiver = new BatteryBroadcastReceiver();

        //This is additional feature, used to run a progress bar
        splashProgress = findViewById(R.id.splashProgress);
        playProgress();
        //Code to start timer and take action after the timer ends
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(MainActivity.this, RegLogChoice.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page
                finish();

            }
        }, SPLASH_TIME);
    }
    //Method to run progress bar for 5 seconds
    private void playProgress()
    {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(600)
                .start();
    }
    @Override
    protected void onStart()
    {
        BatteryBroadcastReceiver mReceiver = new BatteryBroadcastReceiver();
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            if(level<20)
            {
                FancyToast.makeText(getBaseContext(),"Battery is low",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

            }
            mBatteryLevelText.setText(getString(R.string.battery_level) + "" + level);
            mBatteryLevelProgress.setProgress(level);
        }
    }
}