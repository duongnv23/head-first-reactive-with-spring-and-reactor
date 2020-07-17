package io.spring.workshop.tradingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quotes")
public class QuotesController {
    private final QuotesClient quotesClient;
    private final TradingCompanyClient companyClient;

    @GetMapping
    @RequestMapping(value = "/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<Quote> get() {
        return quotesClient.quotesFeed();
    }

    @GetMapping("/summary/{ticker}")
    public Mono<TradingCompanySummary> one(@PathVariable String ticker) {
        return companyClient.getTradingCompany(ticker)
                .zipWith(quotesClient.getLatestQuote(ticker), TradingCompanySummary::new);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TickerNotFoundException.class})
    public void onTickerNotFound(){}

}
