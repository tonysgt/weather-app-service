package org.tonysgt.adapters;

import org.tonysgt.model.SearchLocationResponse;
import org.tonysgt.model.data.forecast.ForecastResponse;

import java.util.List;

public interface WeatherAdapter {

    List<SearchLocationResponse> searchPlaceResponse(String place);

    ForecastResponse forecast(String lat, String lon);
}
