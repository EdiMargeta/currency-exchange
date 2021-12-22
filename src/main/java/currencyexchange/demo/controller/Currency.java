package currencyexchange.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Currency {

    @GetMapping("/message")
    public String test() {
        return "Currency is TRUE!!!!!!!";
    }
}
