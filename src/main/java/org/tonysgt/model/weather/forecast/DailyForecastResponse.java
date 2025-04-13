package org.tonysgt.model.weather.forecast;

import java.util.List;

public class DailyForecastResponse {

    private List<Forecast> forecasts;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
