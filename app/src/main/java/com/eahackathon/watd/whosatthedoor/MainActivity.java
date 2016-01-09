package com.eahackathon.watd.whosatthedoor;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.eahackathon.watd.whosatthedoor.helpers.GcmRegistrationHelper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GcmRegistrationHelper.getInstance().setActivity(this);

        mStartBtn = (Button)findViewById(R.id.btn_start);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GcmRegistrationHelper.getInstance().register();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });


    }
}
