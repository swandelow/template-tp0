package ar.fiuba.tdd.template.tp0;

/**
 * Created by swandelow on 3/17/16.
 */
public class Token {
    private String term;
    private Quantifier quantifier;
    private StringGenerator generator;

    public Token(String term, Quantifier quantifier, StringGenerator generator) {
        this.term = term;
        this.quantifier = quantifier;
        this.generator = generator;
    }

    public String getTerm() {
        return term;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public String generate(int limit) {
        return this.generator.generate(this.term, this.quantifier, limit);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        Token other = (Token) obj;
        if (this.term.equals(other.getTerm())) {

            boolean nullQuantifiers = this.quantifier == null && other.getQuantifier() == null;
            if(nullQuantifiers) {
                return true;
            } else {
                return this.quantifier.equals(other.getQuantifier());
            }
        } else {
            return false;
        }
    }
}
