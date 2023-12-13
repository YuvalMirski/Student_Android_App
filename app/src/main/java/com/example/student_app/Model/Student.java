package com.example.student_app.Model;

public class Student {


    String name, address, id, mobile;
    boolean isChecked;

    public Student() {
        this.isChecked = false;

    }

    public Student(String name, String address, String id, String mobile, boolean isChecked) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.mobile = mobile;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
