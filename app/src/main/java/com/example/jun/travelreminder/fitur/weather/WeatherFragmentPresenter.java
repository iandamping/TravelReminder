package com.example.jun.travelreminder.fitur.weather;

import android.content.Context;
import android.view.View;

import com.example.jun.travelreminder.base.BaseFragmentPresenter;
import com.example.jun.travelreminder.base.DisposingObserver;
import com.example.jun.travelreminder.model.Example;
import com.example.jun.travelreminder.network.RequestObserver;

import io.reactivex.disposables.Disposable;

public class WeatherFragmentPresenter extends DisposingObserver<Example> implements BaseFragmentPresenter {
    private WeatherFragmentView mView;
    private Context context;
    private Disposable disposable;

    public WeatherFragmentPresenter(WeatherFragmentView mView) {
        this.mView = mView;
    }


    public void getWeatherData(final String city, String api_key) {
        RequestObserver.getWeather(this, city, api_key);

    }

    public double tempConverter(double kelvin) {
        double celcius = kelvin - 273.15;
        return celcius;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
    }

    @Override
    public void onCreateView(View view) {
        mView.initView(view);
        mView.initListener();
    }

    @Override
    public void onCompletedPull(Disposable d) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        disposable = d;
    }

    @Override
    public void onNext(Example next) {
        super.onNext(next);
        mView.onSuccess(next.getWeather());
        mView.onTempSucces(next.getMain());
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mView.failureTask(e.getMessage());
        mView.finishedTask();
    }

//    public DisposableObserver<NewsAll> getDisposable() {
//        return new DisposableObserver<NewsAll>() {
//            @Override
//            public void onNext(NewsAll example) {
//                mView.onSuccess(example.getWeather());
//                mView.onTempSucces(example.getMain());
//                mView.finishedTask();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mView.failureTask(e.getMessage());
//                mView.finishedTask();
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//    }

}


//    public void initService() {
//        this.apiService = ApiClient.get();
//
//    }
//    public void getTemperaturData(String city, String api_key) {
//
//        initService();
//        Call<NewsAll> call = apiService.callWeather(city, api_key);
//        call.enqueue(new Callback<NewsAll>() {
//            @Override
//            public void onResponse(Call<NewsAll> call, Response<NewsAll> response) {
//                if (response.code() == 200) {
//                    mView.onTempSucces(response.body().getMain());
//                    mView.onSuccess(response.body().getWeather());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsAll> call, Throwable t) {
//                mView.failureTask(t.getMessage());
//                mView.finishedTask();
//            }
//        });
//    }
