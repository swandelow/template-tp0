package ar.fiuba.tdd.template.tp0;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by swandelow on 3/18/16.
 */
public class QuantifierTest {

    @Test
    public void get_quantifier_method() {
        Assert.assertEquals(Quantifier.ONCE_OR_NOT_AT_ALL, Quantifier.getQuantifier('?'));
        Assert.assertEquals(Quantifier.ZERO_OR_MORE_TIMES, Quantifier.getQuantifier('*'));
        Assert.assertEquals(Quantifier.ONE_OR_MORE_TIMES, Quantifier.getQuantifier('+'));
    }

}
