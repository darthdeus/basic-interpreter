package ast;

import eval.Env;

public class IfStartNode extends SyntaxNode {
    private final ValueNode left;
    private final ValueNode right;
    private final char op;

    public IfStartNode(ValueNode left, ValueNode right, char op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String toString() {
        return "IfStartNode{" +
                "left=" + left +
                ", right=" + right +
                ", op=" + op +
                '}';
    }

    public boolean isTrue(Env env) throws EvaluationError {
        int leftVal = left.value(env);
        int rightVal = right.value(env);

        switch (op) {
            case '<':
                return leftVal < rightVal;

            case '>':
                return leftVal > rightVal;

            case '=':
                return leftVal == rightVal;
            default:
                throw new EvaluationError(String.format("Invalid operator %c", op));
        }
    }
}
