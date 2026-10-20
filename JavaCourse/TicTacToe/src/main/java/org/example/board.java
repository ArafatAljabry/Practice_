package org.example;

import java.util.Arrays;

public class board {
    public static final int ROW = 3;
    public static final int COL = 3;
    public static final String EMPTY = "-";
    public static final String PLAYER_X = "X";
    public static final String PLAYER_O = "O";

    String[][] board;

    board(){
        initBoard();
    }

    private void initBoard(int row, int col){
        board = new String[row][col];
        for (String[] r : board) {
            Arrays.fill(r, EMPTY);
        }
    }
    private void initBoard(){
        initBoard(ROW, COL);
    }

    public void printBoard(){
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void updateBoard(int row, int col, String user) {

        if (board[row][col] == EMPTY || board[row][col] != PLAYER_X || board[row][col] != PLAYER_O) {
            board[row][col] = user;
        } else {
            System.out.println("Invalid play");
        }
    }

    public boolean checkWin(String player){
        // 1. Check rows for maximum early termination
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }

        // 2. Check columns
        for (int j = 0; j < COL; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }

        // 3. Check main diagonal (top-left to bottom-right)
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }

        // 4. Check anti-diagonal (top-right to bottom-left)
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }

        return false;
    }

}
