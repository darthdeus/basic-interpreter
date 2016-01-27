package eval;

import ast.SubBlockNode;

import java.util.HashMap;
import java.util.Map;

public class Env {
    public int depth = 0;
    public Map<String, SubBlockNode> subs = new HashMap<>();
    public Map<String, Integer> vars = new HashMap<>();
}
