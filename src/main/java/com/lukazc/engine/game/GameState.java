package com.lukazc.engine.game;

public class GameState {

    // State of the game to switch between menu and game

    public enum STATE {
        MENU,
        GAME,
    }

    // Initial STATE of the game

    public static STATE State = STATE.MENU;

    // Getter for State

    public STATE getState(){
        return State;
    }

    // Setter for State

    public static void setState(STATE state){
        State = state;
    }
}