package ar.fiuba.tdd.template.tp0;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by swandelow on 3/17/16.
 */
public class Tokenizer {

    private static final Character LEFT_SQUARE_BRACKET = '[';
    private static final Character RIGHT_SQUARE_BRACKET = ']';
    private static final Character BACKSLASH = '\\';

    private int idx;

    public Tokenizer() {
        this.idx = 0;
    }

    public List<Token> tokenize(String regex) {
        ArrayList<Token> tokens = new ArrayList<>();
        Character[] characters = this.getCharacters(regex);
        this.idx = 0;
        while (this.getIndex() < characters.length) {
            Character character = characters[this.getIndex()];
            // caso conjunto
            if (LEFT_SQUARE_BRACKET.equals(character)) {
                Token token = this.parseCharacterSet(characters);
                tokens.add(token);
                continue;
            }
            // caso caracter reservado
            if (BACKSLASH.equals(character)) {
                Token token = this.parseLiteral(characters);
                tokens.add(token);
                continue;
            }

            String term = String.valueOf(character);
            Quantifier quantifier = this.getQuantifier(characters);
            this.incrementIndex();
            tokens.add(new Token(term, quantifier, AbstractStringGenerator::charGenerator));
        }

        return tokens;
    }

    private Token parseCharacterSet(Character[] characters) {
        Character character;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            character = characters[this.getIndex()];
            stringBuilder.append(character);
            if (!RIGHT_SQUARE_BRACKET.equals(character)) {
                this.incrementIndex();
            }
        } while (!RIGHT_SQUARE_BRACKET.equals(character) && this.getIndex() < characters.length);

        String term = stringBuilder.toString();
        Quantifier quantifier = this.getQuantifier(characters);
        this.incrementIndex();
        return new Token(term, quantifier, AbstractStringGenerator::groupCharGenerator);
    }

    private Token parseLiteral(Character[] characters) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(characters[this.getIndex()]);
        this.incrementIndex();
        stringBuilder.append(characters[this.getIndex()]);
        String term = stringBuilder.toString();
        Quantifier quantifier = this.getQuantifier(characters);
        this.incrementIndex();
        return new Token(term, quantifier, AbstractStringGenerator::escapedCharGenerator);
    }

    private Quantifier getQuantifier(Character[] characters) {
        Quantifier quantifier = null;
        if (this.getIndex() + 1 < characters.length) {
            Character nextCharacter = characters[this.getIndex() + 1];
            if (isQuantifier(nextCharacter)) {
                quantifier = Quantifier.getQuantifier(nextCharacter);
                this.incrementIndex();
            }
        }
        return quantifier;
    }

    private boolean isQuantifier(Character character) {
        Quantifier quantifier = Quantifier.getQuantifier(character);
        return quantifier != null;
    }

    private void incrementIndex() {
        this.idx++;
    }

    private int getIndex() {
        return this.idx;
    }

    private Character[] getCharacters(String regex) {
        return regex.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    }
}
