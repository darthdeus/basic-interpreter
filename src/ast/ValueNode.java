package ast;

import eval.Env;

public abstract class ValueNode {
    public abstract int value(Env env) throws EvaluationError;
}
