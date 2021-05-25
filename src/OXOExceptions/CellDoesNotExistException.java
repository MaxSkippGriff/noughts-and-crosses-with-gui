package OXOExceptions;

public class CellDoesNotExistException extends OXOMoveException
{
    private int rowNumber;
    private int columnNumber;


    public CellDoesNotExistException(int row, int col)
    {
        rowNumber = row;
        columnNumber = col;
    }

    protected int getRow()
    {
        return rowNumber;
    }

    protected int getColumn()
    {
        return columnNumber;
    }

    public CellDoesNotExistException() {
        super.toString();
    }

    public String toString()
    {
        return this.getClass().getName() + ": Error: the cell index [" + rowNumber + "," + columnNumber + "] is not possible on this board.";
    }
}
