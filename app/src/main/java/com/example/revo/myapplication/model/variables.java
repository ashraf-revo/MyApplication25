package com.example.revo.myapplication.model;

/**
 * Created by revo on 18/11/15.
 */
public class variables {
    private String queuename;
    private String virtual_host;
    private String host;
    private String username;
    private String password;
    private Cloudinary cloudinary;

    public String getQueuename() {
        return queuename;
    }

    public variables setQueuename(String queuename) {
        this.queuename = queuename;
        return this;
    }

    public String getVirtual_host() {
        return virtual_host;
    }

    public variables setVirtual_host(String virtual_host) {
        this.virtual_host = virtual_host;
        return this;
    }

    public String getHost() {
        return host;
    }

    public variables setHost(String host) {
        this.host = host;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public variables setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public variables setPassword(String password) {
        this.password = password;
        return this;
    }

    public Cloudinary getCloudinary() {
        return cloudinary;
    }

    public variables setCloudinary(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
        return this;
    }
}
