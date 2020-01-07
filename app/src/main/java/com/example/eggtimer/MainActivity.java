package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timeControl;
    TextView textView;
    int currprogress;
    MediaPlayer mediaPlayer;
    CountDownTimer myCountDownTimer;

    public void goFunc(View view ){
        Button goButton = (Button) findViewById(R.id.button2);
        Button stopButton = (Button) findViewById(R.id.button3);
       stopButton.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        Log.i("Info","GO PRESSED!!!");

        timeControl.setVisibility(View.INVISIBLE);

        myCountDownTimer=new CountDownTimer(currprogress*1000,1000){
            public void onTick(long millisecondsUntilDone){
                Log.i("Time Remaining" , String.valueOf(millisecondsUntilDone/1000));
                currprogress=(int)millisecondsUntilDone/1000;
                int minutes=currprogress/60;
                int seconds=currprogress%60;

                textView.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
            }
            public void onFinish(){
                Log.i("Finished","ON time");
                mediaPlayer.start();
                Button goButton = (Button) findViewById(R.id.button2);
                Button stopButton = (Button) findViewById(R.id.button3);
                stopButton.setVisibility(View.INVISIBLE);
                goButton.setVisibility(View.VISIBLE);

                timeControl.setVisibility(View.VISIBLE);
                currprogress=30;
                timeControl.setProgress(currprogress);
            }
        }.start();
    }

    public void stopFunc(View view){
        Button goButton = (Button) findViewById(R.id.button2);
        Button stopButton = (Button) findViewById(R.id.button3);
        stopButton.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        Log.i("Info","STOP PRESSED!!!");

        timeControl.setVisibility(View.VISIBLE);

        myCountDownTimer.cancel();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.alarm);

        timeControl =(SeekBar) findViewById(R.id.seekBar);
        textView=(TextView) findViewById(R.id.editText);
        int max=300;
        int min=1;
        currprogress=30;
        timeControl.setMax(max);
        timeControl.setMin(min);
        timeControl.setProgress(currprogress);
        int minutes=currprogress/60;
        int seconds=currprogress%60;
        textView.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
        timeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Current Value of Seekbar ",Integer.toString(progress));
                currprogress=progress;
                int minutes=currprogress/60;
                int seconds=currprogress%60;
                textView.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
