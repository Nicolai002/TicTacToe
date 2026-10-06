
package dk.easv.tictactoe.bll;


import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 *
 * @author EASV
 */

public class GameBoard implements IGameBoard
{
    private static int player;
    private int WinningPlayer;

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */

    public int getNextPlayer() {
        return player;
    }
    // making a method to change player
    public static void changePlayer() {
        player = (player + 1) % 2;
    }

    // making a multidimensional array with a size of 3x3
    public static int[][] arrBoard = new int[3][3];

    // method that checks the values of each button on the board
    public static void checkBoard() {
        for(int row = 0; row < arrBoard.length; row++) {
            for(int col = 0; col < arrBoard[0].length; col++) {
                System.out.print(arrBoard[row][col]+" ");
            }
            System.out.println();
        }
    }
    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        //TODO Implement this method
        return true;
    }
    public boolean isGameOver() {
        // checking all rows in the array if they are either 1 or 2
        for (int row = 0; row < arrBoard.length; row++) {
            int x = arrBoard[row][0];
            if (x == 1
                    && x == arrBoard[row][1]
                    && x == arrBoard[row][2]) {
                WinningPlayer = 1;
                return true;
            } else if (x == 2
                    && x == arrBoard[row][1]
                    && x == arrBoard[row][2]) {
                WinningPlayer = 2;
                return true;
            }
        }
        // checking all columns in the array if they are either 1 or 2
        for (int col = 0; col < arrBoard.length; col++) {
            int y = arrBoard[0][col];
            if (y == 1
                    && y == arrBoard[1][col]
                    && y == arrBoard[2][col]) {
                WinningPlayer = 1;
                return true;
            } else if (y == 2
                    && y == arrBoard[1][col]
                    && y == arrBoard[2][col]) {
                WinningPlayer = 2;
                return true;
            }
        }
        // checking diagonally if the value is 1 or 2
        if (arrBoard[0][0] == 1
                && arrBoard[0][0] == arrBoard[1][1]
                && arrBoard[0][0] == arrBoard[2][2]) {
            WinningPlayer = 1;
            return true;
            } else if (arrBoard[0][0] == 2
                && arrBoard[0][0] == arrBoard[1][1]
                && arrBoard[0][0] == arrBoard[2][2]) {
            WinningPlayer = 2;
            return true;
        // checking diagonally if the value is 1 or 2
        } else if (arrBoard[2][0] == 1
                && arrBoard[1][1] == arrBoard[0][2]
                && arrBoard[0][2] == arrBoard[2][0]) {
            WinningPlayer = 1;
            return true;
        } else if (arrBoard[2][0] == 2
                && arrBoard[1][1] == arrBoard[0][2]
                && arrBoard[0][2] == arrBoard[2][0]) {
            WinningPlayer = 2;
            return true;
        }
        if(isBoardFull() && WinningPlayer==0) {
            return true;
        }
        return false;
    }
    private boolean isBoardFull() {
        for (int[] row : arrBoard) {
            for (int cell : row) {
                if (cell == 0) {
                    return false; // found an empty cell
                }
            }
        }
        return true; // no empty cells found
    }
    public int getWinner() {
        if(WinningPlayer == 1) {
            System.out.println("Player 0 won");
            return 0;
        } else if(WinningPlayer == 2) {
            System.out.println("Player 1 won");
            return 1;
        } else {
            return -1;
        }
    }
    public void newGame()
    {
        WinningPlayer = 0;
        for (int r = 0; r < arrBoard.length; r++) {
            for (int c = 0; c < arrBoard[r].length; c++) {
                arrBoard[r][c] = 0;
            }
        }
    }
}
