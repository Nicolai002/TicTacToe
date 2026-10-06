
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;

public class TicTacViewController implements Initializable
{
    @FXML
    private Label lblPlayer;
    @FXML
    private Button btnNewGame;
    @FXML
    private GridPane gridPane;
    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;

    @FXML   // method/event handler that runs when a button/cell in the array is clicked
    private void handleButtonAction(ActionEvent event) {
        try {
            /*
            figuring out which cell (in the array) was clicked. event.getSource returns which button was clicked.
            getRow/ColumnIndex returns its position in the array.
            they return as wrapper types Integer (not int) because JavaFX by default returns null when a node is in row/column 0 and not defined yet
            and an int cannot be null
             */
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            /*
            if row is null (doesn't have a value), set it to 0 (indicated by "?")
            otherwise set it to row (keep it as is, but as an int) (indicated by ":")
            basically checking if the wrapper type is null, and converting it into an int (since an int cannot be null)
             */
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();

            if(game.isGameOver()==false) {
                Button btn = (Button) event.getSource();
                String xOrO = player == 0 ? "X" : "O";
                btn.setText(xOrO);
                // calling the method from gameboard to change to the next players turn
                GameBoard.changePlayer();
                setPlayer();
                // sets the value of the cell the player has clicked on [r][c] as either 1 (if player = 0) or 2 (if player = 1)
                GameBoard.arrBoard[r][c] = player + 1;
                System.out.println("Player turn: " + player);
                GameBoard.checkBoard();
            }
            if (game.play(c, r)) {
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer();
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}
