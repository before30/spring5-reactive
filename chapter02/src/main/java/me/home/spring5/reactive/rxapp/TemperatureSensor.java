package me.home.spring5.reactive.rxapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class TemperatureSensor {
    private final Random rnd = new Random();

    private final Observable<Temperature> dataStream =
            Observable
            .range(0, Integer.MAX_VALUE)
            .concatMap(ignore -> Observable
                    .just(1)
                    .delay(rnd.nextInt(5000), TimeUnit.MILLISECONDS)
                    .map(ignore2 -> this.probe()))
            .publish()
            .refCount();

    public Observable<Temperature> temperatureStream() {
        return dataStream;
    }

    private Temperature probe() {
        double actualTemp = 16 + rnd.nextGaussian() * 10;
        log.info("Asking sensor, sensor value: {}", actualTemp);
        return new Temperature(actualTemp);
    }
}
