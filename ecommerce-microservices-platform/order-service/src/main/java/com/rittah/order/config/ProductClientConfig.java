
package com.rittah.order.config;

import com.rittah.order.client.ProductClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ProductClientConfig {

    @Value("${app.services.product-url}")
    private String productServiceUrl;

    @Bean
    public ProductClient productClient() {

        RestClient restClient = RestClient.builder()
                .baseUrl(productServiceUrl)
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter)
                        .build();

        return factory.createClient(ProductClient.class);
    }
}
