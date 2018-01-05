package com.smartdo.scc.mabang.backend.bean;

public class User {
    private int id;
    private String name;
    private String dept;
    private String phone;
    private String website;

    public User(int id, String name, String dept, String phone, String website) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.phone = phone;
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
