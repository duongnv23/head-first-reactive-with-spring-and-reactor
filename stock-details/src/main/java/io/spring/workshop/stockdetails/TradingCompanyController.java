package io.spring.workshop.stockdetails;

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
    private final TradingCompanyRepository repository;

    @GetMapping
    public Flux<TradingCompany> all() {
        return repository.findAll();
    }

    @GetMapping("/{ticker}")
    public Mono<TradingCompany> one(@PathVariable String ticker) {
        return repository.findByTicker(ticker);
    }

}
