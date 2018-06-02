package com.lukazc.engine.game;

public class GameState {

    // State of the game to switch between menu and game

    public enum STATE{
        MENU,
        GAME
    }

    // Initial STATE of the game

    public static STATE State = STATE.GAME;

    public void changeState(){
        State = STATE.GAME;
    }
}
