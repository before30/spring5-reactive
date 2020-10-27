package me.home.spring5.reactive.observer;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelSubject implements Subject<String> {
    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();
    public final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        observers.forEach(
                o -> executorService.submit(
                        () -> o.observe(event)
                )
        );
    }
}
