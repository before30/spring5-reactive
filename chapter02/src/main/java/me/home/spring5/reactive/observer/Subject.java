package me.home.spring5.reactive.observer;

public interface Subject<T> {
    void registerObserver(Observer<T> observer);
    void unregisterObserver(Observer<T> observer);
    void notifyObservers(T event);
}
