package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException
{
    String identifierType;
    String identifier;


    public InvalidIdentifierCharacterException(String type, String ident) {
        identifierType = type;
        identifier = ident;
    }

    public InvalidIdentifierCharacterException(String type, char ident)
    {
        identifierType = type;
        identifier = "" + ident;
    }

    public InvalidIdentifierCharacterException() {
        super.toString();
    }

    public String toString()
    {
        return this.getClass().getName() + ": Error: The following identifier " + identifierType + " '" + identifier + "' is not valid.";
    }
}