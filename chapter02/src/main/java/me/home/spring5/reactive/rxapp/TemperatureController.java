package me.home.spring5.reactive.rxapp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureSensor temperatureSensor;

    @RequestMapping(
            value = "/temperature-stream",
            method = RequestMethod.GET
    )
    public SseEmitter events(HttpServletRequest request) {
        RxSeeEmitter emitter = new RxSeeEmitter();

        temperatureSensor.temperatureStream()
                .subscribe(emitter.getSubscriber());

        return emitter;
    }
}
