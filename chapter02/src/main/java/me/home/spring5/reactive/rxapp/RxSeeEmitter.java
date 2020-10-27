package me.home.spring5.reactive.rxapp;

import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscriber;

import java.io.IOException;

public class RxSeeEmitter extends SseEmitter {

    static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
    @Getter
    private final Subscriber<Temperature> subscriber;

    public RxSeeEmitter() {
        super(SSE_SESSION_TIMEOUT);

        this.subscriber = new Subscriber<Temperature>() {


            @Override
            public void onNext(Temperature temperature) {
                try {
                    RxSeeEmitter.this.send(temperature);
                } catch (IOException e) {
                     unsubscribe();
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable t) {

            }

        };

        onCompletion(subscriber::unsubscribe);
        onTimeout(subscriber::unsubscribe);
    }
}
