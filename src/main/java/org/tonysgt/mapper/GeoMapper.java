package org.tonysgt.mapper;

import org.tonysgt.model.weather.SearchLocationResponse;
import org.tonysgt.model.geo.GeoDirectResponse;

public class GeoMapper {

    public static SearchLocationResponse mapGeoDirectToSearchLocationResponse(GeoDirectResponse geoDirectResponse){
        SearchLocationResponse searchLocationResponse = new SearchLocationResponse();
        searchLocationResponse.setName(geoDirectResponse.getName());
        searchLocationResponse.setLatitude(geoDirectResponse.getLat());
        searchLocationResponse.setLongitude(geoDirectResponse.getLon());
        return searchLocationResponse;
    }
}
