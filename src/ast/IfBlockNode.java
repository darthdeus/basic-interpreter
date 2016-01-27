package ast;

import eval.Env;

import java.util.List;

public class IfBlockNode implements IBlockNode {
    private final IfStartNode condition;
    private final List<IBlockNode> body;

    public IfBlockNode(IfStartNode condition, List<IBlockNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void evaluate(Env env) throws EvaluationError {
        if (condition.isTrue(env)) {
            for (IBlockNode node : body) {
                node.evaluate(env);
            }
        }
    }
}
