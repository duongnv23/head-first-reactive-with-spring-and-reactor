package io.spring.workshop.tradingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/details")
public class TradingCompanyController {
    private final TradingCompanyClient client;

    @GetMapping
    public Flux<TradingCompany> all() {
        return client.findAllCompanies();
    }

    @GetMapping("/{ticker}")
    public Mono<TradingCompany> one(@PathVariable String ticker) {
        return client.getTradingCompany(ticker);
    }

}
