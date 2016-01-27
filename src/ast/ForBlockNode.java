package ast;

import eval.Env;

import java.util.List;

public class ForBlockNode implements IBlockNode {
    private final ForLoopNode condition;
    private final List<IBlockNode> body;

    public ForBlockNode(ForLoopNode condition, List<IBlockNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void evaluate(Env env) throws EvaluationError {
        String var = condition.varName;

        int from = condition.startValue.value(env);
        int to = condition.endValue.value(env);


        for (int i = from; i <= to; i++) {
            env.vars.put(var, i);

            for (IBlockNode node : body) {
                node.evaluate(env);
            }
        }
    }
}
