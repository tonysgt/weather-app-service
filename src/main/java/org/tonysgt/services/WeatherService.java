package org.tonysgt.services;

import jakarta.ws.rs.core.Response;
import org.tonysgt.model.weather.SearchLocationResponse;

import java.util.List;

public interface WeatherService {
    List<SearchLocationResponse> searchLocationResponse(String place);

    Response forecast(String lat, String lon);
}
