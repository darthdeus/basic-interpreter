package ast;

import java.util.List;

public class NodeStream {
    private final List<SyntaxNode> nodes;
    private int index = 0;

    public NodeStream(List<SyntaxNode> nodes) {
        this.nodes = nodes;
    }

    public SyntaxNode consume() throws NodeParseError {
        if (index >= nodes.size()) {
            // TODO - az od indexu
            throw new NodeParseError(String.format("chybi node v miste %s", nodes));
        } else {
            return nodes.get(index++);
        }
    }

    public SyntaxNode peek() throws NodeParseError {
        if (index >= nodes.size()) {
            throw new NodeParseError(String.format("chybi node v miste %s", nodes));
        } else {
            return nodes.get(index);
        }
    }

    public boolean peekSubEnd() {
        if (index < nodes.size()) {
            return nodes.get(index) instanceof SubEndNode;
        } else {
            return false;
        }
    }

    public boolean peekForEnd() {
        if (index < nodes.size()) {
            return nodes.get(index) instanceof ForLoopEndNode;
        } else {
            return false;
        }
    }

    public boolean peekIfEnd() {
        if (index < nodes.size()) {
            return nodes.get(index) instanceof IfEndNode;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return index >= nodes.size();
    }

    public int remaining() {
        return nodes.size() - index;
    }


    public static class NodeParseError extends Exception {
        public NodeParseError(String s) {
            super(s);
        }
    }


}
