package com.example.revo.myapplication.model;

import java.io.Serializable;

/**
 * Created by revo on 17/11/15.
 */
public class event implements Serializable {
    String name;
    String email;

    public String getName() {
        return name;
    }

    public event setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public event setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return name + "    " + email;
    }
}
