import java.util.List;
import java.util.Objects;

public class TokenStream {
    private List<Token> tokens;
    private int index = 0;
    public boolean inside = false;

    public TokenStream(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token peek() {
        if (tokens.size() > index) {
            return tokens.get(index);
        }
        return null;
    }

    public boolean peekType(TokenType type) {
        return tokens.size() > index && tokens.get(index).type == type;
    }

    public boolean peekOp(String options) {
        if (tokens.size() > index && tokens.get(index).type == TokenType.OP) {
            char c = tokens.get(index).value;
            for (int i = 0; i < options.length(); i++) {
                if (options.charAt(i) == c) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public boolean peekSymbol(String symbol) {
        if (tokens.size() > index && tokens.get(index).type == TokenType.SYMBOL) {
            return Objects.equals(tokens.get(index).string, symbol);
        } else {
            return false;
        }
    }

    public boolean pepeekEquals() {
        if (tokens.size() - 1 > index) {
            return tokens.get(index + 1).value == '=';
        } else {
            return false;
        }
    }

    public Token consume() throws ParseError {
        if (index >= tokens.size()) {
            throw new ParseError(String.format("chybi token v radce %s", tokens));
        }
        return tokens.get(index++);
    }

    public int number() {
        if (tokens.size() > index && tokens.get(index).type == TokenType.NUMBER) {
            return tokens.get(index).intValue;
        } else {
            throw new RuntimeException();
        }
    }

    public int remaining() {
        return tokens.size() - index;
    }

    public boolean isEmpty() {
        return index >= tokens.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("TokenStream{ ");

        for (int i = index; i < tokens.size(); i++) {
            builder.append(tokens.get(i).toString());
            builder.append(", ");
        }

        builder.append(" }");

        return builder.toString();
    }
}
