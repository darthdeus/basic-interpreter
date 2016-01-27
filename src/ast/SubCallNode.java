package ast;

import eval.Env;

public class SubCallNode extends SyntaxNode implements IBlockNode {
    private final String string;

    @Override
    public String toString() {
        return "SubCallNode{" +
                "string='" + string + '\'' +
                '}';
    }

    public SubCallNode(String string) {
        this.string = string;
    }

    @Override
    public void evaluate(Env env) throws EvaluationError {
        SubBlockNode sub = env.subs.get(string);

        if (env.depth > 100) {
            throw new EvaluationError("Stack overflow at " + string);
        }

        env.depth++;

        for (IBlockNode node : sub.body) {
            node.evaluate(env);
        }

        env.depth--;
    }
}
