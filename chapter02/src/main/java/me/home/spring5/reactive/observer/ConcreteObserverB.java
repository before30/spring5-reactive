package me.home.spring5.reactive.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteObserverB implements Observer<String> {
    @Override
    public void observe(String event) {
        log.info("Observer B: {}", event);
    }
}
