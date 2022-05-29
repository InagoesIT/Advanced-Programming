package com.lab11_requests.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Friend implements Serializable {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("person1")
    private Person person1;

    @JsonProperty("person2")
    private Person person2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", person1=" + person1 +
                ", person2=" + person2 +
                '}';
    }
}