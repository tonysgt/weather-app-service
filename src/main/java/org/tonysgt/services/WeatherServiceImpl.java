package org.tonysgt.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.adapters.WeatherAdapter;
import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;

import java.util.List;

@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    @Inject
    WeatherAdapter weatherAdapter;

    @Override
    public List<SearchLocationResponse> searchLocationResponse(String place){
        return weatherAdapter.searchPlaceResponse(place);
    }

    @Override
    public DailyForecastResponse forecast(String lat, String lon){
        return weatherAdapter.dailyForecast(lat,lon);
    }

}
