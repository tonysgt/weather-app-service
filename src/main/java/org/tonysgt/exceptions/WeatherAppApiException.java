package org.tonysgt.exceptions;

import java.util.List;

public class WeatherAppApiException extends Exception {

    private int status;
    private String messageApi;
    private List<String> parameters;

    public WeatherAppApiException(String message, int status, String messageApi, List<String> parameters) {
        super(message);
        this.status = status;
        this.messageApi = messageApi;
        this.parameters = parameters;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return messageApi;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
