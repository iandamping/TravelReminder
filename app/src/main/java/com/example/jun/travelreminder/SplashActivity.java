package com.example.jun.travelreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jun.travelreminder.helper.Notification.ReminderUtil;
import com.example.jun.travelreminder.model.bola.Bola;
import com.example.jun.travelreminder.model.bola.Event;
import com.example.jun.travelreminder.model.quotes.ExampleQuotes;
import com.example.jun.travelreminder.network.RequestObserver;

import io.reactivex.observers.DisposableObserver;

public class SplashActivity extends AppCompatActivity {
    TextView tvBody, tvAuthor;
    RelativeLayout rlSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        rlSplash = findViewById(R.id.rlSplash);
        tvBody = findViewById(R.id.tvBody);
        tvAuthor = findViewById(R.id.tvAuthor);
        ReminderUtil.scheduleNotifReminder(this);
        RequestObserver.getQuotes(disposalQuotes());
        testingData();

        rlSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    public void testingData() {
        RequestObserver.getBola(disposableObserver());
    }

    public DisposableObserver<Event> disposableObserver() {
        return new DisposableObserver<Event>() {
            @Override
            public void onNext(Event bola) {
                tvBody.setText(bola.getStrEvent());
                tvAuthor.setText(bola.getStrHomeTeam());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public DisposableObserver<ExampleQuotes> disposalQuotes() {
        return new DisposableObserver<ExampleQuotes>() {
            @Override
            public void onNext(ExampleQuotes quote) {
//                tvAuthor.setText(quote.getQuote().getAuthor());
//                tvBody.setText(quote.getQuote().getBody());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposalQuotes() != null && !disposalQuotes().isDisposed()) {
            disposalQuotes().dispose();
        }
    }
}
