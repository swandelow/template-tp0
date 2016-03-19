package ar.fiuba.tdd.template.tp0;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by swandelow on 3/18/16.
 */
public enum Quantifier {

    ONCE_OR_NOT_AT_ALL {
        @Override
        String apply(String character, int limit) {
            return repeat(character, randomInt(0, 1));
        }
    }, ZERO_OR_MORE_TIMES {
        @Override
        String apply(String character, int limit) {
            return repeat(character, randomInt(0, limit));
        }
    }, ONE_OR_MORE_TIMES {
        @Override
        String apply(String character, int limit) {
            return repeat(character, randomInt(1, limit));
        }
    };

    abstract String apply(String character, int limit);

    private static String repeat(String character, int times) {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0; i < times; i++) {
            stringBuffer.append(character);
        }
        return stringBuffer.toString();
    }

    private static int randomInt(int min,int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private static Map<String, Quantifier> quantifiers = new HashMap<String, Quantifier>() {
        {
            put("?", ONCE_OR_NOT_AT_ALL);
            put("*", ZERO_OR_MORE_TIMES);
            put("+", ONE_OR_MORE_TIMES);
        }
    };

    public static Quantifier getQuantifier(String quantifierSymbol) {
        return quantifiers.get(quantifierSymbol);
    }

}
