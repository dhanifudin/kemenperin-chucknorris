package com.dhanifudin.jokes.services;

import com.dhanifudin.jokes.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChuckService {

    @GET("/jokes/random")
    Call<Joke> getRandomJoke();
}
