package nl.intcs.ss.p006.fourinarow.protocol;

/**
 * Contains the commands as in the protocol specification.
 * Includes the challenge extension.
 * @author Harindu Perera
 * @version 1.0
 */
public enum Commands {

    // Control commands
    OK("OK"),
    ERROR("ERROR"),
    CONNECT("CONNECT"),
    DISCONNECT("DISCONNECT"),

    // Game commands
    READY("READY"),
    UNREADY("UNREADY"),
    START("START"),
    MOVE("MOVE"),
    EXIT("EXIT"),

    // Challenge commands
    LIST("LIST"),
    CHALLENGE("CHALLENGE"),
    ACCEPT("ACCEPT"),
    DECLINE("DECLINE");

    private final String text;

    /**
     * @param text the command text
     */
    Commands(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
