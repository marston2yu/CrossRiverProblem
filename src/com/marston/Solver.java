package com.marston;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solver {

    private Set<Stack<Integer>> solve; // 解决方案
    private int s; // 初始点
    private boolean[] marked;
    Stack<Integer> states;

    public Solver(Graph G) {
        marked = new boolean[G.V()];
        solve = new HashSet<Stack<Integer>>();
        states = new Stack<>();
        s = (G.V()) / 2 - 1;

        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        System.out.println("trace: " + v);
        states.add(v);

        for (int w : G.nextStep(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        marked[v] = false;
        System.out.println("end trace.");
        if (v == s + 1) solve.add(states);
        states = new Stack<>();
    }

    public Set<Stack<Integer>> getSolve() {
        return solve;
    }




    public static void main(String[] args) {
        Graph G = new Graph(3);
        Solver S = new Solver(G);

        int count = 0;
        for (Stack<Integer> stack :
                S.getSolve()) {
            System.out.print("Solve " + (count++) + ": ");
            for (Integer i : stack
                    ) {
                System.out.print(i + "->");
            }
            System.out.println();
        }
    }
}
