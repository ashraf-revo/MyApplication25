package com.example.revo.myapplication;

/**
 * Created by kenz on 11/15/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.revo.myapplication.model.event;

public class NotificationReceiver extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        event event= (event) getIntent().getSerializableExtra("key");
        Log.d("jhgjhgjh", event.toString());

    }
}