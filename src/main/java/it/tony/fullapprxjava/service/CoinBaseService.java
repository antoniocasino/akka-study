package it.tony.fullapprxjava.service;

import it.tony.fullapprxjava.model.CoinBaseResponse;
import reactor.core.publisher.Mono;

public interface CoinBaseService {

    Mono<CoinBaseResponse> getCryptoPrice(String priceName);
}
