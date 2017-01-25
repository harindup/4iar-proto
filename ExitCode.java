package nl.intcs.ss.p006.fourinarow.protocol;

/**
 * Contains exit codes used in communications,
 * i.e.: codes for the "EXIT" command.
 * @author Harindu Perera
 * @version 1.0
 */
public enum ExitCode {

    // Server exit codes
    WON(Constants.EXIT_CODE_WON, CommandDirection.SERVER_TO_CLIENT),
    LOST(Constants.EXIT_CODE_LOST, CommandDirection.SERVER_TO_CLIENT),
    DRAW(Constants.EXIT_CODE_DRAW, CommandDirection.SERVER_TO_CLIENT),
    TIMEOUT(Constants.EXIT_CODE_TIMEOUT, CommandDirection.SERVER_TO_CLIENT),

    // Client AND Server exit codes
    FORFEITURE(Constants.EXIT_CODE_FORFEITURE, CommandDirection.BIDIRECTIONAL);

    private final String text;
    public final CommandDirection direction;

    /**
     * @param direction the directions for which the <code>ExitCode</code> is valid
     * @param text the <code>ExitCode</code> text
     */
    ExitCode(final String text, CommandDirection direction) {
        this.text = text;
        this.direction = direction;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Try to get an <code>ExitCode</code> from its string representation.
     * @param str the string
     * @return the <code>ExitCode</code> if valid, and <code>null</code> otherwise
     */
    public static ExitCode fromString(String str) {
        for (ExitCode e: ExitCode.values())
            if (e.toString().equals(str)) return e;
        return null;
    }

}
