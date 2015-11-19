package com.example.revo.myapplication.Service;

import android.os.AsyncTask;

import com.example.revo.myapplication.RevoApplication;
import com.example.revo.myapplication.model.Person;
import com.example.revo.myapplication.model.variables;

import java.util.concurrent.ExecutionException;

/**
 * Created by revo on 19/11/15.
 */
public class MainService {
   public Person LoginAsync(final RevoApplication application, final String email, final String password) {
        try {
            return new AsyncTask<Void, Void, Person>() {
                @Override
                protected Person doInBackground(Void... params) {
                    return application.Login(email, password);
                }
            }.execute().get();
        } catch (InterruptedException | ExecutionException ignored) {
            return null;
        }
    }

  public   variables GetAsyncVariables(final RevoApplication application) {
        try {
            return new AsyncTask<Void, Void, variables>() {
                @Override
                protected variables doInBackground(Void... params) {
                    return application.variables();
                }
            }.execute().get();

        } catch (InterruptedException | ExecutionException ignored) {
            return null;
        }
    }

}
