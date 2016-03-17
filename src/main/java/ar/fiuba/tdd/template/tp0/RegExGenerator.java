package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {

    private static final int DEFAULT_MAX_LENGTH = 5;

    private int maxLength;

    public RegExGenerator() {
        this.maxLength = DEFAULT_MAX_LENGTH;
    }

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    // TODO: Uncomment parameters
    public List<String> generate(/*String regEx, int numberOfResults*/) {
        return new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
            }
        };
    }
}