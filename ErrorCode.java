package nl.intcs.ss.p006.fourinarow.protocol;

/**
 * Contains errors used in communications,
 * i.e.: codes for the "ERROR" command.
 * @author Harindu Perera
 * @version 1.0
 */
public enum ErrorCode {

    UNKNOWN_ERROR(Constants.ERR_UNKNOWN_ERROR, CommandDirection.BIDIRECTIONAL),
    COMMAND_UNSUPPORTED(Constants.ERR_COMMAND_UNSUPPORTED, CommandDirection.BIDIRECTIONAL),
    COMMAND_INVALID(Constants.ERR_COMMAND_INVALID, CommandDirection.BIDIRECTIONAL),
    NAME_UNAVAILABLE(Constants.ERR_NAME_UNAVAILABLE, CommandDirection.SERVER_TO_CLIENT),
    FORBIDDEN(Constants.ERR_FORBIDDEN, CommandDirection.BIDIRECTIONAL),
    ILLEGAL_MOVE(Constants.ERR_ILLEGAL_MOVE, CommandDirection.BIDIRECTIONAL),
    SERVER_SHUTTING_DOWN(Constants.ERR_SERVER_SHUTTING_DOWN, CommandDirection.SERVER_TO_CLIENT);


    private final String number;
    public final CommandDirection direction;

    /**
     * @param number the <code>ErrorCode</code> number
     * @param direction the valid directions for the <code>ErrorCode</code> to be sent
     */
    ErrorCode(final String number, CommandDirection direction) {
        this.number = number;
        this.direction = direction;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.number;
    }

    /**
     * Try to get an <code>ErrorCode</code> from its string representation.
     * @param str the string
     * @return the <code>ErrorCode</code> if valid, and <code>null</code> otherwise
     */
    public static ErrorCode fromString(String str) {
        for (ErrorCode e: ErrorCode.values())
            if (e.toString().equals(str)) return e;
        return null;
    }
}
