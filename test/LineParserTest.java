import ast.LetNode;
import ast.SyntaxNode;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

public class LineParserTest {

    @Test
    public void testParse() throws Exception {
        SyntaxNode node = new LineParser().parse(tokenStream("let a = 3"));

        assertTrue(node instanceof LetNode);
    }

    public TokenStream tokenStream(String str) throws IOException, ParseError {
        BufferedReader buf = new BufferedReader(new StringReader(str));
        List<Token> tokens = new Tokenizer().tokenize(buf);
        return new TokenStream(tokens);
    }

}