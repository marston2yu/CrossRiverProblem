package com.marston;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 获取输入商人和随从数
        System.out.println("Input number of merchants(>1): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        // 输入0退出
        while (n != 0) {

            // 实例化类
            Graph G = new Graph(n);
            Solver S = new Solver(G);

            S.printSolution(); // 输出解决方案

            System.out.println("Input number of merchants(>1): (0 to quit)");
            n = sc.nextInt();
        }

    }
}
