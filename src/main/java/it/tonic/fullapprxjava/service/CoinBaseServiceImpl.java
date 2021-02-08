package it.tonic.fullapprxjava.service;

import it.tonic.fullapprxjava.model.CoinBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CoinBaseServiceImpl implements CoinBaseService {

    @Autowired
    WebClient webClient;

    @Override
    public Mono<CoinBaseResponse> getCryptoPrice(String priceName) {
        return webClient.get()
                .uri("https://api.coinbase.com/v2/prices/{cryptoName}/buy",priceName)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(CoinBaseResponse.class));
    }
}
