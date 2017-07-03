package com.test.mycontacts.model;

import com.google.gson.annotations.SerializedName;


public class ContactsModel {



int id;
    @SerializedName("uid")
    private String uid;

    @SerializedName("name")
    private String name;

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    private int isDeleted;
    public ContactsModel(){

    }

    // Empty constructor
    public ContactsModel(int id) {
        this.id = id;
    }
    // Empty constructor
    public ContactsModel(String uid) {
        this.uid = uid;
    }
    public ContactsModel(String name, String uid){
        this.name = name;
        this.uid = uid;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
