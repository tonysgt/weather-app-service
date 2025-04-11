package org.tonysgt;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/weather")
public class WeatherResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }


    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String forecast(@QueryParam("city") String city) {
        return "Hello from Quarkus REST";
    }
}
