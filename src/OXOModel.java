import java.util.*;

/**
 * Please note:
 *  The idea of using a dynamic number of players and board (OXOModel) was
 *  inspired by the following three sources:
 *
 *  [1] https://stackoverflow.com/questions/38948232/can-you-create-an-instance-of-an-object-dynamically-in-java
 *  [2] https://stackoverflow.com/questions/27739227/how-to-generate-dynamic-arraylist-in-java
 *  [3] http://people.scs.carleton.ca/~lanthier/teaching/COMP1406/Tutorials/Tutorial3/Code/GameBoard.java
 */

class OXOModel
{
    private OXOPlayer currentPlayer;
    private OXOPlayer winner;
    private ArrayList<ArrayList<OXOPlayer>> boardCells;
    private ArrayList<OXOPlayer> dynamicPlayers;
    private boolean gameDrawn;
    private int winThreshold;

    /**
     * ArrayLists enable board to change size dynamically.
     */
    public OXOModel(int rows, int cols, int winThresh) {
        winThreshold = winThresh;
        boardCells = new ArrayList<ArrayList<OXOPlayer>>();
        for (int i=0; i < rows; i++) {
            ArrayList<OXOPlayer> newRow = new ArrayList<OXOPlayer>(cols);
            for (int j=0; j < cols; j++) {
                newRow.add(null);
            }
            boardCells.add(newRow);
        }
        dynamicPlayers = new ArrayList<OXOPlayer>();
    }

    public int getNumberOfPlayers() {
        int numberOfPlayers = dynamicPlayers.size();
        return numberOfPlayers;
    }

    public void addPlayer(OXOPlayer player) {
        dynamicPlayers.add(player);
    }

    public OXOPlayer getPlayerByNumber(int playerNumber) {
        return dynamicPlayers.get(playerNumber);
    }

    public OXOPlayer getWinner() {
        return winner;
    }

    public void setWinner(OXOPlayer player) {
        winner = player;
    }

    public OXOPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(OXOPlayer player) {
        currentPlayer = player;
    }

    public int getNumberOfRows() {
        return boardCells.size();
    }

    /**
     * Scan and count columns
     *
     */
    public int getNumberOfColumns() {
        int j;
        int rows = boardCells.size();
        int count = 0;

        for (j=0; j < rows; j++) {
            if (boardCells.get(j).size() > count) {
                count = boardCells.get(j).size();
            }
        }
        return count;
    }

    /**
     * Locate specific row.
     */
    public ArrayList<OXOPlayer> locateSpecificRow(int rows) {
        return boardCells.get(rows);
    }

    public OXOPlayer getCellOwner(int rows, int cols) {
        return boardCells.get(rows).get(cols);
    }

    public void setCellOwner(int rows, int cols, OXOPlayer player) {
        boardCells.get(rows).set(cols, player);
    }

    public void setWinThreshold(int winThresh) {
        winThreshold = winThresh;
    }

    public int getWinThreshold() {
        return winThreshold;
    }

    public void setGameDrawn() {
        gameDrawn = true;
    }

    public boolean isGameDrawn() {
        return gameDrawn;
    }

}