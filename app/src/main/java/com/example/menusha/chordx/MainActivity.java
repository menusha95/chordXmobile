package com.example.menusha.chordx;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnRecord;
    private TextView screenMsg;
    private MediaRecorder mediaRec;
    private String songFile = null;
    private static final String LOG_TAG= "Record_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecord = (Button)findViewById(R.id.btnRecord);
        screenMsg= (TextView)findViewById(R.id.textView);
        songFile= Environment.getExternalStorageDirectory().getAbsolutePath();
        songFile += "/sample.mp3";

        btnRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    recordBegin();
                    screenMsg.setText("Recording on progress..");
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    stopRecording();
                    screenMsg.setText("Recording done, File saved!");
                }
                return true;
            }
        });
    }
    private void recordBegin() {
        mediaRec = new MediaRecorder();
        mediaRec.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRec.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRec.setOutputFile(songFile);
        mediaRec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRec.prepare();
        } catch (IOException e) {
        }
        mediaRec.start();
    }
    private void stopRecording() {
        mediaRec.stop();
        mediaRec.release();
        mediaRec = null;
    }
}
