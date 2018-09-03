package com.example.jun.travelreminder.network;


import com.example.jun.travelreminder.model.Example;
import com.example.jun.travelreminder.model.blogspot.MainModelBlogspot;
import com.example.jun.travelreminder.model.bola.Event;
import com.example.jun.travelreminder.model.news.NewsAll;
import com.example.jun.travelreminder.model.quotes.ExampleQuotes;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("data/2.5/weather")
    Observable<Example> callWeathers(@Query("q") String city, @Query("appid") String ApiId);

    @GET("qotd")
    Observable<ExampleQuotes> callQuote();

    @GET("data/2.5/weather")
    Call<Example> callWeather(@Query("q") String city, @Query("appid") String ApiId);

    @GET("/v2/top-headlines")
    Observable<NewsAll> getNews(@Query("sources") String source, @Query("apiKey") String apiKey);

    @GET("api/v1/json/1/eventspastleague.php?id=4328")
    Observable<Event> getBola();

    @GET(ApiConfig.BLOG_POST_URL)
    Observable<MainModelBlogspot> getUrlPost(@Query("key") String key);
}
