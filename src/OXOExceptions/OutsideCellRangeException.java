package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException
{
    private int rowNumber;
    private int columnNumber;


    public OutsideCellRangeException(int row, int col)
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

    public OutsideCellRangeException() {
        super.toString();
    }

    public String toString()
    {
        return this.getClass().getName() + ": Error: the cell index [" + rowNumber + "," + columnNumber + "] is not possible on this board.";
    }
}