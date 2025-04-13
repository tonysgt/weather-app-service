package org.tonysgt.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.adapters.WeatherAdapter;
import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;
import org.tonysgt.model.weather.forecast.Forecast;

import java.time.*;
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
        DailyForecastResponse dailyForecastResponse = weatherAdapter.dailyForecast(lat, lon);

        LocalDate today = LocalDate.now();
        LocalDate sixDaysAfter = today.plusDays(6);

        //filter only for the next 5 days
        List<Forecast> list = dailyForecastResponse.getForecasts().stream().filter(forecast -> {
                    LocalDate localDate = Instant.ofEpochSecond(forecast.getTimestamp())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return localDate.isBefore(sixDaysAfter) && localDate.isAfter(today);
                }
        ).toList();
        dailyForecastResponse.setForecasts(list);
        return dailyForecastResponse;
    }
}
