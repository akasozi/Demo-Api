package io.javabrains.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greeting")
public class GreetingController {


    @Value("${my.greeting}")
    private String greetingMessage;

    @Value("${app.description}")
    private String description;

    @Autowired
    private Environment env;

    @GetMapping
    public String greetUser() {

        return greetingMessage + " " + description;
    }


    @GetMapping(path = "/env")
    public String getEnvironmentDetails() {

        return env.toString();
    }
}
