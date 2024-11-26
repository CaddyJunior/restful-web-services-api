package com.learning.webservices.restful_web_services.versioning.model;

public class Name {

    private String name;
    private String lastname;

    public Name(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
