package com.y.observer;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class HelloRx {
    public static void main(String[] args) {
        // 被订阅者  观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                TimeUnit.SECONDS.sleep(10);
                emitter.onNext("hello");
                TimeUnit.SECONDS.sleep(1);
                emitter.onNext("world");
                TimeUnit.SECONDS.sleep(1);
                emitter.onNext("!");
                TimeUnit.SECONDS.sleep(3);
                emitter.onComplete();
            }
        });
        // 订阅者 目标
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(Thread.currentThread().getName() + "==" + s);
            }
        };
        Consumer<String> consumer2 = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(Thread.currentThread().getName() + "===" + s);
            }
        };

        observable.subscribe(consumer1);
        observable.subscribe(consumer2);

        Observer<String > observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("===" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("!!!");
            }
        };

        observable.subscribe(observer);

        for(;;);
    }

}
