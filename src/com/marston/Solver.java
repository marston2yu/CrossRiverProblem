package com.marston;

import java.util.HashSet;

public class Solver {

    private HashSet badState; // 商人数目少于随从的情况
    public Solver(int n) {

        badState = new HashSet();

        State[] allState = new State[2*(n+1)*(n+1)];
        for (int i = 0; i < n+1; i++) {
            allState[i] = new State(i/4, i%4, false);
            allState[i+(n+1)*(n+1)] = new State(i/4, i%4, true);
        }

        for (State state:
             allState) {
            if ((state.getMerchant() > 0 && state.getServant() > state.getMerchant())
                    || (state.getMerchant() < 3 && state.getServant() < state.getMerchant()))
                badState.add(state);
        }
    }
}
