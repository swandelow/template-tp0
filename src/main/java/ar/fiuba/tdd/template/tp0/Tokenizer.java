package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swandelow on 3/17/16.
 */
public class Tokenizer {

    private List<String> quantifiers = new ArrayList<>();

    public Tokenizer() {
        this.quantifiers.add("?");
        this.quantifiers.add("*");
        this.quantifiers.add("+");
    }

    public List<Token> tokenize(String regex) {
        ArrayList<Token> tokens = new ArrayList<>();
        String[] characters = regex.split("");
        int idx = 0;
        while (idx < characters.length) {
            String term = null;
            String quantifier = null;
            String character = characters[idx];
            // caso conjunto
            if (character.equals("[")) {
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    character = characters[idx];
                    stringBuilder.append(character);
                    if (!character.equals("]")) idx++;
                } while (!character.equals("]") && idx < characters.length);
                term = stringBuilder.toString();
                if (idx + 1 < characters.length) {
                    String nextCharacter = characters[idx + 1];
                    if (isQuantifier(nextCharacter)) {
                        quantifier = nextCharacter;
                        idx++;
                    }
                }
                idx++;
                tokens.add(new Token(term, quantifier));
                continue;
            }
            // caso caracter reservado
            if (character.equals("\\")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(characters[idx]);
                idx++;
                stringBuilder.append(characters[idx]);
                term = stringBuilder.toString();
                if (idx + 1 < characters.length) {
                    String nextCharacter = characters[idx + 1];
                    if (isQuantifier(nextCharacter)) {
                        quantifier = nextCharacter;
                        idx++;
                    }
                }
                idx++;
                tokens.add(new Token(term, quantifier));
                continue;
            }

            term = character;
            if (idx + 1 < characters.length) {
                String nextCharacter = characters[idx + 1];
                if (isQuantifier(nextCharacter)) {
                    quantifier = nextCharacter;
                    idx++;
                }
            }
            idx++;
            tokens.add(new Token(term, quantifier));
        }

        return tokens;
    }

    private boolean isQuantifier(String character) {
        return this.quantifiers.contains(character);
    }
}
