package ast;

import eval.Env;

public class LetNode extends SyntaxNode implements IBlockNode {
    private String name;
    private final boolean singleValue;

    private ValueNode value;

    private ValueNode left;
    private char op;
    private ValueNode right;

    public LetNode(String name, ValueNode value) {
        this.name = name;
        this.value = value;
        this.singleValue = true;
    }

    @Override
    public String toString() {
        if (singleValue) {
            return "LeftNode{ " + name + " = " + value + " }";
        } else {
            return "LeftNode{ " + name + " = " + left + " " + op + " " + right + " }";
        }
    }

    public LetNode(String name, ValueNode left, char op, ValueNode right) {
        this.name = name;
        this.left = left;
        this.op = op;
        this.right = right;
        this.singleValue = false;
    }

    @Override
    public void evaluate(Env env) throws EvaluationError {
        if (singleValue) {
            env.vars.put(name, value.value(env));
        } else {
            int leftVal = left.value(env);
            int rightVal = right.value(env);
            int result = 0;

            switch (op) {
                case '+':
                    result = leftVal + rightVal; break;
                case '-':
                    result = leftVal - rightVal; break;
                case '*':
                    result = leftVal * rightVal; break;
                case '/':
                    result = leftVal / rightVal; break;
                default:
                    throw new EvaluationError(String.format("Invalid operator %c", op));
            }

            env.vars.put(name, result);
        }
    }
}
