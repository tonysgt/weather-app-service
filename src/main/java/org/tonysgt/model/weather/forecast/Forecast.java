package org.tonysgt.model.weather.forecast;


public class Forecast {

    private Long timestamp;
    private Long sunrise;
    private Long sunset;
    private Temperature temperature;
    private PerceivedTemperature perceivedTemperature;
    private Wind wind;
    private Weather weather;
    private Integer pressure;
    private Integer humidityPerc;
    private Double dewPoint;
    private Integer clouds;
    private Integer pop;
    private Double rain;
    private Integer uvi;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public PerceivedTemperature getPerceivedTemperature() {
        return perceivedTemperature;
    }

    public void setPerceivedTemperature(PerceivedTemperature perceivedTemperature) {
        this.perceivedTemperature = perceivedTemperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidityPerc() {
        return humidityPerc;
    }

    public void setHumidityPerc(Integer humidityPerc) {
        this.humidityPerc = humidityPerc;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public Integer getUvi() {
        return uvi;
    }

    public void setUvi(Integer uvi) {
        this.uvi = uvi;
    }
}
