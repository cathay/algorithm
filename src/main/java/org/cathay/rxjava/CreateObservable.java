package org.cathay.rxjava;

import io.reactivex.rxjava3.core.Observable;

import static org.cathay.rxjava.SchedulerFactory.*;

public class CreateObservable {

    public static void main(String[] args) {
        //notExecutedIfNotSubscribed();
       // executeOnSchedulerA();
        mixSchedulersOnObserverAndSubscriber();
    }

    static void mixSchedulersOnObserverAndSubscriber() {
        log("Starting");
        final Observable<String> obs = simple(); log("Created");
        obs
                .map(x -> x + "1")
                .doOnNext(x -> log("Found 1: " + x))
                .observeOn(schedulerB)
                .map(x -> x + "2")
                .doOnNext(x -> log("Found 2: " + x))
                //.observeOn(schedulerC)
                .map(x -> x + "3")
                .doOnNext(x -> log("Found 3: " + x))
                .subscribeOn(schedulerA)
                .subscribe(
                        x -> log("Got 1: " + x),
                        Throwable::printStackTrace,
                        () -> log("Completed")
                );
        log("Exiting");
    }

    static void executeOnSchedulerA() {
        log("Starting");
        final Observable<String> obs = simple();
        log("Created");
        obs
                .doOnNext(CreateObservable::log).map(x -> x + '1')
                .doOnNext(CreateObservable::log).map(x -> x + '2')
                .subscribeOn(schedulerA)
                .doOnNext(CreateObservable::log).subscribe(
                x -> log("Got " + x),
                Throwable::printStackTrace,
                () -> log("Completed")
        );
        log("Exiting");
    }

    static void log(String s) {
        System.out.println(s + "   ||   " + Thread.currentThread().getName());
    }

    static void notExecutedIfNotSubscribed() {
        System.out.println("Starting");
        final Observable<String> obs = simple();
        System.out.println("Created");
        final Observable<String> obs2 = obs
                .map(x -> x)
                .filter(x -> true);
        System.out.println("Transformed");
        obs2.subscribe(
                x -> System.out.println("Got " + x),
                Throwable::printStackTrace,
                () -> System.out.println("Completed"));
        System.out.println("Exiting");
    }

    static Observable<String> simple() {
        return Observable.create(subscriber -> {
            log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onComplete();
        });
    }
}
