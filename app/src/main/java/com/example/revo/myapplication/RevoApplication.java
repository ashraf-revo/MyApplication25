package com.example.revo.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.Base64;

import com.example.revo.myapplication.Service.Revorabbitmq;
import com.example.revo.myapplication.model.Person;
import com.example.revo.myapplication.model.variables;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by revo on 15/11/15.
 */

public class RevoApplication extends Application {
    Revorabbitmq revorabbitmq;
    HttpEntity request;
    Person person = null;
    variables variables = null;
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void initRabbit(variables variables, Context context) {
        revorabbitmq = new Revorabbitmq();
        revorabbitmq.init(variables, context,person);
    }

    public Person Login(String email, String password) {
        try {
            String plainCreds = email + ":" + password;
            String base64Creds = Base64.encodeToString(plainCreds.getBytes(), Base64.DEFAULT);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity<String> request = new HttpEntity<String>(headers);
            this.request = request;
            ResponseEntity<Person> response = restTemplate.exchange("https://hivon.herokuapp.com/home/currentlogin", HttpMethod.GET, request, Person.class);
            new RestTemplate().exchange("https://hivon.herokuapp.com/home/currentlogin", HttpMethod.GET, request, Person.class);
            this.person = response.getBody();
        } catch (Exception ignored) {
        }
        return this.person;
    }

    public variables variables() {
        try {
            ResponseEntity<variables> exchange = restTemplate.exchange("https://hivon.herokuapp.com/home/variables", HttpMethod.GET, request, variables.class);
            this.variables = exchange.getBody();
        } catch (Exception ignored) {
        }
        return variables;
    }
}