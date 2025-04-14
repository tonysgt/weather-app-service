package org.tonysgt.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tonysgt.model.weather.SearchLocationResponse;
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
    public Response forecast(@QueryParam("lat") String lat, @QueryParam("lon") String lon) {
        return weatherService.forecast(lat, lon);
    }
}
