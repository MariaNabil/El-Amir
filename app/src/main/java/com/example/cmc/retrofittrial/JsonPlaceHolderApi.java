package com.example.cmc.retrofittrial;

import android.arch.persistence.room.Update;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("api/head")
    Call<List<Employee>> getEmployees();


    @POST("api/head")
    Call<Employee> createEmployee(@Body Employee employee);


    @FormUrlEncoded
    @POST("api/head")
    Call<Employee> createEmployee(
            @Field("Job_Description") String Job_Description,
            @Field("Working_Hours") int  Working_Hours,
            @Field("Name") String Name );


    @DELETE("api/head/{id}")
    Call<Void> deleteEmployee(@Path("id") int id);


    //Call<Post> putPost(@Path("id") int id, @Body Post post);
    @PUT("api/head/{id}")
    Call<Void> updateEmployee(
            @Path("id") int ServerID,
            @Body Employee employee);

}

