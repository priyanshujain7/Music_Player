package com.example.ritikjain.mp3sirtrial;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PlayerWorking extends AppCompatActivity {
    String  song;
    MediaPlayer mediaPlayer;
    Button play,forward,backward;
    SeekBar seekBar;
    Runnable runnable;
    Handler handler;
    ImageView icon;
    TextView textSong,timer;
    //String prevSong="";
    long millis;
    String[] songName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_working);
        song=getIntent().getStringExtra("Songname");
        play=(Button)(findViewById(R.id.play));
        icon=(ImageView)(findViewById(R.id.image));
        forward=(Button)(findViewById(R.id.forward));
        backward=(Button)(findViewById(R.id.backward));
        seekBar=(SeekBar)(findViewById(R.id.seekBar));
        textSong=(TextView)(findViewById(R.id.textSong));
        timer=(TextView)(findViewById(R.id.timer));
        songName=song.split("Songs/");
        textSong.setText(songName[1]);
        handler=new Handler();
        try {
            mediaPlayer=new MediaPlayer();
//            mediaPlayer.setDataSource(prevSong);
//            mediaPlayer.stop();
           // mediaPlayer.reset();
          //  Toast.makeText(getApplicationContext(),mediaPlayer.isPlaying()+"",Toast.LENGTH_LONG).show();
            //if(mediaPlayer.isPlaying()&&mediaPlayer!=null)
            {
            // mediaPlayer.stop();
             // mediaPlayer.release();
          }
            mediaPlayer.setDataSource(song);//path of the song
      //      prevSong=song;
            mediaPlayer.prepare();
            //Toast.makeText(getApplicationContext(),mediaPlayer.getDuration()+"",Toast.LENGTH_LONG).show();
            millis=mediaPlayer.getDuration();
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration()); //set length of seekbar
            changeSeekBar();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));//to convert milliseconds into h:m:s format
        Toast.makeText(getApplicationContext(),hms,Toast.LENGTH_LONG).show();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { // i is progress here
                if(b)
                {
                    mediaPlayer.seekTo(i);
                    //timer.setText(i+"");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    play.setText(">");
                }
                else
                {
                    mediaPlayer.start();
                    play.setText("||");
                }
                changeSeekBar(); //to move the seekbar
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
            }
        });

        // path="/storage/emulated/0/Songs/02 Taang Uthake - Housefull 3 (Mika) 190Kbps.mp3";
       // Toast.makeText(getApplicationContext(),path,Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(),song,Toast.LENGTH_LONG).show();
        //mediaPlayer=MediaPlayer.create(PlayerWorking.this,SongId);

    }

    private void changeSeekBar() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying())
        {
            runnable= new Runnable() {
                @Override
                public void run() {
                    changeSeekBar();
                }
            };
            handler.postDelayed(runnable,1000);
            //When we call run(); the first thing it's going to do
            //is tell your handler to run the very same Runnable after 1000 milliseconds,
            //and then to call changeSeekBar(). What this will actually result in is your changeSeekBar() method being called twice:
            //once right away, and a second time once the Handler is done waiting 1000 milliseconds
        }
    }

    @Override
    public void onBackPressed() {
      //  mediaPlayer.stop();
        //seekBar.setProgress(M);
        mediaPlayer.stop();
        Intent intent=new Intent(PlayerWorking.this,MainActivity.class);
        startActivity(intent);

        //super.onBackPressed();
    }
}
