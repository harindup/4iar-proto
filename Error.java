package nl.intcs.ss.p006.fourinarow.protocol;

/**
 * Contains errors used in communications,
 * i.e.: codes for the "ERROR" command.
 * @author Harindu Perera
 * @version 1.0
 */
public enum Error {

    UNKNOWN_COMMAND("0"),
    COMMAND_UNSUPPORTED("1"),
    COMMAND_INVALID("2"),
    NAME_UNAVAILABLE("3"),
    FORBIDDEN("4"),
    ILLEGAL_MOVE("5"),
    SERVER_SHUTTING_DOWN("6");


    private final String number;

    /**
     * @param number the error number
     */
    Error(final String number) {
        this.number = number;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.number;
    }
}
