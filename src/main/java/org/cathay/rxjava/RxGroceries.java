package org.cathay.rxjava;

import io.reactivex.rxjava3.core.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;

import static org.cathay.rxjava.SchedulerFactory.schedulerA;

public class RxGroceries {

    public static void main(String[] args) {

        //Observable<BigDecimal> totalPrice =  badImplementation_blocking();
        Observable<BigDecimal> totalPrice = totalNonBlocking();

        totalPrice.subscribe(
                x -> log("Total price:" + x),
                Throwable::printStackTrace,
                () -> log("Completed")
        );
    }

    static Observable<BigDecimal> badImplementation_blocking() {
        return Observable.just("bread", "butter", "milk", "tomato", "cheese")
                .subscribeOn(schedulerA) //BROKEN!!!
                .map(prod -> RxGroceries.doPurchase(prod, 1))
                .reduce(BigDecimal::add)
                .toObservable();
    }

//    static Observable<BigDecimal> groupBy() {
//        return Observable
//                .just("bread", "butter", "egg", "milk", "tomato",
//                        "cheese", "tomato", "egg", "egg")
//                .groupBy(prod -> prod)

//                .flatMap(grouped -> grouped
//                        .count()
//                        .map(quantity -> {
//                            String productName = grouped.getKey();
//                            return Pair.of(productName, quantity);
//                        }))
//                        .flatMap(order -> RxGroceries
//                                .purchase(order.getLeft(), order.getRight())
//                        .subscribeOn(schedulerA))
//                        .reduce(BigDecimal::add)
//    }

    static Observable<BigDecimal> totalNonBlocking() {
        return Observable
                .just("bread", "butter", "milk", "tomato", "cheese")
                .flatMap(prod ->
                        RxGroceries
                                .purchase(prod, 1)
                                .subscribeOn(schedulerA))
                .reduce(BigDecimal::add)
                .toObservable();
    }

    static Observable<BigDecimal> purchase(String productName, long quantity) {
        return Observable.fromCallable(() ->
                doPurchase(productName, quantity));
    }

    static BigDecimal doPurchase(String productName, long quantity) {
        log("Purchasing " + quantity + " " + productName);
        //real logic here
        try {
            Thread.sleep((long) productName.length() * quantity * 1000); //assume query DB take time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("Done " + quantity + " " + productName);
        return BigDecimal.valueOf((long) productName.length() * quantity);
    }

    static void log(String s) {
        System.out.println(s + "   ||   " + Thread.currentThread().getName());
    }
}
