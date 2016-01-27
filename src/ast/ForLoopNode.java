package ast;

public class ForLoopNode extends SyntaxNode {
    public final String varName;
    public final ValueNode startValue;

    @Override
    public String toString() {
        return "ForLoopNode{" +
                "varName='" + varName + '\'' +
                ", startValue=" + startValue +
                ", endValue=" + endValue +
                '}';
    }

    public final ValueNode endValue;

    public ForLoopNode(String varName, ValueNode startValue, ValueNode endValue) {
        this.varName = varName;
        this.startValue = startValue;
        this.endValue = endValue;
    }
}
