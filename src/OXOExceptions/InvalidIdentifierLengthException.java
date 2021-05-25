package OXOExceptions;

public class InvalidIdentifierLengthException extends InvalidIdentifierException
{
    String identifierType;
    String identifier;

    public InvalidIdentifierLengthException(String message, String type, String ident) {
        super.toString();
    }

    public InvalidIdentifierLengthException(String type, String ident) {
        identifierType = type;
        identifier = ident;
    }

    public InvalidIdentifierLengthException(String type, char ident)
    {
        identifierType = type;
        identifier = "" + ident;
    }

    public InvalidIdentifierLengthException() {
        super.toString();
    }

    public String toString()
    {
        return this.getClass().getName() + ": Error: The following identifier " + identifierType + " '" + identifier + "' is not valid.";
    }
}