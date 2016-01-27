import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TokenStreamTest {

    @Test
    public void testPeekType() throws Exception {

    }

    @Test
    public void testPeekOp() throws Exception {

    }

    @Test
    public void testPeekSymbol() throws Exception {

        ArrayList<Token> list = new ArrayList<>();
        list.add(new Token("foo"));
        list.add(new Token('='));
        list.add(new Token(3));


        TokenStream tokens = new TokenStream(list);

        assertEquals(tokens.remaining(), 3);

        assertTrue(tokens.peekSymbol("foo"));
        tokens.consume();
        assertFalse(tokens.peekSymbol("="));
        assertTrue(tokens.peekOp("="));
        tokens.consume();
        assertFalse(tokens.peekSymbol("3"));
        assertFalse(tokens.peekOp("3"));
        assertTrue(tokens.peekType(TokenType.NUMBER));
        tokens.consume();

        assertFalse(tokens.peekType(TokenType.NUMBER));
        assertFalse(tokens.peekSymbol("3"));
        assertFalse(tokens.peekOp("3"));

    }
}