package org.tonysgt.adapters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.tonysgt.clients.open_weather_map.DataClient;
import org.tonysgt.clients.open_weather_map.GeoClient;
import org.tonysgt.configuration.OpenWeatherMapConfig;
import org.tonysgt.mapper.GeoMapper;
import org.tonysgt.model.SearchLocationResponse;
import org.tonysgt.model.data.forecast.ForecastResponse;
import org.tonysgt.model.geo.GeoDirectResponse;

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

    @Override
    public List<SearchLocationResponse> searchPlaceResponse(String place) {
        List<GeoDirectResponse> geoDirectResponses = geoClient.direct(place, 5, openWeatherMapConfig.apikey());
        return geoDirectResponses.stream().map(GeoMapper::mapGeoDirectToSearchLocationResponse).toList();
    }

    @Override
    public ForecastResponse forecast(String lat, String lon) {
        return dataClient.forecast(lat, lon,
                openWeatherMapConfig.apikey(),
                openWeatherMapConfig.units().orElse(null),
                openWeatherMapConfig.lang().orElse(null));
    }

}
