package com.example;

import io.reactivex.Observable;
import reactor.core.publisher.Flux;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.annotation.Order;

class Product {
    //Creación de un objeto llamado Product que cuenta con su fecha de vencimiento y precio del producto como atributos
    private final String expirationDate;
    private final int productPrice;

    public Product(String expirationDate, int productPrice) {
        this.expirationDate=expirationDate;
        this.productPrice=productPrice;
    }
    //getters de cada atributo
    public String getExpirationDate(){
        return expirationDate;
    }
    public int getProductPrice(){
        return productPrice;
    }
}

public class ImplementationExample {
    public static void main(String[] args) {
        //Crear un List de Products el cual sera modificado por los operadores
        List<Product> products = Arrays.asList(
                new Product("07/25", 255),
                new Product("12/24", 30),
                new Product("01/26",  55),
                new Product("08/25",  20)
                );
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEscriba la opción que desea utilizar\n map \n filter \n flatMap \n merge \n zip: ");
        String value = scanner.nextLine();
        //Sistema del menú de opciones
        switch (value) {
            case "map":
                map(products);
                break;
            case "filter":
                filter(products);
                break;
            case "flatMap":
                flatMap(products);
                break;
            case "merge":
                merge(products);
                break;
            case "zip":
                zip(products);
                break;
            default:
                break;
        }
    }
    //Implementación de los operadores
    public static void map(List<Product> products){
        Observable.fromIterable(products)
                .map(product -> product.getProductPrice() * 2)
                .subscribe(System.out::println);
    }

    public static void filter(List<Product> products){
        Observable.fromIterable(products)
                .filter(product -> product.getProductPrice() % 2 == 0)
                .subscribe(System.out::println);
    }

    public static void flatMap(List<Product> products){
        Observable.fromIterable(products)
                .flatMap(product -> Observable.just(product.getProductPrice() * 10, product.getProductPrice() * 20))
                .subscribe(System.out::println);
    }
    public static void merge(List<Product> products){
        Observable<Object> observable1 = Observable.fromIterable(products).map(product -> product.getExpirationDate());
        Observable<Object> observable2 = Observable.fromIterable(products).map(product -> product.getProductPrice());

        Observable.merge(observable1, observable2)
                .subscribe(System.out::println);
    }
    public static void zip(List<Product> products){
        Observable<Object> observable1 = Observable.fromIterable(products).map(product -> product.getProductPrice());
        Observable<Object> observable2 = Observable.fromIterable(products).map(product -> product.getExpirationDate());
        Observable<String> stringObservable1 = observable1.map(obj -> obj.toString());
        Observable<String> stringObservable2 = observable2.map(obj -> obj.toString());
        Observable.zip(stringObservable1, stringObservable2, (num, letter) -> num +"|"+ letter)
                .subscribe(System.out::println);
    }
}