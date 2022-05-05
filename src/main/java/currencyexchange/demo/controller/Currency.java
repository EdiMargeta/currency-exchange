package currencyexchange.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import currencyexchange.demo.model.ExchangeResponse;
import currencyexchange.demo.model.NewsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/currency-exchange")
public class Currency {

    private final WebClient.Builder webClientBuilder = WebClient.builder();

    @GetMapping("/currency/{from}-{to}/amount/{amount}")
    public ExchangeResponse test(@PathVariable String from, @PathVariable String to, @PathVariable Double amount) throws JsonProcessingException {

        String exchangeResponseJson = null;
        try {
            exchangeResponseJson = webClientBuilder.build()
                    .get()
                    .uri("https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "" + "&amount=" + amount)
                    .header("apikey", "ZOGO9dmX9u5OUgCuQUgIigM00n39Yr56")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(exchangeResponseJson, ExchangeResponse.class);
    }

}
