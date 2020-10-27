package me.home.spring5.reactive.observer;

public interface Observer<T> {
    void observe(T event);
}
