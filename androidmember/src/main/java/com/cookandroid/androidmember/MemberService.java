package com.cookandroid.androidmember;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MemberService {

    @GET("list")
    Call<List<Member>> findAll();

    @POST("insert")
    Call<Member> save(@Body Member memberDto);

    @PUT("update/{id}")
    Call<Member> update(@Path("id") Long id, @Body Member memberDto);

    @DELETE("delete/{id}")
    Call<Void> deleteById(@Path("id") Long id);
}
