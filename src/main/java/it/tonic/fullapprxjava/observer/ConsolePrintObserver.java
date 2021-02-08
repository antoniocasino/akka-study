package it.tonic.fullapprxjava.observer;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DefaultObserver;
import it.tonic.fullapprxjava.model.CoinBaseResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class ConsolePrintObserver extends DefaultObserver<Mono<CoinBaseResponse>> {

    @Override
    public void onNext(Mono<CoinBaseResponse> coinBaseResponseMono) {
        coinBaseResponseMono.subscribe(coinBaseResponse ->
                System.out.println("[ " + LocalDateTime.now() + " " +coinBaseResponse.getData().getBase() +
                        "Buy Price $ " + coinBaseResponse.getData().getAmount() + " " +
                        coinBaseResponse.getData().getCurrency())
        );
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.out.println("e = " + e);
    }

    @Override
    public void onComplete() {
        System.out.println("Complete");
    }
}
