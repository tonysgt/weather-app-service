package org.tonysgt.services;

import jakarta.ws.rs.core.Response;
import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;

import java.util.List;

public interface WeatherService {
    List<SearchLocationResponse> searchLocationResponse(String place);

    Response forecast(String lat, String lon);
}
