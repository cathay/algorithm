package org.cathay.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import static io.reactivex.rxjava3.core.Observable.timer;
import static java.util.concurrent.TimeUnit.SECONDS;

public class RxJavaLearning {

    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> o = Observable.range(1, 3);
//        observable.subscribeOn(Schedulers.io()).subscribe(i -> System.out.println("i=i+1:" + (i + 1) + Thread.currentThread().getName()));
//        observable.subscribe(i -> System.out.println("i=i*2:" + (i * 2) + Thread.currentThread().getName()));


        o.subscribeOn(Schedulers.newThread())
                .delay(1, SECONDS)
                .subscribe(System.out::println);


        delayAndTimer();
        sleep(8, SECONDS);
        System.out.println("Main thread.......");
    }

    static void delayAndTimer() {
        Observable
                .just("Lorem", "ipsum", "dolor", "sit", "amet",
                        "consectetur", "adipiscing", "elit")
                .flatMap(word ->
                        timer(word.length(), SECONDS, Schedulers.io()).map(x -> word))
                .subscribe(RxJavaLearning::println);

//        Observable
//                .just("Lorem", "ipsum", "dolor", "sit", "amet",
//                        "consectetur", "adipiscing", "elit")
//                .delay(word -> timer(word.length(), SECONDS))
//                .subscribe(System.out::println);
    }

    private static void println(String s) {
        System.out.println(s +":" + Thread.currentThread().getName());
    }

    static Observable<String> createChainObservable() {
        return Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i)).filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i)).map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s)).filter(s -> s.length() < 4);
    }

    static ConnectableObservable createConnectable() {
        Observable<Integer> o = Observable.range(1, 3);
        ConnectableObservable<Integer> published = o.publish();
        published.subscribe(System.out::println);
        published.subscribe(System.out::println);
        published.connect();

        return published;
    }

    static Observable<Integer> createObservable() {
        return Observable.create(subcriber -> {
            subcriber.onNext(42);
            subcriber.onNext(12);
            subcriber.onComplete();
        });
    }

    static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (InterruptedException ignored) {
            //intentionally ignored
        }
    }
}
