package com.example.appquiz.classes;

public class Lesson {
    String name;
    int picture;

    public Lesson(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }
    public Lesson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
