package org.tonysgt.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.tonysgt.adapters.WeatherAdapter;
import org.tonysgt.exceptions.WeatherAppApiException;
import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;
import org.tonysgt.model.weather.forecast.Forecast;

import java.time.*;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    @Inject
    WeatherAdapter weatherAdapter;

    @Override
    public List<SearchLocationResponse> searchLocationResponse(String place){
        return weatherAdapter.searchPlaceResponse(place);
    }

    @Override
    public Response forecast(String lat, String lon){
        Response dailyForecastResponse = null;
        try {
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
            return Response.ok(dailyForecastResponse).build();

        } catch (WeatherAppApiException e) {

            String message = Optional.ofNullable(e.getParameters()).map(parameters ->
            {
                String join = String.join(", ", parameters);
                return e.getMessage() + " Parameters: " + join;
            }).orElse(e.getMessage());

            return Response.status(e.getStatus()).entity(message).build();
        }


    }
}
