package io.spring.workshop.tradingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class QuotesController {
    private final QuotesClient quotesClient;

    @GetMapping
    @RequestMapping(value = "/quotes/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<Quote> get() {
        return quotesClient.quotesFeed();
    }
}
