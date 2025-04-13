package org.tonysgt.services;

import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;

import java.util.List;

public interface WeatherService {
    List<SearchLocationResponse> searchLocationResponse(String place);

    DailyForecastResponse forecast(String lat, String lon);
}
