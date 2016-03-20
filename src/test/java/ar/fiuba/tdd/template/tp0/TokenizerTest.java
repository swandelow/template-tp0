package ar.fiuba.tdd.template.tp0;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swandelow on 3/17/16.
 */
public class TokenizerTest {

    private Tokenizer target = new Tokenizer();

    @Test
    public void regex_without_groups_without_quantifiers() {
        List<Token> expected = new ArrayList<Token>();
        expected.add(new Token("f", null, null));
        expected.add(new Token("r", null, null));
        expected.add(new Token("u", null, null));
        expected.add(new Token("t", null, null));
        expected.add(new Token("a", null, null));

        List<Token> result = this.target.tokenize("fruta");

        this.validate(expected, result);
    }

    @Test
    public void regex_without_groups_with_quantifiers() {
        List<Token> expected = new ArrayList<Token>();
        expected.add(new Token("f", null, null));
        expected.add(new Token("r", Quantifier.getQuantifier("*"), null));
        expected.add(new Token("u", null, null));
        expected.add(new Token("t", Quantifier.getQuantifier("?"), null));
        expected.add(new Token("a", Quantifier.getQuantifier("+"), null));

        List<Token> result = this.target.tokenize("fr*ut?a+");

        this.validate(expected, result);
    }

    @Test
    public void regex_with_groups_without_quantifiers() {
        List<Token> expected = new ArrayList<Token>();
        expected.add(new Token("f", null, null));
        expected.add(new Token("r", null, null));
        expected.add(new Token("u", null, null));
        expected.add(new Token("[ta]", null, null));

        List<Token> result = this.target.tokenize("fru[ta]");

        this.validate(expected, result);
    }

    @Test
    public void regex_with_groups_with_quantifiers() {
        List<Token> expected = new ArrayList<Token>();
        expected.add(new Token("f", null, null));
        expected.add(new Token("r", Quantifier.getQuantifier("*"), null));
        expected.add(new Token("u", null, null));
        expected.add(new Token("[ta]", Quantifier.getQuantifier("?"), null));

        List<Token> result = this.target.tokenize("fr*u[ta]?");

        this.validate(expected, result);
    }

    @Test
    public void regex_escaped_chars_without_quantifiers() {
        List<Token> expected = new ArrayList<Token>();
        expected.add(new Token("f", null, null));
        expected.add(new Token("\\[", null, null));
        expected.add(new Token("u", null, null));

        List<Token> result = this.target.tokenize("f\\[u");

        this.validate(expected, result);
    }

    @Test
    public void regex_escaped_chars_with_quantifiers() {
        List<Token> expected = new ArrayList<Token>();
        expected.add(new Token("f", null, null));
        expected.add(new Token("\\[", Quantifier.getQuantifier("*"), null));
        expected.add(new Token("u", null, null));

        List<Token> result = this.target.tokenize("f\\[*u");

        this.validate(expected, result);
    }


    private void validate(List<Token> expected, List<Token> result) {
        Assert.assertEquals(result.size(), expected.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), result.get(i));
        }
    }
}
