package ar.fiuba.tdd.template.tp0;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by swandelow on 3/17/16.
 */
public class Tokenizer {

    private int idx;

    public Tokenizer() {
        this.idx = 0;
    }

    public List<Token> tokenize(String regex) {
        ArrayList<Token> tokens = new ArrayList<>();
        String[] characters = regex.split("");
        this.idx = 0;
        while (this.getIndex() < characters.length) {
            String character = characters[this.getIndex()];
            // caso conjunto
            if (character.equals("[")) {
                Token token = this.parseCharacterSet(characters);
                tokens.add(token);
                continue;
            }
            // caso caracter reservado
            if (character.equals("\\")) {
                Token token = this.parseLiteral(characters);
                tokens.add(token);
                continue;
            }

            String term = character;
            Quantifier quantifier = this.getQuantifier(characters);
            this.incrementIndex();
            tokens.add(new Token(term, quantifier, AbstractStringGenerator::charGenerator));
        }

        return tokens;
    }

    private Token parseCharacterSet(String[] characters) {
        String character;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            character = characters[this.getIndex()];
            stringBuilder.append(character);
            if (!character.equals("]")) {
                this.incrementIndex();
            }
        } while (!character.equals("]") && this.getIndex() < characters.length);

        String term = stringBuilder.toString();
        Quantifier quantifier = this.getQuantifier(characters);
        this.incrementIndex();
        return new Token(term, quantifier, AbstractStringGenerator::groupCharGenerator);
    }

    private Token parseLiteral(String[] characters) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(characters[this.getIndex()]);
        this.incrementIndex();
        stringBuilder.append(characters[this.getIndex()]);
        String term = stringBuilder.toString();
        Quantifier quantifier = this.getQuantifier(characters);
        this.incrementIndex();
        return new Token(term, quantifier, AbstractStringGenerator::escapedCharGenerator);
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
