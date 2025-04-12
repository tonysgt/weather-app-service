package org.tonysgt.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.adapters.WeatherAdapter;
import org.tonysgt.model.SearchLocationResponse;
import org.tonysgt.model.data.forecast.ForecastResponse;

import java.util.List;

@ApplicationScoped
public class WeatherService {

    @Inject
    WeatherAdapter weatherAdapter;

    public List<SearchLocationResponse> searchLocationResponse(String place){
        return weatherAdapter.searchPlaceResponse(place);
    }

    public ForecastResponse forecast(String lat, String lon){
        return weatherAdapter.forecast(lat,lon);
    }

}
