package ast;

public class EvaluationError extends Throwable {
    public EvaluationError(String msg) {
        super(msg);
    }
}
