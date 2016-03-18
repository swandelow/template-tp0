package ar.fiuba.tdd.template.tp0;

/**
 * Created by swandelow on 3/17/16.
 */
public class Token {
    private String term;
    private String quantifier;

    public Token(String term, String quantifier) {
        this.term = term;
        this.quantifier = quantifier;
    }

    public String getTerm() {
        return term;
    }

    public String getQuantifier() {
        return quantifier;
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
