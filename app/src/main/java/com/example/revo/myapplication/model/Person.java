package com.example.revo.myapplication.model;

/**
 * Created by revo on 18/11/15.
 */
public class Person {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private String moreInfo;
    private Location addresses;
    private Location location;
    private String role;
    private String queueName;

    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Person setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Person setImage(String image) {
        this.image = image;
        return this;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public Person setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
        return this;
    }

    public Location getAddresses() {
        return addresses;
    }

    public Person setAddresses(Location addresses) {
        this.addresses = addresses;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Person setLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Person setRole(String role) {
        this.role = role;
        return this;
    }

    public String getQueueName() {
        return queueName;
    }

    public Person setQueueName(String queueName) {
        this.queueName = queueName;
        return this;
    }
}
