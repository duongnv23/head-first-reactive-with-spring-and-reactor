package io.spring.workshop.tradingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TradingCompanyClient {
    private final String BASE_URL = "http://localhost:8082";

    private final WebClient.Builder builder;

    public Flux<TradingCompany> findAllCompanies() {
        var client = builder.baseUrl(BASE_URL)
                .build();
        return client.get()
                .uri("/details")
                .retrieve()
                .bodyToFlux(TradingCompany.class);
    }

    public Mono<TradingCompany> getTradingCompany(String ticker) {
        var client = builder.baseUrl(BASE_URL)
                .build();
        return client.get()
                .uri("/details/{ticker}", ticker)
                .retrieve()
                .bodyToMono(TradingCompany.class)
                .switchIfEmpty(Mono.error(TickerNotFoundException::new));
    }
}
