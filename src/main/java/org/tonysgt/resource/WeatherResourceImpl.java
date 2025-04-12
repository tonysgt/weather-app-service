package org.tonysgt.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tonysgt.model.SearchLocationResponse;
import org.tonysgt.model.data.forecast.ForecastResponse;
import org.tonysgt.services.WeatherService;

import java.util.List;


@ApplicationScoped
public class WeatherResourceImpl implements WeatherResource {

    @Inject
    WeatherService weatherService;

    @Override
    public List<SearchLocationResponse> searchLocation(@QueryParam("location") String place) {
        return weatherService.searchLocationResponse(place);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public ForecastResponse forecast(@QueryParam("lat") String lat, @QueryParam("lon") String lon) {
        return weatherService.forecast(lat, lon);
    }
}
