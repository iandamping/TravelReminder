package com.example.jun.travelreminder.fitur.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jun.travelreminder.MainApplication;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.helper.PreferenceHelper;
import com.example.jun.travelreminder.model.Main;
import com.example.jun.travelreminder.model.Weather;
import com.example.jun.travelreminder.network.ApiKey;
import com.squareup.picasso.Picasso;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class WeatherFragment extends Fragment implements WeatherFragmentView, View.OnClickListener {
    private static final String TYPE_NEWS = "type-sync";
    private static final String KEY = ApiKey.ApiKey;
    TextInputEditText etLocation;
    TextView tvWeather, tvTemp;
    //    WebView wvLiburanKeluarga;
    ImageView ivSearch, imgWeatherIcon;
    LinearLayout linearStatus, linearEmpty;
    WeatherFragmentPresenter presenter;
    Context context;
    PreferenceHelper pref;
    private int stopper;

    public static WeatherFragment newInstance(int condition) {
        Bundle bundle = new Bundle();
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(bundle);
        bundle.putInt(TYPE_NEWS, condition);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        setRetainInstance(true);
        stopper = args.getInt(TYPE_NEWS);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
//        mDb = DatabaseNya.getsObjectClassIni(getActivity().getApplicationContext());
        pref = new PreferenceHelper(context);
        presenter = new WeatherFragmentPresenter(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onCompletedPull(MainApplication.getDisposable());
    }

    @Override
    public void onClick(View view) {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View views = inflater.inflate(R.layout.fragment_weather, container, false);
        presenter.onCreateView(views);
        return views;
    }

    @Override
    public void initView(View view) {
//        wvLiburanKeluarga = view.findViewById(R.id.wvLiburanKeluarga);
//        wvLiburanKeluarga.loadUrl("http://www.liburkeluarga.com/");
        tvWeather = (TextView) view.findViewById(R.id.tvWeather);
        tvTemp = (TextView) view.findViewById(R.id.tvTemp);
        imgWeatherIcon = (ImageView) view.findViewById(R.id.imWeatherIcon);
        etLocation = (TextInputEditText) view.findViewById(R.id.edit_location);
        ivSearch = (ImageView) view.findViewById(R.id.ivSearch);
        linearStatus = (LinearLayout) view.findViewById(R.id.linearStatus);
        linearEmpty = (LinearLayout) view.findViewById(R.id.linearEmpty);


    }

    @Override
    public void initListener() {
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etData = etLocation.getText().toString().trim();
                pref.putString("location", etData);
                presenter.getWeatherData(etData, KEY);
//                presenter.getTemperaturData(etData, KEY);
            }
        });
    }


    @Override
    public void startTask() {

    }

    @Override
    public void finishedTask() {

    }

    @Override
    public void failureTask(String message) {
        if (!message.equals(null)) {
            linearEmpty.setVisibility(View.GONE);
            linearStatus.setVisibility(View.VISIBLE);
            tvWeather.setText(message);
        } else {
            linearStatus.setVisibility(View.GONE);
            linearEmpty.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onSuccess(List<Weather> data_weather) {
        if (data_weather != null) {
            linearEmpty.setVisibility(View.GONE);
            linearStatus.setVisibility(View.VISIBLE);
            tvWeather.setText(data_weather.get(0).getMain());
            Picasso.with(context).load("http://openweathermap.org/img/w/" + data_weather.get(0).getIcon() + ".png")
                    .into(imgWeatherIcon);
        } else {
            linearStatus.setVisibility(View.GONE);
            linearEmpty.setVisibility(View.VISIBLE);
        }
        ivSearch.setVisibility(View.GONE);
        etLocation.setVisibility(View.GONE);


    }

    @Override
    public void onTempSucces(Main data_main) {
        double celciusData = presenter.tempConverter(data_main.getTemp());
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        tvTemp.setText(df.format(celciusData) + " \u2103");
    }

//    public void initViews(){
//        AppExecutor.getsInstance().getDiskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                final LiveData<List<Weather>> data = mDb.dao_cuacanya().load_all_cuaca();
//                final LiveData<Main>datas = mDb.dao_mainnya().load_all_suhu();
//                data.observe(getActivity(), new Observer<List<Weather>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Weather> weathers) {
//                        onSuccess(weathers);
//                    }
//                });
//                datas.observe(getActivity(), new Observer<Main>() {
//                    @Override
//                    public void onChanged(@Nullable Main main) {
//                        onTempSucces(main);
//                    }
//                });
//
//            }
//        });
//    }


}
