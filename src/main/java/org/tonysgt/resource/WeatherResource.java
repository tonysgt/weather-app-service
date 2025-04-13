package org.tonysgt.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.weather.forecast.DailyForecastResponse;

import java.util.List;

@Path("/weather")
public interface WeatherResource {

    @GET
    @Path("location/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<SearchLocationResponse> searchLocation(@QueryParam("location") String place);


    /**
     *
     * @param lat
     * @param lon
     * @return DailyForecastResponse if request successed
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response forecast(@QueryParam("lat") String lat, @QueryParam("lon") String lon);
}
