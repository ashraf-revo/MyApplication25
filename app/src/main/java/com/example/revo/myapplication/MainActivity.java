package com.example.revo.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.revo.myapplication.Service.MainService;
import com.example.revo.myapplication.model.Person;
import com.example.revo.myapplication.model.variables;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    MainService mainService = new MainService();
    private RevoApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (RevoApplication) getApplication();
        Person person = mainService.LoginAsync(application, "ashraf.abdelrasool@yahoo.com", "revo");
        if (person != null) {
            variables variables = mainService.GetAsyncVariables(application);
            if (variables != null) {
                application.initRabbit(variables, getApplicationContext());
            }
        }
    }
}

