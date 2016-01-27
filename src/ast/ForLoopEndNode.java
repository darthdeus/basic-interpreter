package ast;

public class ForLoopEndNode extends SyntaxNode {
    public final String string;

    public ForLoopEndNode(String string) {
        super();
        this.string = string;
    }

    @Override
    public String toString() {
        return "ForLoopEndNode{" +
                "string='" + string + '\'' +
                '}';
    }
}
