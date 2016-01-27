import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

    public void chyba(String msg) throws ParseError {
        throw new ParseError(msg);
    }

    // Tokenizer
    public List<Token> tokenize(BufferedReader reader) throws IOException, ParseError {
        List<Token> tokens = new LinkedList<>();

        int i;
        boolean number = false;
        boolean negate = true;
        boolean symbol = false;
        StringBuilder builder = new StringBuilder();
        while ((i = reader.read()) != -1) {
            char c = (char) i;

            if (Character.isWhitespace(c)) {
                if (builder.length() > 0) {
                    if (symbol) {
                        tokens.add(new Token(builder.toString()));
                    } else if (number) {
                        tokens.add(new Token(Integer.parseInt(builder.toString())));
                    }

                    builder = new StringBuilder();
                    symbol = false;
                    number = false;
                }
                // Intentionally doing nothing
            } else if (Character.isDigit(c)) {
                if (symbol) {
                    chyba("Cislo obashujici symbol");
                }
                builder.append(c);
                number = true;
            } else if (Character.isAlphabetic(c)) {
                if (number) {
                    chyba("Symbol obsahujici cisla");
                }
                builder.append(c);
                symbol = true;
            } else {
                if (number) {
                    tokens.add(new Token(Integer.parseInt(builder.toString())));
                    builder = new StringBuilder();
                    number = false;
                } else if (symbol) {
                    tokens.add(new Token(builder.toString()));
                    builder = new StringBuilder();
                    symbol = false;
                }

                switch (c) {
                    case '=':
                    case ',':
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '(':
                    case ')':
                    case '>':
                    case '<':
                        tokens.add(new Token(c));
                        break;

                    default:
                        chyba(String.format("Invalid operator %s", c));

                }
            }
        }

        if (builder.length() > 0) {
            if (symbol) {
                tokens.add(new Token(builder.toString()));
            } else {
                tokens.add(new Token(Integer.parseInt(builder.toString())));
            }
        }

        return tokens;
    }
}
