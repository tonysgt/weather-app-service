package org.tonysgt.model.weather;

public class WeatherAppError {

    private int code;
    private String message;

    public WeatherAppError() {
    }

    public WeatherAppError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
