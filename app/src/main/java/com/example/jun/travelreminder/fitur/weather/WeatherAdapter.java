package com.example.jun.travelreminder.fitur.weather;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.base.BaseListAdapter;
import com.example.jun.travelreminder.base.BaseViewHolder;
import com.example.jun.travelreminder.model.Weather;
import com.squareup.picasso.Picasso;

public class WeatherAdapter extends BaseListAdapter<Weather, WeatherAdapter.ViewHolder> {
    public WeatherAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_weather;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, viewType), onItemClickListener);
    }

    public class ViewHolder extends BaseViewHolder<Weather> {
        TextView tvCelcius, tvCloudy;
        ImageView imWeatherIcon;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
//            tvCelcius = (TextView) itemView.findViewById(R.id.tvCelcius);
//            imWeatherIcon = (ImageView) itemView.findViewById(R.id.imWeatherIcon);
        }

        @Override
        public void bind(Weather item) {
            tvCelcius.setText(item.getMain());

            Picasso.with(context).load("http://openweathermap.org/img/w/" + item.getIcon() + ".png")
                    .into(imWeatherIcon);

        }
    }
}
