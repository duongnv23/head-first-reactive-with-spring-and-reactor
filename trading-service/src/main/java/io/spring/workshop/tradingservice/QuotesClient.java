package io.spring.workshop.tradingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class QuotesClient {
    private final WebClient.Builder builder;

    private final TcpClient tcpClient;


    public Flux<Quote> quotesFeed() {
        WebClient webClient = builder.baseUrl("http://localhost:8081")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();

        return webClient.get()
                .uri("/quotes")
                .retrieve()
                .bodyToFlux(Quote.class);
    }

    public Mono<Quote> getLatestQuote(String ticker) {
        return quotesFeed()
                .filter(q -> ticker.equalsIgnoreCase(q.getTicker()))
                .next()
                .timeout(Duration.ofSeconds(15), Mono.just(Quote.of(ticker)))
                ;


    }
}
