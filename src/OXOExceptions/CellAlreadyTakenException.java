package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException
{
    private int rowNumber;
    private int columnNumber;


    public CellAlreadyTakenException(int row, int col)
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

    public CellAlreadyTakenException() {
        super.toString();
    }

    public String toString()
    {
        return this.getClass().getName() + ": Error: the cell index [" + rowNumber + "," + columnNumber + "] is already taken.";
    }
}