package org.tonysgt.adapters;

import org.tonysgt.exceptions.WeatherAppApiException;
import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;

import java.util.List;

public interface WeatherAdapter {

    List<SearchLocationResponse> searchPlaceResponse(String place);

    DailyForecastResponse dailyForecast(String lat, String lon) throws WeatherAppApiException;
}
