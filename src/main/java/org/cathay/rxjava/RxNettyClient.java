package org.cathay.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.cathay.rxjava.CreateObservable.log;
import static org.cathay.rxjava.SchedulerFactory.schedulerA;

public class RxNettyClient {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        List<URI> targets = Collections.singletonList(
                new URI("https://jsonplaceholder.typicode.com/todos/1"));
        HttpClient client = HttpClient.newHttpClient();
        Observable.fromStream(targets.stream())
            .map(target -> client
                    .sendAsync(
                            HttpRequest.newBuilder(target).GET().build(),
                            HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body))
            .flatMap(FutureUtils::observe)
              //  .subscribeOn(schedulerA)
            .subscribe(CreateObservable::log);

//        List<Observable<String>> futureObservables =
//                targets.stream()
//                .map(target -> client
//                        .sendAsync(
//                                HttpRequest.newBuilder(target).GET().build(),
//                                HttpResponse.BodyHandlers.ofString())
//                        .thenApply(HttpResponse::body))
//                .map(FutureUtils::observe)
//                .collect(Collectors.toList());
//
//        futureObservables.forEach(o ->
//                //.subscribeOn(schedulerA)
//                o.subscribe(
//                        CreateObservable::log,
//                        (err) ->  log(err.getMessage()),
//                        System.out::println
//                ));

         Thread.sleep(3000);
//        Observable<ByteBuf> response = HttpClient
//                .newClient("example.com", 80)
//                .createGet("/")
//                .flatMap(HttpClientResponse::getContent);
//        response
//                .map(bb -> bb.toString(UTF_8))
//                .subscribe(System.out::println);
    }


}
