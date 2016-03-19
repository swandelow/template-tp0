package ar.fiuba.tdd.template.tp0;

/**
 * Created by swandelow on 3/19/16.
 */

@FunctionalInterface
public interface StringGenerator {

    public String generate(String term, Quantifier quantifier, int limit);
}
