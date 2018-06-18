package com.stanlytango.android.myhandlerapplication;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int MAX_COUNT = 10; // кол-во итераций
    TextView textView;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            textView.setText(" "+msg.what );
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txt_view);
        textView.setText("current thread is "+Thread.currentThread().getName());
    }

    //  onClick method

    public void onClick(View view ){
        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while(count < MAX_COUNT){
                    try{
                        Thread.currentThread().sleep(1000);
                        Log.d(TAG, ""+Thread.currentThread().getName()+ count);
                        handler.sendEmptyMessage(count);
                        count++;
                    } catch ( InterruptedException e ){  e.printStackTrace();  }
                }
            }
        });
        background.setName("BackgroundThread");
        background.start();
    }
}
