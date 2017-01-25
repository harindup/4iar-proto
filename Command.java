package nl.intcs.ss.p006.fourinarow.protocol;

/**
 * Contains the commands as in the protocol specification.
 * Includes the challenge extension.
 * @author Harindu Perera
 * @version 1.0
 */
public enum Command {

    // Control commands
    OK(Constants.CMD_OK, CommandDirection.BIDIRECTIONAL, false),
    ERROR(Constants.CMD_ERROR, CommandDirection.BIDIRECTIONAL, false),
    CONNECT(Constants.CMD_CONNECT, CommandDirection.CLIENT_TO_SERVER, true),
    DISCONNECT(Constants.CMD_DISCONNECT, CommandDirection.BIDIRECTIONAL, false),

    // Game commands
    READY(Constants.CMD_READY, CommandDirection.CLIENT_TO_SERVER, true),
    UNREADY(Constants.CMD_UNREADY, CommandDirection.CLIENT_TO_SERVER, true),
    START(Constants.CMD_START, CommandDirection.SERVER_TO_CLIENT, true),
    MOVE(Constants.CMD_MOVE, CommandDirection.BIDIRECTIONAL, true),
    EXIT(Constants.CMD_EXIT, CommandDirection.BIDIRECTIONAL, true),

    // Challenge commands
    LIST(Constants.CMD_LIST, CommandDirection.BIDIRECTIONAL, true),
    CHALLENGE(Constants.CMD_CHALLENGE, CommandDirection.BIDIRECTIONAL, true),
    ACCEPT(Constants.CMD_ACCEPT, CommandDirection.CLIENT_TO_SERVER, true),
    DECLINE(Constants.CMD_DECLINE, CommandDirection.CLIENT_TO_SERVER, true),

    // Chat commands
    AVAILABLE(Constants.CMD_AVAILABLE, CommandDirection.CLIENT_TO_SERVER, true),
    SAY(Constants.CMD_SAY, CommandDirection.BIDIRECTIONAL, false),

    // Leaderboard commands
    LEADERBOARD(Constants.CMD_LEADERBOARD, CommandDirection.BIDIRECTIONAL, true);

    private final String text;
    public final boolean isAcknowledged;
    public final CommandDirection direction;

    /**
     * @param direction the directions for which the <code>Command</code> is valid
     * @param text the <code>Command</code> text
     * @param isAcknowledged whether the command requires an acknowledgement
     */
    Command(final String text, CommandDirection direction, boolean isAcknowledged) {
        this.text = text;
        this.direction = direction;
        this.isAcknowledged = isAcknowledged;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Try to get a <code>Command</code> from its string representation.
     * @param str the string
     * @return the <code>Command</code> if valid, and <code>null</code> otherwise
     */
    public static Command fromString(String str) {
        for (Command c: Command.values())
            if (c.toString().equals(str)) return c;
        return null;
    }

}
