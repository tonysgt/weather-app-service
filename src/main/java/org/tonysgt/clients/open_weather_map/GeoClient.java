package org.tonysgt.clients.open_weather_map;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.tonysgt.model.GeoDirectResponse;

import java.util.List;

@Path("/geo")
@RegisterRestClient(configKey = "open-weather-map")
public interface GeoClient {

    @GET
    @Path("/1.0/direct")
    List<GeoDirectResponse> direct(@QueryParam("q") String q, @QueryParam("limit") Integer lon, @QueryParam("appID") String appID);

}
