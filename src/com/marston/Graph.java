package com.marston;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    int n;
    private boolean[][][] badState; // 商人数目少于随从的情况
    private Set<Integer>[] nextStep;// 下一步可能的位置

    public Graph(int n) {

        this.n = n;
        badState = new boolean[2][n + 1][n + 1];
        nextStep = (Set<Integer>[]) new HashSet[2 * (n + 1) * (n + 1)];
        for (int i = 0; i < nextStep.length; i++) {
            nextStep[i] = new HashSet<>();
        }


        searchBadState();
        searchStep();
    }


    public int V() {
        return 2 * (n + 1) * (n + 1);
    }

    ;

    // 搜索所有坏点
    private void searchBadState() {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                State s1 = new State(i, j, false);
                State s2 = new State(i, j, true);

                // 标记商人数目少于随从的情况
                if ((i > 0 && j > i) || (i < n && j < i)) {
                    badState[0][i][j] = true;
                    badState[1][i][j] = true;
                }
            }
        }
    }

    // 添加可逆步
    private void addStep(int v, int w) {
        nextStep[v].add(w);
        nextStep[w].add(v);
    }

    // 搜索所有步骤
    private void searchStep() {
        for (int i = 0; i < 2 * (n + 1) * (n + 1); i++) {
            if (i < (n + 1) * (n + 1)) {
                int x = i / (n + 1), y = i % (n + 1); // 有船情况下转化为平面坐标

                if (badState[1][x][y]) continue; // 该状态是无效状态，跳过此次循环

                if (x >= 1 && !badState[1][x - 1][y]) addStep(i, i + (n + 1) * n);
                if (x >= 2 && !badState[1][x - 2][y]) addStep(i, i + (n + 1) * (n - 1));
                if (y >= 1 && !badState[1][x][y - 1]) addStep(i, i + (n + 1) * (n + 1) - 1);
                if (y >= 2 && !badState[1][x][y - 2]) addStep(i, i + (n + 1) * (n + 1) - 2);
                if (x >= 1 && y >= 1 && !badState[1][x - 1][y - 1])
                    addStep(i, i + (n + 1) * (n + 1) - (n + 1) - 1);
            } else {
                int x = (i - (n + 1) * (n + 1)) / (n + 1), y = (i - (n + 1) * (n + 1)) % (n + 1); // 无船情况下转化为平面坐标

                if (badState[0][x][y]) continue; // 该状态是无效状态，跳过此次循环

                if (x <= n - 1 && !badState[1][x + 1][y]) addStep(i, i - (n + 1) * n);
                if (x <= n - 2 && !badState[1][x + 2][y]) addStep(i, i - (n + 1) * (n - 1));
                if (y <= n - 1 && !badState[1][x][y + 1]) addStep(i, i - (n + 1) * (n + 1) + 1);
                if (y <= n - 2 && !badState[1][x][y + 2]) addStep(i, i - (n + 1) * (n + 1) + 2);
                if (x <= n - 1 && y <= 1 && !badState[1][x + 1][y + 1])
                    addStep(i, i - (n + 1) * (n + 1) + (n + 1) + 1);
            }
        }

        // 手动将目标点的下一步清零
        nextStep[(n + 1) * (n + 1)] = new HashSet<>();
    }

    public Iterable<Integer> nextStep(int v) {
        return nextStep[v];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(3);
        for (int i = 0; i < 2 * 4 * 4; i++) {
            System.out.print("next to " + i + ": ");
            for (Integer s :
                    graph.nextStep(i)) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
