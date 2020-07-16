package io.spring.workshop.tradingservice;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class TcpConfiguration {
    @Bean
    public TcpClient tcpClient() {
        return TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .doOnConnected(connection -> {
                    connection.addHandler(new ReadTimeoutHandler(3, TimeUnit.SECONDS));
                    connection.addHandler(new WriteTimeoutHandler(3, TimeUnit.SECONDS));
                });
    }
}
