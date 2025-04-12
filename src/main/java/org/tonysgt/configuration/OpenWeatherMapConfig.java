package org.tonysgt.configuration;


import io.smallrye.config.ConfigMapping;

import java.util.Optional;

@ConfigMapping(prefix = "openWeatherMap")
public interface OpenWeatherMapConfig {

    String apikey();

    Optional<String> lang();

    Optional<String> units();
}
