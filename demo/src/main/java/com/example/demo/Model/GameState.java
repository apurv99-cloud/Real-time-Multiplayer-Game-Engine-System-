package com.example.demo.Model;

public class GameState {
    public char[][] board;
    public char currentTurn;
    public String message;
    public String winner;

    public GameState(char[][] board, char currentTurn, String message, String winner) {
        this.board = board;
        this.currentTurn = currentTurn;
        this.message = message;
        this.winner = winner;
    }

}
