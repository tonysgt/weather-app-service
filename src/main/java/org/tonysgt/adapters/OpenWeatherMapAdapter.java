package org.tonysgt.adapters;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.tonysgt.clients.open_weather_map.DataClient;
import org.tonysgt.clients.open_weather_map.GeoClient;
import org.tonysgt.configuration.OpenWeatherMapConfig;
import org.tonysgt.exceptions.WeatherAppApiException;
import org.tonysgt.mapper.GeoMapper;
import org.tonysgt.mapper.OneCallResponseToWeatherForecastResponseMapper;
import org.tonysgt.model.data.onecall.OneCallResponse;
import org.tonysgt.model.geo.GeoDirectResponse;
import org.tonysgt.model.open_weather_map.ErrorResponse;
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
    public DailyForecastResponse dailyForecast(String lat, String lon) throws WeatherAppApiException {
        try {
            OneCallResponse oneCallResponse = dataClient.oneCall(lat, lon,
                    openWeatherMapConfig.apikey(),
                    openWeatherMapConfig.units().orElse(null),
                    openWeatherMapConfig.lang().orElse(null),
                    "minutely,hourly" //to exclude forecast by minute and by hour
            );
            return oneCallResponseToWeatherForecastResponseMapper.mapOneCallResponseToDailyForecastResponse(oneCallResponse);
        }
        catch (WebApplicationException e){
            Response response = e.getResponse();
            Log.errorf("Status; %s, Message: %s",response.getStatus(), e.getMessage());

            ErrorResponse entity = (ErrorResponse) response.getEntity();

            throw new WeatherAppApiException(e.getMessage(),response.getStatus(),entity.getMessage(), entity.getParameters());
        }
    }

}
