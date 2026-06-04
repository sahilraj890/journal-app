package net.sahil.journalApp;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class Dog {

    public String sound() {
        return "bark";
    }
}
