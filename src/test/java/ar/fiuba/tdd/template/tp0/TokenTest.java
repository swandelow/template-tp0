package ar.fiuba.tdd.template.tp0;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by swandelow on 3/20/16.
 */
public class TokenTest {

    @Test
    public void equality_of_tokens() {
        Token token1 = new Token("A", null, null);
        Token token2 = new Token("A", null, null);
        Assert.assertEquals(token1, token2);

        token1 = new Token("\\A", Quantifier.ONE_OR_MORE_TIMES, null);
        token2 = new Token("\\A", Quantifier.ONE_OR_MORE_TIMES, null);
        Assert.assertEquals(token1, token2);

        token1 = new Token("\\A[ng]", Quantifier.ONE_OR_MORE_TIMES, null);
        token2 = new Token("\\A[ng]", Quantifier.ONE_OR_MORE_TIMES, AbstractStringGenerator::charGenerator);
        Assert.assertEquals(token1, token2);
    }

    @Test
    public void inequality_of_tokens() {
        Token token1 = new Token("A", null, null);
        Token token2 = new Token("B", null, null);
        Assert.assertNotEquals(token1, token2);

        token1 = new Token("\\A", Quantifier.ONE_OR_MORE_TIMES, null);
        token2 = new Token("\\A", Quantifier.ZERO_OR_MORE_TIMES, null);
        Assert.assertNotEquals(token1, token2);
    }

}
