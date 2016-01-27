import ast.*;
import eval.Env;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("INPUT"))) {

            String line;

            Tokenizer tokenizer = new Tokenizer();

            List<SyntaxNode> lineNodes = new ArrayList<>();

            while ((line = reader.readLine()) != null) {

                List<Token> tokens = tokenizer.tokenize(new BufferedReader(new StringReader(line)));

                if (tokens.isEmpty()) {
                    // Preskocime prazdne radky
                    continue;
                }

                TokenStream tokenStream = new TokenStream(tokens);

                SyntaxNode lineNode = new LineParser().parse(tokenStream);
                lineNodes.add(lineNode);
            }


            NodeStream nodeStream = new NodeStream(lineNodes);

            List<IBlockNode> blocks = new BlockParser().parse(nodeStream);

            Env globalEnv = new Env();

            for (IBlockNode block : blocks) {
                block.evaluate(globalEnv);
            }

            for (SyntaxNode node : lineNodes) {
                System.out.println(node);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseError parseError) {
            parseError.printStackTrace();
        } catch (NodeStream.NodeParseError nodeParseError) {
            nodeParseError.printStackTrace();
        } catch (EvaluationError evaluationError) {
            evaluationError.printStackTrace();
        }
    }
}
