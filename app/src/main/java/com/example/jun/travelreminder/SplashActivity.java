package com.example.jun.travelreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jun.travelreminder.helper.Notification.ReminderUtil;
import com.example.jun.travelreminder.model.quotes.ExampleQuotes;
import com.example.jun.travelreminder.network.RequestObserver;

import io.reactivex.observers.DisposableObserver;

public class SplashActivity extends AppCompatActivity {
    TextView tvBody, tvAuthor;
    RelativeLayout rlSplash;
    Thread splashTread;
    Animation fromRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        rlSplash = findViewById(R.id.rlSplash);
        tvBody = findViewById(R.id.tvBody);
        tvAuthor = findViewById(R.id.tvAuthor);
        ReminderUtil.scheduleNotifReminder(this);
        RequestObserver.getQuotes(disposalQuotes());

        fromRv = AnimationUtils.loadAnimation(this, R.anim.translate);
        rlSplash.setAnimation(fromRv);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();
    }


    public DisposableObserver<ExampleQuotes> disposalQuotes() {
        return new DisposableObserver<ExampleQuotes>() {
            @Override
            public void onNext(ExampleQuotes quote) {
                tvAuthor.setText(quote.getQuote().getAuthor());
                tvBody.setText(quote.getQuote().getBody());
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
