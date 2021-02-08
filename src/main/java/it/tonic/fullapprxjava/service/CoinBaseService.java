package it.tonic.fullapprxjava.service;

import it.tonic.fullapprxjava.model.CoinBaseResponse;
import reactor.core.publisher.Mono;

public interface CoinBaseService {

    Mono<CoinBaseResponse> getCryptoPrice(String priceName);
}
