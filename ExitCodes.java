package nl.intcs.ss.p006.fourinarow.protocol;

/**
 * Contains exit codes used in communications,
 * i.e.: codes for the "EXIT" command.
 * @author Harindu Perera
 * @version 1.0
 */
public enum ExitCodes {

    // Server exit codes
    WON("WON"),
    LOST("LOST"),
    DRAW("DRAW"),
    TIMEOUT("TIMEOUT"),

    // Client exit codes
    FORFEITURE("FORFEITURE");

    private final String text;

    /**
     * @param text the exit code text
     */
    ExitCodes(final String text) {
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
