package me.home.spring5.reactive.pubsubapp.domain;

import lombok.Getter;

public final class Temperature {
    @Getter
    private final double value;

    public Temperature(double temperature) {
        this.value = temperature;
    }
}
