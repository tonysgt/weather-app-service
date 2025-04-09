package org.tonysgt;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tonysgt.clients.open_weather_map.GeoClient;
import org.tonysgt.model.GeoDirectResponse;

import java.util.List;


@QuarkusTest
class GeoClientTest {

    @Inject
    @RestClient
    GeoClient geoClient;

    @ConfigProperty(name ="openWeatherMap.apiKey")
    String apiKey;

    @Test
    void testGeoDirectApi() {
        List<GeoDirectResponse> geoDirectResponses = geoClient.direct("London", 5, apiKey);
        Assertions.assertNotNull(geoDirectResponses);
        Assertions.assertEquals("London", geoDirectResponses.getFirst().getName());
    }
}
