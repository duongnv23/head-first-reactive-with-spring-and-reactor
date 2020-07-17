package io.spring.workshop.stockquotes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class QuoteHandler {
    private final QuoteGenerator generator;

    public Mono<ServerResponse> streamQuotes(ServerRequest request) {
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(this.generator.fetchQuoteStream(), Quote.class);
    }
}
