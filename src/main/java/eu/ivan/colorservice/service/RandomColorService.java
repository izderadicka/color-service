package eu.ivan.colorservice.service;

import java.util.Optional;
import java.util.Random;

import reactor.core.publisher.Mono;

public class RandomColorService implements ColorService{

    public Mono<Color> generateColor(Optional<String> name) {
        byte[] colors = new byte[3];
        Random rng = name.map(s -> new Random(name.hashCode())).orElseGet(()-> new Random());
        rng.nextBytes(colors);
        String result = "#";

        for (int i=0; i< colors.length; i++) {
            int newColor =(int) ((colors[i] & 0xFF) * 0.75);
            result += String.format("%1$02x", newColor);
        }

        


        return Mono.just( new Color(result));
    }
    
}
