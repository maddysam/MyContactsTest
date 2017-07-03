package com.test.mycontacts.rest;


import com.test.mycontacts.response.ContactsResponse;

 import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("contactlist.php")
    Call<ContactsResponse> getData(@Query("token") String token);


    }