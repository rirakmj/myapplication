package com.cookandroid.recyclerview;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2Client {
    private static Retrofit2Client instance;
    private PersonService personService;

    public Retrofit2Client() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.104.41:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        personService = retrofit.create(PersonService.class);
    }

    public static Retrofit2Client getInstance() {
        if(instance == null) {
            instance = new Retrofit2Client();
        }
        return instance;
    }
    public PersonService getPersonService() {
        return personService;
    }

}
