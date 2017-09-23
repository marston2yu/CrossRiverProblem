package com.marston;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solver {

    private Set<Stack<Integer>> solve; // 解决方案
    private int s; // 初始点
    private boolean[] marked; // 用于dsf的标志
    Stack<Integer> steps; // 路径

    public Solver(Graph G) {
        marked = new boolean[G.V()];
        solve = new HashSet<Stack<Integer>>();
        steps = new Stack<>();

        s = (G.V()) / 2 - 1; // 目标点

        dfs(G, s);
    }


    // 深度优先搜索
    private void dfs(Graph G, int v) {
        marked[v] = true; // 标志已搜索
        // System.out.println("trace: " + v);
        steps.add(v);

        for (int w : G.nextStep(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        marked[v] = false; // 返回时将标志清楚
        // System.out.println("end trace.");
        if (v == s + 1) {
            // 找到路径，保存
            // 保存steps的副本而不是其本身
            solve.add(copyStack(steps));
        }

        steps.pop(); // 返回同时pop一个步骤，即返回上一步
    }

    // 返回堆栈副本
    private Stack<Integer> copyStack(Stack<Integer> s) {
        Stack<Integer> stack = new Stack<>();
        for (Integer i :
                s) {
            stack.push(i);
        }
        return stack;
    }


    // 解决方案
    public Set<Stack<Integer>> getSolve() {
        return solve;
    }


    public static void main(String[] args) {
        int n = 2;
        Graph G = new Graph(n);
        Solver S = new Solver(G);

        int count = 0;
        if (S.getSolve().isEmpty()) System.out.println("No solution."); // 无解
        else { // 输出解
            for (Stack<Integer> stack :
                    S.getSolve()) {
                System.out.print("Solve " + (count++) + ": ");
                for (Integer i : stack
                        ) {
                    if (i == (n + 1) * (n + 1)) System.out.print(i);
                    else System.out.print(i + "->");
                }
                System.out.println();
            }
        }
    }
}
