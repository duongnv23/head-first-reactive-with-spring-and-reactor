package io.spring.workshop.stockdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TradingCompanyCommandLineRunner implements CommandLineRunner {

    private final TradingCompanyRepository repository;

    @Override
    public void run(String... args) throws Exception {

        List<TradingCompany> companies = Arrays.asList(
                TradingCompany.of("Pivotal Software", "PVTL"),
                TradingCompany.of("Dell Technologies", "DELL"),
                TradingCompany.of("Google", "GOOG"),
                TradingCompany.of("Microsoft", "MSFT"),
                TradingCompany.of("Oracle", "ORCL"),
                TradingCompany.of("Red Hat", "RHT"),
                TradingCompany.of("Vmware", "VMW")
        );

        this.repository.insert(companies).blockLast(Duration.ofSeconds(30));

    }
}
