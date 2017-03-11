package com.glt.tictac.utils;

/**
 * Created by gltrager on 3/1/17.
 */

public class BoardUtils {

    public static void printBoard(int[][] board){
        for(int i = 0; i< board.length; i++){
            for (int j = 0; j <board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
