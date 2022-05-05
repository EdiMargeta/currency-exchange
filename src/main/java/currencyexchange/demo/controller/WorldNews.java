package currencyexchange.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import currencyexchange.demo.model.NewsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/world-news")
public class WorldNews {

    private final WebClient.Builder webClientBuilder = WebClient.builder();

    @GetMapping("/get-world-news")
    public NewsResponse test() throws JsonProcessingException {

        String exchangeResponseJson = null;
        try {
            exchangeResponseJson = webClientBuilder.build()
                    .get()
                    .uri("https://newsdata.io/api/1/news?apikey=pub_71112dcfcfe66de23bfd6548b2606f593b1f&country=au,ca,gb,ru,kp")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(exchangeResponseJson, NewsResponse.class);
    }

}
