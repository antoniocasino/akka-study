package it.tony.fullapprxjava.cmd;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import it.tony.fullapprxjava.actors.Poller;
import it.tony.fullapprxjava.actors.PriceRequestor;
import it.tony.fullapprxjava.actors.Printer;
import it.tony.fullapprxjava.service.CoinBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdLineUi implements CommandLineRunner {

    @Autowired
    CoinBaseService coinBaseService;

    @Override
    public void run(String... args) throws Exception {

        /*System.out.println("Learning reactive programming with Java 8");
        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tick -> coinBaseService.getCryptoPrice("BTC-USD"))
                .subscribe(new ConsolePrintObserver());

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tick -> coinBaseService.getCryptoPrice("ETH-USD"))
                .subscribe(new ConsolePrintObserver());*/

        final ActorSystem system = ActorSystem.create("helloakka");
        final ActorRef printerActor = system.actorOf(Printer.props(),"printerActor");

        final ActorRef priceRequestor = system.actorOf(
                PriceRequestor.props(printerActor,coinBaseService),"requestor");
        System.out.println("Coin Base service: \n");
        final ActorRef poller = system.actorOf(
                Poller.props("BTC-USD", priceRequestor), "poller");


    }
}
