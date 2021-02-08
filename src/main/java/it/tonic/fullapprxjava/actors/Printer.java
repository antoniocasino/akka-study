package it.tonic.fullapprxjava.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import it.tonic.fullapprxjava.model.CoinBaseResponse;
import it.tonic.fullapprxjava.service.CoinBaseService;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Printer extends AbstractActor {

    public Printer() {
    }

    public static Props props(){
        return Props.create(Printer.class,Printer::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(CryptoPrice.class, msg-> msg.message.subscribe(
                coinBaseResponse ->
                    System.out.println("["+ LocalDateTime.now()+"]"+coinBaseResponse.getData().getBase() +
                            " Buy price: " + coinBaseResponse.getData().getAmount() +
                            " "+ coinBaseResponse.getData().getCurrency())
        )).build();
    }

    public static class CryptoPrice{
        public final Mono<CoinBaseResponse> message;

        public CryptoPrice(Mono<CoinBaseResponse> message) {
            this.message = message;
        }
    }
}
