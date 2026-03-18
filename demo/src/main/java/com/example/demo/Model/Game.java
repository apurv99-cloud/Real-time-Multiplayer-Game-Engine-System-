package com.example.demo.Model;

import java.util.Arrays;

public class Game {
    public char[][] board = new char[3][3];
    public char currentTurn = 'X';
    public String playerX;
    public String playerO;

    public boolean gameOver = false;
    public String winner = null;

    public Game() {
        for (char[] row : board) {
            Arrays.fill(row, '\0');
        }
    }

    // Join Player
    public String addPlayer(String name) {
        if (playerX == null) {
            playerX = name;
            return "Assigned X";
        } else if (playerO == null) {
            playerO = name;
            return "Assigned O";
        } else {
            return "Room Full";
        }
    }

    public String makeMove(int row, int col, char player) {

        if (gameOver)
            return "Game already finished";

        if (board[row][col] != '\0')
            return "Cell already filled";

        if (player != currentTurn)
            return "Not your turn";

        board[row][col] = player;

        // check winner
        if (checkWin(player)) {
            gameOver = true;
            winner = String.valueOf(player);
            return "Player " + player + " wins!";
        }

        // check draw
        if (isBoardFull()) {
            gameOver = true;
            return "Draw!";
        }

        // switch turn
        currentTurn = (player == 'X') ? 'O' : 'X';

        return "Move successful";
    }

    private boolean checkWin(char p) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p)
                return true;
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p)
                return true;
        }
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p)
            return true;
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p)
            return true;
        return false;

    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '\0')
                    return false;
            }
        }
        return true;
    }

}
