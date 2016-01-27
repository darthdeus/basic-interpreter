package ast;

import eval.Env;

public class PrintNode extends SyntaxNode implements IBlockNode {
    public final ValueNode value;

    @Override
    public String toString() {
        return "PrintNode{" +
                "value=" + value +
                '}';
    }

    public PrintNode(ValueNode value) {
        this.value = value;
    }

    @Override
    public void evaluate(Env env) throws EvaluationError {
        System.out.println(value.value(env));
    }
}
