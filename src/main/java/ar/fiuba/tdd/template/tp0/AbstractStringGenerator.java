package ar.fiuba.tdd.template.tp0;

import java.util.Random;

/**
 * Created by swandelow on 3/19/16.
 */
public abstract class AbstractStringGenerator {

    private static final String DOT = ".";

    public static String charGenerator(String term, Quantifier quantifier, int limit) {
        String character = DOT.equals(term) ? RandomUtils.randomChar() : term;
        return quantifier != null ? quantifier.apply(character, limit) : term;
    }

    public static String groupCharGenerator(String term, Quantifier quantifier, int limit) {
        final String[] characters = term.substring(1, term.length() - 1).split("");
        String character = characters[RandomUtils.randomInt(0, characters.length - 1)];
        return quantifier != null ? quantifier.apply(character, limit) : character;
    }

    public static String escapedCharGenerator(String term, Quantifier quantifier, int limit) {
        String character = term.substring(1);
        return quantifier != null ? quantifier.apply(character, limit) : character;
    }

}
