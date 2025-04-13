package org.tonysgt.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.configuration.OpenWeatherMapConfig;
import org.tonysgt.model.data.onecall.Daily;
import org.tonysgt.model.data.onecall.OneCallResponse;
import org.tonysgt.model.weather.forecast.*;
import org.tonysgt.utils.UnitUtils;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OneCallResponseToWeatherForecastResponseMapper {

    public static final String DEFAULT = "default";

    @Inject
    OpenWeatherMapConfig openWeatherMapConfig;

    public DailyForecastResponse mapOneCallResponseToDailyForecastResponse(OneCallResponse oneCallResponse) {
        DailyForecastResponse dailyForecastResponse = new DailyForecastResponse();

        List<Daily> dailys = oneCallResponse.getDaily();
        List<Forecast> forecastList = dailys.stream().map(daily -> {

            Forecast forecast = new Forecast();
            forecast.setTimestamp(daily.getDt());
            forecast.setSunrise(daily.getSunrise());
            forecast.setSunset(daily.getSunset());
            forecast.setHumidityPerc(daily.getHumidity());

            forecast.setPressure(daily.getPressure());

            Optional.ofNullable(daily.getTemp()).ifPresent(temp -> {
                Temperature temperature = new Temperature();
                temperature.setMin(temp.getMin());
                temperature.setMax(temp.getMax());
                temperature.setMorning(temp.getMorn());
                temperature.setDay(temp.getDay());
                temperature.setEve(temp.getEve());
                temperature.setNight(temp.getNight());
                temperature.setUnits(UnitUtils.getTempUnits(openWeatherMapConfig.units().orElse(DEFAULT)));
                forecast.setTemperature(temperature);
            });

            Optional.ofNullable(daily.getFeelsLike()).ifPresent(feelsLike -> {
                PerceivedTemperature perceivedTemperature = new PerceivedTemperature();
                perceivedTemperature.setMorn(feelsLike.getMorn());
                perceivedTemperature.setDay(feelsLike.getDay());
                perceivedTemperature.setEve(feelsLike.getEve());
                perceivedTemperature.setNight(feelsLike.getNight());
                perceivedTemperature.setUnits(UnitUtils.getTempUnits(openWeatherMapConfig.units().orElse(DEFAULT)));
                forecast.setPerceivedTemperature(perceivedTemperature);
            });

            Optional.ofNullable(daily.getWeather()).ifPresent(weather -> {
                Weather weatherObject = new Weather();
                weatherObject.setId(weatherObject.getId());
                weatherObject.setMain(weatherObject.getMain());
                weatherObject.setDescription(weatherObject.getDescription());
                forecast.setWeather(weatherObject);
            });

            Wind wind = new Wind();
            wind.setUnit(UnitUtils.getSpeedUnits(openWeatherMapConfig.units().orElse(DEFAULT)));
            wind.setSpeed(daily.getWindSpeed());
            wind.setDeg(daily.getWindDeg());
            wind.setGust(daily.getWindGust());
            forecast.setWind(wind);

            forecast.setClouds(daily.getClouds());
            forecast.setPop(daily.getPop());
            forecast.setRain(daily.getRain());
            forecast.setUvi(daily.getUvi());

            return forecast;
        }).toList();
        dailyForecastResponse.setForecasts(forecastList);
        return dailyForecastResponse;
    }
}
