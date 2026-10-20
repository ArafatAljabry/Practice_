package org.example;

import java.util.Scanner;

public class Game {
    board b;
    Scanner scanner;
    boolean gameIsRunning = true;


    Game(){
        b = new board();
        b.printBoard();
        scanner = new Scanner(System.in);
    }

    public void play(){
        int inputRow;
        int inputColumn;

        while(gameIsRunning)
        {
            System.out.print("Enter row: ");
            inputRow = getValidInt();
            System.out.print("Enter column: ");
            inputColumn = getValidInt();

            b.updateBoard(inputRow, inputColumn, board.PLAYER_X);
            opps();
            b.printBoard();
            isGameOver();
        }
    }

    public void isGameOver(){
        if(b.checkWin(b.PLAYER_X)){
            System.out.println("You win!");
            gameIsRunning = false;
        }

        if(b.checkWin(b.PLAYER_O)){
            System.out.println("GAME OVER You lose!");
            System.out.println("You lose!");
            gameIsRunning = false;
        }

    }

    private int getValidInt(){
        while(true){
            if(!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
                continue;
            }

            int input = scanner.nextInt();

            if(!isInValidRange(input)){
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }
            return input;
        }
    }

    private boolean isInValidRange(int input){
        return input < 3 && input >= 0;
    }

    private void opps(){
        int aiRow = getRandomNumber(0,3);
        int aiCol = getRandomNumber(0,3);
        while(!isSpaceUnused(aiRow, aiCol))
        {
             aiRow = getRandomNumber(0,3);
             aiCol = getRandomNumber(0,3);
        }

        b.updateBoard(aiRow, aiCol, board.PLAYER_O);
    }
    private boolean isSpaceUnused(int row, int col){
        return  b.board[row][col] == b.EMPTY;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
