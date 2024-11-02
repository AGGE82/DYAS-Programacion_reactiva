package com.example.RxJava;

import io.reactivex.Observable;
/**
 * Hello world!
 *
 */
public class RxJavaMapOperatorExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .map(item -> item * 2)
                .subscribe(System.out::println);
    }
}
