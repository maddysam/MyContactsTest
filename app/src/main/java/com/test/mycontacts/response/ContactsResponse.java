package com.test.mycontacts.response;

import com.google.gson.annotations.SerializedName;
import com.test.mycontacts.model.ContactsModel;

import java.util.List;

public class ContactsResponse {

    @SerializedName("status")
    private String sucess;
    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private List<ContactsModel> result;


    public String getSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ContactsModel> getResult() {
        return result;
    }

    public void setResult(List<ContactsModel> result) {
        this.result = result;
    }
}
