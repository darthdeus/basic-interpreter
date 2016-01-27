package ast;

import eval.Env;

import java.util.List;

public class SubBlockNode implements IBlockNode {
    public final String name;
    public final List<IBlockNode> body;

    public SubBlockNode(String name, List<IBlockNode> body) {
        this.name = name;
        this.body = body;
    }

    @Override
    public void evaluate(Env env) {
        env.subs.put(name, this);
    }
}
