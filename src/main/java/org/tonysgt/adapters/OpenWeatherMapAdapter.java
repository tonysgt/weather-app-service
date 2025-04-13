package org.tonysgt.adapters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.tonysgt.clients.open_weather_map.DataClient;
import org.tonysgt.clients.open_weather_map.GeoClient;
import org.tonysgt.configuration.OpenWeatherMapConfig;
import org.tonysgt.mapper.GeoMapper;
import org.tonysgt.mapper.OneCallResponseToWeatherForecastResponseMapper;
import org.tonysgt.model.data.onecall.OneCallResponse;
import org.tonysgt.model.geo.GeoDirectResponse;
import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;

import java.util.List;

@ApplicationScoped
public class OpenWeatherMapAdapter implements WeatherAdapter{

    @Inject
    @RestClient
    GeoClient geoClient;

    @Inject
    @RestClient
    DataClient dataClient;

    @Inject
    OpenWeatherMapConfig openWeatherMapConfig;

    @Inject
    OneCallResponseToWeatherForecastResponseMapper oneCallResponseToWeatherForecastResponseMapper;

    @Override
    public List<SearchLocationResponse> searchPlaceResponse(String place) {
        List<GeoDirectResponse> geoDirectResponses = geoClient.direct(place, 5, openWeatherMapConfig.apikey());
        return geoDirectResponses.stream().map(GeoMapper::mapGeoDirectToSearchLocationResponse).toList();
    }

    @Override
    public DailyForecastResponse dailyForecast(String lat, String lon) {
        OneCallResponse oneCallResponse = dataClient.oneCall(lat, lon,
                openWeatherMapConfig.apikey(),
                openWeatherMapConfig.units().orElse(null),
                openWeatherMapConfig.lang().orElse(null),
                "minutely,hourly" //to exclude forecast by minute and by hour
        );
        return oneCallResponseToWeatherForecastResponseMapper.mapOneCallResponseToDailyForecastResponse(oneCallResponse);
    }

}
