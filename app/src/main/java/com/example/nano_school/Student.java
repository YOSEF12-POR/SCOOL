package com.example.nano_school;

import java.io.Serializable;

class Student implements Serializable {
    private int id_std;
    private String name_std;
    private int age;
    private String password;
    private double rate;
    private int level;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Student(int id_std, String name_std, int age, String password, double rate, int level) {
        this.id_std = id_std;
        this.name_std = name_std;
        this.age = age;
        this.password = password;
        this.rate = rate;
        this.level = level;
    }

    public Student(int id_std, String name_std, int age, String password) {
        this.id_std = id_std;
        this.name_std = name_std;
        this.age = age;
        this.password = password;
    }

    public Student(String name_std, int age, String password) {

        this.name_std = name_std;
        this.age = age;
        this.password = password;
    }

    public int getId_std() {
        return id_std;
    }

    public void setId_std(int id_std) {
        this.id_std = id_std;
    }

    public String getName_std() {
        return name_std;
    }

    public void setName_std(String name_std) {
        this.name_std = name_std;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
