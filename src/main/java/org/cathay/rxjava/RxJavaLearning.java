package org.cathay.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.cathay.datastructure.Pair;
import org.reactivestreams.Subscriber;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.reactivex.rxjava3.core.Observable.timer;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class RxJavaLearning {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> observable = Observable.range(1, 5);
        observable
                .flatMap(
                        x ->  Observable.just(x)
                                .doOnNext(s -> System.out.println("flatMap:"+Thread.currentThread().getName()))
                                .subscribeOn(Schedulers.io()))
//                .observeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.newThread())
                .map(x -> {
                    System.out.println("map:"+Thread.currentThread().getName());
                    return x;
                })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(Schedulers.computation())
                .subscribe(i -> {
                    Thread.sleep(1000);
                    System.out.println("i=" + i + " " + Thread.currentThread().getName());
                });

//        Observable.just("long", "longer", "longest")
//                .flatMap(v ->
//                        performLongOperation(v)
//                                .doOnNext(s -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
//                                .subscribeOn(Schedulers.newThread()))
//                .subscribe(length -> System.out.println("received item length " + length + " on thread " + Thread.currentThread().getName()));


        Thread.sleep(7000);
//
//        o.subscribeOn(Schedulers.newThread())
//                .delay(1, SECONDS)
//                .subscribe(System.out::println);


//        Observable.range(1, 10)
//                .flatMap(x -> {
//                    if (x == 4 && count == 0) {
//                        count++;
////                        return Observable.error(new RuntimeException("Something went wrong!"));
//                    }
//                     return Observable.just(x);
//                })
//                .retry(2)
//                .subscribe(i -> {
//                    System.out.println(Thread.currentThread().getName() + ".." + i);
//                },
//                        err -> System.out.println(err.toString()));
//        .forEach(i -> System.out.println(Thread.currentThread().getName() + ".." + i) );
//        Observable<Integer> progress = Observable.range(1, 5);
//        progress.scan(Integer::sum)
//                .subscribe(System.out::println);
//
//        speakDemo();

//        sleep(15, SECONDS);
        System.out.println("Main thread.......");
    }

    protected static Observable<Integer> performLongOperation(String v) {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(3) * 1000);
            return Observable.just(v.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void speakDemo() {
        Observable<String> alice = speak(
                "To be, or not to be: that is the question", 110);
        Observable<String> bob = speak(
                "Though this be madness, yet there is method in't", 90);
        Observable<String> jane = speak("There are more things in Heaven and Earth, " +
                "Horatio, than are dreamt of in your philosophy", 100);

//        Observable.merge(
//                alice.map(w -> "Alice:" + w),
//                bob.map(w -> "Bob:" + w),
//                jane.map(w -> "Jane:" + w)
//        ).subscribe(System.out::println);

        Observable.concat(
                alice.map(w -> "Alice:" + w),
                bob.map(w -> "Bob:" + w),
                jane.map(w -> "Jane:" + w)
        ).subscribe(System.out::println);
    }

    static Observable<String> speak(String quote, long millisPerChar) {
        String[] tokens = quote.replaceAll("[:,]", "").split(" ");
        Observable<String> words = Observable.fromArray(tokens);
        Observable<Long> absoluteDelay = words
                .map(String::length)
                .map(len -> len * millisPerChar)
                .scan((total, current) -> total + current);
        return words
                .zipWith(absoluteDelay.startWith(Observable.just(0L)), Pair::of)
                .flatMap(pair -> Observable.just(pair.first)
                        .delay(pair.second, MILLISECONDS));
        //delayAndTimer();
    }

    static void lazy() {
        Observable<Integer> infinite = Observable
                .range(0, Integer.MAX_VALUE)
                .takeWhile(i -> i < 20);

        infinite.take(21)
                .subscribe(System.out::println);
    }

    static void delayAndTimer() {
        Observable
                .just("Lorem", "ipsum", "dolor", "sit", "amet",
                        "consectetur", "adipiscing", "elit")
                .flatMap(word ->
                        timer(word.length(), SECONDS, Schedulers.io()).map(x -> word))
                .subscribe(RxJavaLearning::println);

        Observable
                .just("Lorem", "ipsum", "dolor", "sit", "amet",
                        "consectetur", "adipiscing", "elit")
                .delay(word -> timer(word.length(), SECONDS))
                .subscribe(System.out::println);
    }

    private static void println(String s) {
        System.out.println(s + ":" + Thread.currentThread().getName());
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
