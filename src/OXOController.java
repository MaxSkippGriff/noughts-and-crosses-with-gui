import OXOExceptions.*;

/**
 *  This OXO Controller is capable of the following:
 *   > Handling different board sizes
 *   > Scaling to include multiple players
 *   > Changing different win thresholds
*/

class OXOController {

    private OXOModel model;
    private int row;
    private int col;
    private boolean checkWinner = false;
    private boolean checkDraw = false;
    private int swapPlayers = 0;


    public OXOController(OXOModel model) {
        this.model = model;
        swapPlayers = 0;
        this.model.setCurrentPlayer(model.getPlayerByNumber(swapPlayers));
    }

    /**
     *  Handle incoming commands and check for errors.
     */
    public void handleIncomingCommand(String command) throws OXOMoveException {
        if ((!checkWinner) && (!checkDraw)) {
            command = command.toLowerCase();
            if (!(checkLength(command))) {
                if (!(checkInput(command))) {
                    throw new InvalidIdentifierException("input", command);
                }
            }
            if (!(checkLength(command))) {
                if (!(checkInput(command))) {
                    throw new InvalidIdentifierCharacterException("input", command);
                }
            }
            if (!(checkLength(command))) {
                if (!(checkInput(command))) {
                    throw new InvalidIdentifierLengthException("input", command);
                }
            }
            handleCoordinates(command);
        }
    }

    /**
     *  Obtain coordinates and check
     *  cells are within bounds.
     */
    public void handleCoordinates(String command) throws OXOMoveException {
        // Obtain coordinates
        int row = command.charAt(0) - 'a';
        int col = command.charAt(1) - '1';

        // Let's check whether cell is within bounds
        if (!(row >= 0 && row < model.getNumberOfRows() &&
                col >= 0 && col < model.getNumberOfColumns())) {
            throw new CellDoesNotExistException(row, col);
        }
        if (!(row >= 0 && row < model.getNumberOfRows() &&
                col >= 0 && col < model.getNumberOfColumns())) {
            throw new OutsideCellRangeException(row, col);
        }
        // Checks whether cell selected has been taken
        if (model.getCellOwner(row, col) != null) {
            throw new CellAlreadyTakenException(row, col);
        }
        model.setCellOwner(row, col, model.getCurrentPlayer());
        // Check for win or draw
        checkResult(row, col);
    }

    /**
     * Check command length is valid.
     *
     */
    private boolean checkLength(String command) {
        if (command.length() > 2 || command.length() < 2) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Check identifiers are valid.
     *
     */
    private boolean checkInput(String command) {
        if (!(Character.isLetter(command.charAt(0)))) {
            return false;
        }
        if (!(Character.isDigit(command.charAt(1)))) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Check for win or draw.
     */
    private void checkResult(int row, int col) {
        if (winDetection(row, col)) {
            model.setWinner(model.getCurrentPlayer());
            model.getWinner();
            checkWinner = true;
        }
        if (checkForADraw()) {
            model.setGameDrawn();
            checkDraw = true;
        }
        swapPlayer();
    }

    /**
     * Check rows, columns and diagonals
     */
    private boolean winDetection(int row, int col) {
        int checkCells;
        int isTheBoardASquareOrRectangle = Math.min(model.getNumberOfColumns(),model.getNumberOfRows());
        int winThreshold = model.getWinThreshold();
        int rowNumber = model.getNumberOfRows();
        int colNumber = model.getNumberOfColumns();
        // Check rows
        checkCells = 0;
        for (int i = 0; i < rowNumber; i++) {
            if (checkRow(i, row)) {
                ++checkCells;
                if (checkCells == winThreshold) {
                    return true;
                }
            }
            else {
                checkCells = 0;
            }
        }
        // Check columns for matches
        checkCells = 0;
        for (int j = 0; j < colNumber; j++) {
            if (checkCol(j, col)) {
                ++checkCells;
                if (checkCells == winThreshold) {
                    return true;
                }
            }
            else {
                checkCells = 0;
            }
        }
        // Check main diagonal
        checkCells = 0;
        if (col == row) {
            for (int i = 0; i < isTheBoardASquareOrRectangle; i++) {
                if (checkMain(i)) {
                    ++checkCells;
                    if (checkCells == winThreshold) {
                        return true;
                    }
                }
                else {
                    checkCells = 0;
                }
            }
        }
        // Check min diagonal
        checkCells = 0;
        if (col+row == isTheBoardASquareOrRectangle-1) {
            for (int i = 0; i < isTheBoardASquareOrRectangle; i++) {
                if (checkMin(i, isTheBoardASquareOrRectangle)) {
                    ++checkCells;
                    if (checkCells == winThreshold) {
                        return true;
                    }
                }
                else {
                    checkCells = 0;
                }
            }
        }
        return false;
    }

    /**
     * Methods check for matches.
     *
     */
    private boolean checkRow(int i, int row) {
        if (model.locateSpecificRow(row).get(i) == model.getCurrentPlayer()) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkCol(int j, int col) {
        if (model.locateSpecificRow(j).get(col) == model.getCurrentPlayer()) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkMain(int i) {
        if (model.locateSpecificRow(i).get(i) == model.getCurrentPlayer()) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkMin(int i, int isTheBoardASquareOrRectangle) {
        if (model.locateSpecificRow(i).get((isTheBoardASquareOrRectangle - 1) - i)
                == model.getCurrentPlayer()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Check for draw.
     *
     */
    private boolean checkForADraw() {
        int rows = model.getNumberOfRows();
        int cols = model.getNumberOfColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (model.getCellOwner(i, j) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Swap players to allow turn taking.
     *
     */
    private void swapPlayer() {
        if (model.getCurrentPlayer() !=
                model.getPlayerByNumber(model.getNumberOfPlayers() - 1)) {
            swapPlayers++;
        }
        else {
            swapPlayers = 0;
        }
        model.setCurrentPlayer(model.getPlayerByNumber(swapPlayers));
    }
}
