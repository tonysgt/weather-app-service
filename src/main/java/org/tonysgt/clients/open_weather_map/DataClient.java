package org.tonysgt.clients.open_weather_map;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/data")
@RegisterRestClient(configKey = "open-weather-map")
public interface DataClient {

    @GET
    @Path("/3.0/onecall")
    JsonNode oneCall(@QueryParam("lat") String lat, @QueryParam("lon") String lon, @QueryParam("appID") String appID);

}
