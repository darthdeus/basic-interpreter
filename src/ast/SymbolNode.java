package ast;

import eval.Env;

public class SymbolNode extends ValueNode {
    private final String name;

    public SymbolNode(String string) {
        this.name = string;
    }

    @Override
    public String toString() {
        return "SymbolNode{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int value(Env env) throws EvaluationError {
        if (env.vars.containsKey(name)) {
            return env.vars.get(name);
        } else {
            throw new EvaluationError(String.format("Undefined varaible name %s", name));
        }
    }
}
