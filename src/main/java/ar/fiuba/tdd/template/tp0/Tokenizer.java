package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swandelow on 3/17/16.
 */
public class Tokenizer {

    private List<String> quantifiers = new ArrayList<>();
    private int idx;

    public Tokenizer() {
        this.idx = 0;
        this.quantifiers.add("?");
        this.quantifiers.add("*");
        this.quantifiers.add("+");
    }

    public List<Token> tokenize(String regex) {
        ArrayList<Token> tokens = new ArrayList<>();
        String[] characters = regex.split("");
        this.idx = 0;
        while (this.getIndex() < characters.length) {
            String term = null;
            Quantifier quantifier = null;
            String character = characters[this.getIndex()];
            // caso conjunto
            if (character.equals("[")) {
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    character = characters[this.getIndex()];
                    stringBuilder.append(character);
                    if (!character.equals("]")) {
                        this.incrementIndex();
                    }
                } while (!character.equals("]") && this.getIndex() < characters.length);

                term = stringBuilder.toString();
                quantifier = this.getQuantifier(characters);
                this.incrementIndex();
                tokens.add(new Token(term, quantifier, AbstractStringGenerator::groupCharGenerator));
                continue;
            }
            //TODO fixear esto!
            // caso caracter reservado
            if (character.equals("\\")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(characters[this.getIndex()]);
                this.incrementIndex();
                stringBuilder.append(characters[this.getIndex()]);
                term = stringBuilder.toString();
                quantifier = this.getQuantifier(characters);
                this.incrementIndex();
                tokens.add(new Token(term, quantifier, null));
                continue;
            }

            term = character;
            quantifier = this.getQuantifier(characters);
            this.incrementIndex();
            tokens.add(new Token(term, quantifier, AbstractStringGenerator::charGenerator));
        }

        return tokens;
    }

    private Quantifier getQuantifier(String[] characters) {
        Quantifier quantifier = null;
        if (this.getIndex() + 1 < characters.length) {
            String nextCharacter = characters[this.getIndex() + 1];
            if (isQuantifier(nextCharacter)) {
                quantifier = Quantifier.getQuantifier(nextCharacter);
                this.incrementIndex();
            }
        }
        return quantifier;
    }

    private boolean isQuantifier(String character) {
        Quantifier quantifier = Quantifier.getQuantifier(character);
        return quantifier != null;
    }

    private void incrementIndex() {
        this.idx++;
    }

    private int getIndex() {
        return this.idx;
    }
}
