package it.tonic.fullapprxjava.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import it.tonic.fullapprxjava.service.CoinBaseService;

public class PriceRequestor extends AbstractActor {

    private final ActorRef printerActor;
    private final CoinBaseService coinBaseService;

    public PriceRequestor(ActorRef printerActor, CoinBaseService coinBaseService) {
        this.printerActor = printerActor;
        this.coinBaseService = coinBaseService;
    }

    public static Props props(ActorRef actorRef, CoinBaseService coinBaseService){
        return Props.create(PriceRequestor.class,
                ()->new PriceRequestor(actorRef,coinBaseService));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(GetThisCryptoPrice.class,what -> {
            printerActor.tell(new Printer.CryptoPrice(coinBaseService.getCryptoPrice(what.whatPrice)),getSelf());
        }).build();
    }

    public static class GetThisCryptoPrice{
        private final String whatPrice;

        public GetThisCryptoPrice(String whatPrice) {
            this.whatPrice = whatPrice;
        }
    }
}
