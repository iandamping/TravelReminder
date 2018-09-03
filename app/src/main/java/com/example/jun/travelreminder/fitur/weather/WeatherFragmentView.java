package com.example.jun.travelreminder.fitur.weather;

import com.example.jun.travelreminder.base.BaseFragmentView;
import com.example.jun.travelreminder.model.Main;
import com.example.jun.travelreminder.model.Weather;

import java.util.List;

public interface WeatherFragmentView extends BaseFragmentView {
    void onSuccess(List<Weather> data_weather);

    void onTempSucces(Main data_main);
}
