package com.example.jun.travelreminder;

import com.example.jun.travelreminder.model.news.Article;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * NewsAll local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void savingData() {
        List<Article> list_data = new ArrayList<>();
        Observable.fromArray(list_data).subscribeOn(Schedulers.newThread())
                .subscribe(results ->MainApplication.getDatabase().dao_news().insertDataNews(results) );
    }


}