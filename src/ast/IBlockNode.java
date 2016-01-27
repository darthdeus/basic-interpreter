package ast;

import eval.Env;

public interface IBlockNode {
    void evaluate(Env env) throws EvaluationError;

}
