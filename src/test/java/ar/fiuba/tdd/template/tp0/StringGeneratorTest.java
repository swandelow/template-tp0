package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

/**
 * Created by swandelow on 3/19/16.
 */
public class StringGeneratorTest {

    @Test
    public void dot_generator() {
        for (int i = 0; i < 30; i++) {
            String result = AbstractStringGenerator.charGenerator("bla", Quantifier.ONE_OR_MORE_TIMES, 5);
            System.out.println(String.format("%s - %s", i, result));
        }

    }
}
