package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {

    private int maxLength;
    private Tokenizer tokenizer;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
        this.tokenizer = new Tokenizer();
    }

    public List<String> generate(String regEx, int numberOfResults) {
        List<Token> tokens = this.tokenizer.tokenize(regEx);
        List<String> strings = new ArrayList<>(numberOfResults);
        for (int i = 0; i < numberOfResults; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            for (Token token : tokens) {
                stringBuffer.append(token.generate(this.maxLength));
            }
            strings.add(stringBuffer.toString());
        }
        return strings;
    }
}