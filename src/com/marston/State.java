package com.marston;

import java.util.ArrayList;

public class State {
    private int merchant;
    private int servant;
    private boolean boat; // 表示该状态有没有船

    public State(int merchant, int servant, boolean boat) {
        this.merchant = merchant;
        this.servant = servant;
        this.boat = boat;
    }

    public int getMerchant() {
        return merchant;
    }

    public int getServant() {
        return servant;
    }

    public boolean hasBoat() {
        return boat;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;
        State state = (State) obj;
        return (state.getMerchant() == this.getMerchant() && state.getServant() == this.getServant());
    }

    @Override
    public int hashCode() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(merchant);
        list.add(servant);
        list.add(boat);
        return list.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + merchant + ", " + servant + ", " + boat + ")");
        return sb.toString();
    }

    public static void main(String[] args) {
        State[] allState = new State[32];
        for (int i = 0; i < 16; i++) {
            allState[i] = new State(i/4, i%4, false);
            allState[i+16] = new State(i/4, i%4, true);
        }

        // print all state and test hashcode
        for (State state :
                allState) {
            System.out.print(state + " ");
            System.out.println(state.hashCode());
        }

        // test equal
        System.out.println("false:" + allState[1].equals(allState[2]));
        System.out.println("true:" + allState[1].equals(allState[1]));

        // hash
    }
}
