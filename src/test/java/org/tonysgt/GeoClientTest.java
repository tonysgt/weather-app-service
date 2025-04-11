package org.tonysgt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tonysgt.clients.open_weather_map.DataClient;
import org.tonysgt.clients.open_weather_map.GeoClient;
import org.tonysgt.model.GeoDirectResponse;
import org.tonysgt.model.data.forecast.ForecastResponse;

import java.util.List;


@QuarkusTest
class GeoClientTest {

    @Inject
    @RestClient
    GeoClient geoClient;

    @Inject
    @RestClient
    DataClient dataClient;



    @ConfigProperty(name ="openWeatherMap.apiKey")
    String apiKey;

    @Test
    void testGeoDirectApi() {
        List<GeoDirectResponse> geoDirectResponses = geoClient.direct("London", 5, apiKey);
        Assertions.assertNotNull(geoDirectResponses);
        GeoDirectResponse firstResult = geoDirectResponses.getFirst();
        Assertions.assertEquals("London", firstResult.getName());
        Log.infof("Name: %s, lat: %s, lon: %s",firstResult.getName(),firstResult.getLat(), firstResult.getLon());
    }

    @Test
    void testDataForecastApi() {
        ForecastResponse forecast = dataClient.forecast("51.5073219", "-0.1276474", apiKey);
        Assertions.assertNotNull(forecast);
        try {
            String forecastResponseAsString = new ObjectMapper().writeValueAsString(forecast);
            Log.info(forecastResponseAsString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
