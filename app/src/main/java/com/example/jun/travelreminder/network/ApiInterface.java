package com.example.jun.travelreminder.network;


import com.example.jun.travelreminder.model.news.NewsAll;
import com.example.jun.travelreminder.model.quotes.ExampleQuotes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//    @GET("data/2.5/weather")
//    Observable<Example> callWeathers(@Query("q") String city, @Query("appid") String ApiId);

    @GET("qotd")
    Observable<ExampleQuotes> callQuote();

    @GET("/v2/top-headlines")
    Observable<NewsAll> getNews(@Query("sources") String source, @Query("apiKey") String apiKey);

//    @GET(ApiConfig.BLOG_POST_URL)
//    Observable<MainModelBlogspot> getUrlPost(@Query("key") String key);


}
