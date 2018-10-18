package com.dhanifudin.jokes.generator;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    // Mendefinisikan konstanta base url
    private static final String BASE_URL = "https://api.chucknorris.io/";

    // Mendefiniskan builder utk obyek retrofit
    // berdasarkan base url dan gson converter
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    // Digunakan untuk intercept request/response HTTP
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    // Menghindari instansiasi class dengan keyword new
    private ServiceGenerator() {}

    // Membuat service berdasarkan tipe generic yang digunakan
    public static <Service> Service createService(Class<Service> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }
}
