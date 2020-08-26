package com.example.nano_school;

class Subject {
    private int id_sub;
    private String name_sub;
    private int number_std;
    private String name_doc;

    public Subject(int id_sub, String name_sub, int number_std, String name_doc) {
        this.id_sub = id_sub;
        this.name_sub = name_sub;
        this.number_std = number_std;
        this.name_doc = name_doc;
    }

    public Subject(String name_sub, int number_std) {
        this.name_sub = name_sub;
        this.number_std = number_std;
    }

    public Subject(int id_sub, String name_sub, int number_std) {
        this.id_sub = id_sub;
        this.name_sub = name_sub;
        this.number_std = number_std;
    }

    public int getId_sub() {
        return id_sub;
    }

    public void setId_sub(int id_sub) {
        this.id_sub = id_sub;
    }

    public String getName_doc() {
        return name_doc;
    }

    public void setName_doc(String name_doc) {
        this.name_doc = name_doc;
    }

    public String getName_sub() {
        return name_sub;
    }

    public void setName_sub(String name_sub) {
        this.name_sub = name_sub;
    }

    public int getNumber_std() {
        return number_std;
    }

    public void setNumber_std(int number_std) {
        this.number_std = number_std;
    }
}
