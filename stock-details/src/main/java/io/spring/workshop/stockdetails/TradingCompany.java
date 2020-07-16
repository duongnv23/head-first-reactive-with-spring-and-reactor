package io.spring.workshop.stockdetails;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class TradingCompany {

    @Id
    private String id;

    private String description;

    private String ticker;

    public static TradingCompany of(String description, String ticker) {
        TradingCompany company = new TradingCompany();
        company.ticker = ticker;
        company.description = description;
        return company;
    }

}
