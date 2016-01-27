package ast;

import java.util.ArrayList;
import java.util.List;

public class BlockParser {
    public List<IBlockNode> parse(NodeStream nodes) throws NodeStream.NodeParseError {
        List<IBlockNode> result = new ArrayList<>();

        while (!nodes.isEmpty()) {
            IBlockNode block = parseBlock(nodes);

            result.add(block);
        }

        return result;
    }

    private IBlockNode parseBlock(NodeStream nodes) throws NodeStream.NodeParseError, NodeStream.NodeParseError {
        if (nodes.peek() instanceof IfStartNode) {
            IfStartNode condition = (IfStartNode) nodes.consume();

            List<IBlockNode> body = new ArrayList<>();

            while (!nodes.peekIfEnd()) {
                body.add(parseBlock(nodes));
            }

            if (nodes.peekIfEnd()) {
                nodes.consume();

                return new IfBlockNode(condition, body);
            } else {
                throw new NodeStream.NodeParseError("Something is terribly wrong");
            }
        } else if (nodes.peek() instanceof ForLoopNode) {
            ForLoopNode condition = (ForLoopNode) nodes.consume();

            List<IBlockNode> body = new ArrayList<>();

            while (!nodes.peekForEnd()) {
                body.add(parseBlock(nodes));
            }

            if (nodes.peekForEnd()) {
                ForLoopEndNode forEnd = (ForLoopEndNode) nodes.consume();

                if (forEnd.string.equals(condition.varName)) {
                    return new ForBlockNode(condition, body);
                } else {
                    throw new NodeStream.NodeParseError("Mismatch between for and next var name");
                }

            } else {
                throw new NodeStream.NodeParseError("Something is terribly wrong");
            }
        } else if (nodes.peek() instanceof SubStartNode) {
            SubStartNode def = (SubStartNode) nodes.consume();

            List<IBlockNode> body = new ArrayList<>();

            while (!nodes.peekSubEnd()) {
                body.add(parseBlock(nodes));
            }

            if (nodes.peekSubEnd()) {
                nodes.consume();

                return new SubBlockNode(def.name, body);
            } else {
                throw new NodeStream.NodeParseError("Something is terribly wrong");
            }
        } else if (nodes.peek() instanceof IBlockNode) {
            return (IBlockNode) nodes.consume();
        } else {
            throw new NodeStream.NodeParseError(String.format("Node %s isn't IBlockNode", nodes.peek()));
        }
    }
}
