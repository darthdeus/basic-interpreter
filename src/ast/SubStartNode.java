package ast;

public class SubStartNode extends SyntaxNode {
    @Override
    public String toString() {
        return "SubStartNode{" +
                "name='" + name + '\'' +
                '}';
    }

    public final String name;

    public SubStartNode(String name) {
        this.name = name;
    }
}
