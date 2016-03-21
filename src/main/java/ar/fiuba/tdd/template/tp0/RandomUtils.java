package ar.fiuba.tdd.template.tp0;

import java.util.Random;

/**
 * Created by swandelow on 3/19/16.
 */
public abstract class RandomUtils {

    public static int randomInt(int min,int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static String randomChar() {
        char character = (char) RandomUtils.randomInt(65, 122);
        return String.valueOf(character);
    }
}
