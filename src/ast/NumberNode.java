package ast;

import eval.Env;

public class NumberNode extends ValueNode {
    private final int intValue;

    @Override
    public String toString() {
        return "NumberNode{" +
                "intValue=" + intValue +
                '}';
    }

    public NumberNode(int intValue) {
        super();
        this.intValue = intValue;
    }

    @Override
    public int value(Env env) throws EvaluationError {
        return intValue;
    }
}
