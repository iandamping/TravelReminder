package com.example.jun.travelreminder.network;

import android.util.Log;

import com.example.jun.travelreminder.Databasenya.ROOM.AppExecutor;
import com.example.jun.travelreminder.Databasenya.ROOM.DatabaseNya;
import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.model.Example;
import com.example.jun.travelreminder.model.bola.Bola;
import com.example.jun.travelreminder.model.bola.Event;
import com.example.jun.travelreminder.model.news.Article;
import com.example.jun.travelreminder.model.news.NewsAll;
import com.example.jun.travelreminder.model.quotes.ExampleQuotes;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.jun.travelreminder.network.ApiClientBaru.createRequestNews;
import static com.example.jun.travelreminder.network.ApiClientBaru.createRequestQuotes;
import static com.example.jun.travelreminder.network.ApiClientBaru.createRequestWeather;

public class RequestObserver {
    public static final String REFRESHED_DATA = "REFRESH";
    public static String HAS_PULL = "NONE";
    private static DatabaseNya mdb = DatabaseNya.getsObjectClassIni(MainApplication.CONTEXT);

    public static void getQuotes(Observer observer) {
        Observable<ExampleQuotes> getQuotes = createRequestQuotes().callQuote();
        getQuotes.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

    public static void getWeather(Observer observer, String city, String api_key) {
        Observable<Example> getWeather = createRequestWeather().callWeathers(city, api_key);
        getWeather.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }


    public static void getUser(String action, String source, String api_key) {
        if (action.equals("NONE")) {

            refreshNewsData(source, api_key, refreshData());
        } else if (action.equals(REFRESHED_DATA)) {
            refreshNewsData(source, api_key, refreshData());
        }

    }

    private static void refreshNewsData(String source, String api_key, Observer observer) {
        Observable<NewsAll> getNews = createRequestNews().getNews(source, api_key);
        getNews.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
        HAS_PULL = "PULLED";
    }

    private static DisposableObserver<NewsAll> refreshData() {
        return new DisposableObserver<NewsAll>() {
            @Override
            public void onNext(NewsAll newsAll) {
                AppExecutor.getsInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mdb.dao_news().deleteAllData();
                        mdb.dao_news().insertDataNews(newsAll.getArticles());
                    }
                });
                dispose();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private static Article newsConverter(List<Article> articles) {
        Article needData = null;
        for (Article newsData : articles) {
            needData = newsData;
        }

        return needData;
    }


    public static void getBola(Observer obs){
    Observable<Event> getBola = ApiClientBaru.createBola().getBola();
    getBola.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(obs);
    }

}
