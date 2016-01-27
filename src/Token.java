
public class Token {
    public String string;
    public TokenType type;
    public int intValue;
    public char value;

    public Token(char value) {
        this.type = TokenType.OP;
        this.value = value;
    }

    public Token(int doubleValue) {
        this.type = TokenType.NUMBER;
        this.intValue = doubleValue;
    }

    public Token(String value) {
        this.type = TokenType.SYMBOL;
        this.string = value;
    }

    @Override
    public String toString() {
        if (type == TokenType.NUMBER) {
            return String.format("NUMBER(%d)", intValue);
        } else if (type == TokenType.SYMBOL) {
            return String.format("SYMBOL(%s)", string);
        } else {
            return String.format("OP(%c)", value);
        }
    }
}
