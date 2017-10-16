package com.itsmee.retrofit;

import com.itsmee.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Connect {

    @GET("/users")
    Call<List<User>> getUsers();

}
