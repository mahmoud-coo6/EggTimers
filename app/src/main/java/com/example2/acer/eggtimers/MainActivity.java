package com.example2.acer.eggtimers;

        import android.media.MediaPlayer;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.SeekBar;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Boolean conABoolean=false;
    CountDownTimer countDownTimer;
    Button ControlerButtom;
    ImageView photo1,photo2;

    public void resetTimer(){

        textView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        ControlerButtom.setText("Go");
        seekBar.setEnabled(true);
        conABoolean=false;
        photo2.animate().alpha(0f).setDuration(1000);
        photo1.animate().alpha(1f).setDuration(1000);
    }
    public  void updateTimer(int leftSecond){
        int minte=(int)leftSecond/60;
        int second=leftSecond-minte*60;
        String secString=Integer.toString(second);

        if(second<=9){
            secString= "0"+secString;
        }
        textView.setText(Integer.toString(minte)+":"+Integer.toString(second));
    }
    public void ControlTimer(View view){
        if (conABoolean ==false) {
            conABoolean = true;
            seekBar.setEnabled(false);
            ControlerButtom.setText("Stop");
            // Log.i("Bottom pressed","pressed");
            countDownTimer= new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    textView.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    Log.i("finish", "Done!");
                    photo2.animate().alpha(1f).setDuration(1000);
                    photo1.animate().alpha(0f).setDuration(1000);
                }
            }.start();
        }else{
          resetTimer();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         seekBar=(SeekBar) findViewById(R.id.seekBar);
         textView=(TextView)findViewById(R.id.textView) ;
         ControlerButtom=(Button)findViewById(R.id.button);
         photo1=(ImageView) findViewById(R.id.imageView2);
         photo2=(ImageView) findViewById(R.id.imageView);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
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
