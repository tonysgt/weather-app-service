package org.tonysgt.clients.open_weather_map;

import com.fasterxml.jackson.databind.JsonNode;
import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.tonysgt.model.data.forecast.ForecastResponse;
import org.tonysgt.model.data.onecall.OneCallResponse;


@Path("/data")
@RegisterRestClient(configKey = "open-weather-map")
public interface DataClient {


    /**
     * @param lat required Latitude, decimal (-90; 90). If you need the geocoder to automatic convert city names and zip-codes to geo coordinates and the other way around, please use Geocoding API
     * @param lon required Longitude, decimal (-180; 180). If you need the geocoder to automatic convert city names and zip-codes to geo coordinates and the other way around, please use our Geocoding API
     * @param appID required Your unique API key
     * @param units optional	Units of measurement. standard, metric and imperial units are available. If you do not use the units parameter, standard units will be applied by default.
     * @param lang optional	You can use the lang parameter to get the output in your language.
     * @param exclude By using this parameter you can exclude some parts of the weather data from the API response. It should be a comma-delimited list (without spaces).
     *  Available values:
     *  <pre>
     *  <ul>
     *     <li>current</li>
     *     <li>minutely</li>
     *     <li>hourly</li>
     *     <li>daily</li>
     *     <li>alerts</li>
     *  </ul>
     *  </pre>
     * @return OneCallResponse
     */
    @GET
    @Path("/3.0/onecall")
    OneCallResponse oneCall(@QueryParam("lat") @NotNull String lat, @QueryParam("lon") @NotNull String lon, @QueryParam("appID") String appID, @QueryParam("units") String units,
                            @QueryParam("lang") String lang, @QueryParam("exclude") String exclude);


}
