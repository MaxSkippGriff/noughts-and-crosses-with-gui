package OXOExceptions;

public class InvalidIdentifierException extends OXOMoveException
{
    String identifierType;
    String identifier;


    public InvalidIdentifierException(String type, String ident) {
        identifierType = type;
        identifier = ident;
    }

    public InvalidIdentifierException(String type, char ident)
    {
        identifierType = type;
        identifier = "" + ident;
    }

    public InvalidIdentifierException() {

        super.toString();
    }

    public String toString()
    {
        return this.getClass().getName() + ": Error: The following identifier " + identifierType + " '" + identifier + "' is not valid.";
    }
}