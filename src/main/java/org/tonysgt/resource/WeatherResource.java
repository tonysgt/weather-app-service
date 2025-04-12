package org.tonysgt.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.tonysgt.model.SearchLocationResponse;
import org.tonysgt.model.data.forecast.ForecastResponse;

import java.util.List;

@Path("/weather")
public interface WeatherResource {

    @GET
    @Path("location/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<SearchLocationResponse> searchLocation(@QueryParam("location") String place);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ForecastResponse forecast(@QueryParam("lat") String lat, @QueryParam("lon") String lon);
}
