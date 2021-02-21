package org.cathay.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FutureUtils {

    static <T> Observable<T> observe(CompletableFuture<T> future) {
        return Observable.create(subscriber -> {
            future.whenComplete((value, exception) -> {
                if (exception != null) {
                    subscriber.onError(exception);
                } else {
                    subscriber.onNext(value);
                    subscriber.onComplete();
                }
            });
        });
    }

    static <T> CompletableFuture<T> toFuture(Observable<T> observable) {
        CompletableFuture<T> promise = new CompletableFuture<>();
        observable
                .singleElement()
                .subscribe(
                        promise::complete,
                        promise::completeExceptionally
                );
        return promise;
    }

    static <T> CompletableFuture<List<T>> toFutureList(Observable<T> observable) {
        return toFuture(observable.toList().toObservable());
    }
}
