package nl.intcs.ss.p006.fourinarow.protocol;

/**
 * Contains constants used in the protocol.
 * @author Harindu Perera
 * @version 1.0
 */
public class Constants {

    // Command argument separator
    public static final String CMD_ARGUMENT_SEPARATOR = " ";

    // Command termination character
    public static final String CMD_TERMINATOR = "\n";

    // Name check regex pattern
    public static final String NAME_PATTERN = "^[A-Za-z0-9]{1,20}$";

    // Commands

    // Control commands
    public static final String CMD_OK = "OK";
    public static final String CMD_ERROR = "ERROR";
    public static final String CMD_CONNECT = "CONNECT";
    public static final String CMD_DISCONNECT = "DISCONNECT";

    // Game commands
    public static final String CMD_READY = "READY";
    public static final String CMD_UNREADY = "UNREADY";
    public static final String CMD_START = "START";
    public static final String CMD_MOVE = "MOVE";
    public static final String CMD_EXIT = "EXIT";

    // Challenge extension commands
    public static final String CMD_LIST = "LIST";
    public static final String CMD_CHALLENGE = "CHALLENGE";
    public static final String CMD_ACCEPT = "ACCEPT";
    public static final String CMD_DECLINE = "DECLINE";

    // Chat extension commands
    public static final String CMD_AVAILABLE = "AVAILABLE";
    public static final String CMD_SAY = "SAY";

    // Leaderboard extension commands
    public static final String CMD_LEADERBOARD = "LEADERBOARD";


    // Exit codes

    // Server exit codes
    public static final String EXIT_CODE_WON = "WON"; 
    public static final String EXIT_CODE_LOST = "LOST"; 
    public static final String EXIT_CODE_DRAW = "DRAW"; 
    public static final String EXIT_CODE_TIMEOUT = "TIMEOUT"; 

    // Client exit codes
    public static final String EXIT_CODE_FORFEITURE = "FORFEITURE"; 
    
    
    // Error codes
    public static final String ERR_UNKNOWN_COMMAND = "0"; 
    public static final String ERR_COMMAND_UNSUPPORTED = "1"; 
    public static final String ERR_COMMAND_INVALID = "2"; 
    public static final String ERR_NAME_UNAVAILABLE = "3"; 
    public static final String ERR_FORBIDDEN = "4"; 
    public static final String ERR_ILLEGAL_MOVE = "5"; 
    public static final String ERR_SERVER_SHUTTING_DOWN = "6"; 
}
