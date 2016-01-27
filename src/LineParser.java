import ast.*;

public class LineParser {
    public SyntaxNode parse(TokenStream tokens) throws ParseError {
        if (tokens.peekSymbol("sub")) {
            // Zacatek sub
            tokens.consume();

            if (tokens.peekType(TokenType.SYMBOL)) {
                String name = tokens.consume().string;

                if (tokens.isEmpty()) {
                    return new SubStartNode(name);
                } else {
                    extraToken("sub", tokens.consume());
                }
            } else {
                throw new ParseError("Ocekavan nazev funkce");
            }
        } else if (tokens.peekSymbol("endsub")) {
            // Konec sub
            tokens.consume();

            if (tokens.isEmpty()) {
                return new SubEndNode();
            } else {
                extraToken("endsub", tokens.consume());
            }
        } else if (tokens.peekSymbol("if")) {
            // Zacatek if
            tokens.consume();

            ValueNode left = parseValue(tokens.consume());
            if (tokens.peekType(TokenType.OP)) {
                char op = tokens.consume().value;

                ValueNode right = parseValue(tokens.consume());
                return new IfStartNode(left, right, op);
            } else {
                throw new ParseError(String.format("Invalid token %s, expecting op", tokens.consume()));
            }
        } else if (tokens.peekSymbol("endif")) {
            // Konec if
            tokens.consume();

            if (tokens.isEmpty()) {
                return new IfEndNode();
            } else {
                extraToken("endif", tokens.consume());
            }
        } else if (tokens.peekSymbol("for")) {
            // For loop
            tokens.consume();

            if (tokens.peekType(TokenType.SYMBOL)) {
                String varName = tokens.consume().string;

                if (tokens.peekOp("=")) {
                    tokens.consume();

                    ValueNode startValue = parseValue(tokens.consume());
                    if (tokens.peekSymbol("to")) {
                        tokens.consume();
                        ValueNode endValue = parseValue(tokens.consume());

                        if (tokens.isEmpty()) {
                            return new ForLoopNode(varName, startValue, endValue);
                        } else {
                            extraToken("for", tokens.consume());
                        }
                    } else {
                        throw new ParseError(String.format("Unexpected token %s, expecting 'to'", tokens.consume()));
                    }
                } else {
                    throw new ParseError(String.format("Expecting =, got %s", tokens.consume()));
                }
            }
        } else if (tokens.peekSymbol("next")) {
            // End for with next
            tokens.consume();

            if (tokens.peekType(TokenType.SYMBOL)) {
                return new ForLoopEndNode(tokens.consume().string);
            } else {
                throw new ParseError(String.format("Unexpected token %s, expecting variable name", tokens.consume()));
            }
        } else if (tokens.peekSymbol("print")) {
            // Print call
            tokens.consume();

            ValueNode value = parseValue(tokens.consume());

            return new PrintNode(value);
        } else if (tokens.peekType(TokenType.SYMBOL) && tokens.remaining() == 1) {
            // Subroutine call
            return new SubCallNode(tokens.consume().string);
        } else if (tokens.peekType(TokenType.SYMBOL)) {
            // Let assignment or regular assignment

            if (tokens.peekSymbol("let")) {
                tokens.consume();

                if (!tokens.peekType(TokenType.SYMBOL)) {
                    throw new ParseError(String.format("Expecting variable name, got %s", tokens.consume()));
                }
            }

            String name = tokens.consume().string;

            if (tokens.peekOp("=")) {
                tokens.consume();

                if (tokens.remaining() == 1) {
                    ValueNode value = parseValue(tokens.consume());

                    return new LetNode(name, value);
                } else if (tokens.remaining() == 3) {
                    ValueNode left = parseValue(tokens.consume());

                    if (tokens.peekOp("+-*/")) {
                        char op = tokens.consume().value;

                        ValueNode right = parseValue(tokens.consume());

                        return new LetNode(name, left, op, right);
                    } else {
                        throw new ParseError(String.format("Unexpected operator %s, expecting +-*/", tokens.consume()));

                    }
                }
            } else {
                throw new ParseError(String.format("Unexpected token %s, expecting =", tokens.consume()));
            }
        } else {
            throw new ParseError(String.format("Unexpected token %s, expecting variable name", tokens.consume()));
        }

        throw new ParseError(String.format("Invalid token %s, didn't match any of the clauses", tokens.consume()));
    }

    private ValueNode parseValue(Token token) throws ParseError {
        if (token.type == TokenType.SYMBOL) {
            return new SymbolNode(token.string);
        } else if (token.type == TokenType.NUMBER) {
            return new NumberNode(token.intValue);
        } else {
            throw new ParseError(String.format("Invalid token %s", token.toString()));
        }
    }

    private void extraToken(String target, Token token) throws ParseError {
        throw new ParseError(String.format("Token navic za %s %s", target, token.toString()));
    }
}
