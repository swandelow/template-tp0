package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.Random;

/**
 * Created by swandelow on 3/18/16.
 */
public class RandomTest {

    @Test
    public void test() {
        StringBuffer stringBufferA = new StringBuffer();
        StringBuffer stringBufferB = new StringBuffer();
        for (int i = 0; i < 30; i++) {
            //System.out.println(String.format("%s - %s", i, Quantifier.ONCE_OR_NOT_AT_ALL.apply("B", 10)));
            //System.out.println(String.format("%s - %s", i, Quantifier.ONE_OR_MORE_TIMES.apply("B", 5)));
            System.out.println(String.format("%s - %s", i, Quantifier.ZERO_OR_MORE_TIMES.apply("B", 5)));
        }
        //System.out.println(String.format("string: %s - len: %s", stringBufferA.toString(), stringBufferA.toString().length()));
        //System.out.println(String.format("string: %s - len: %s", stringBufferB.toString(), stringBufferB.toString().length()));
    }
}
