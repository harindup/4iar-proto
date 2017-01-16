# 4-In-A-Row 3D Protocol (Lab Group 06) – DRAFT


## Terminology

The words MAY, SHOULD, MUST are used in this text to specify requirements, and have precise meanings as defined below.

 1. MAY - possible, but not required; each party should be prepared to handle cases in which the other party takes such a course of action.
 
 2. SHOULD - recommended, but not required
 
 3. MUST - required; failing to implement/handle such behavior is a violation of the protocol

## Architecture
The game protocol is defined for a client-server architecture, with a central server mediating games between clients (playing the games).

### Server Functions
 - Maintains connections to all clients.
 - Registers a unique alphanumeric identifier for each client.
 - Mediates games between clients, matches clients for games, and determines game results.

### Client Functions
- Connects to a server and announces readiness to play a game.

### Communication
- Communication is via text commands with optional arguments, sent over a connection via TCP. 
-  Each command should end with a new line chatacter, and MUST not contain a such characters.
- A client MUST establish a maximum of only one connection to a server.
- A server should discard any previous connection with a client if client attempts to establish a connection while one already exists.
- Multiple clients MAY connect to a server, and a server MUST support this.
- Some commands require an acknowledgement of success or an error response. This servers and clients MUST send such responses as required.
- A command which requires an acknowledgement is deemed to have been unacknowledged if not acknowledged within 5 seconds.
- If an acknowledgement is not received, the command MAY be resent, and if further acknowledgements are not received, the connection is deemed to be invalid.
- If a client deems its connection to the server to be invalid, it MAY discard the current connection and reconnect.
- A turn MUST get a move response within 10 minutes. Note that due to network delay, clients SHOULD respond a few seconds earlier.


## Command Format
All commands in the protocol are of the format `COMMAND [<arg1>, <arg2>, ...]`. Note that `[]` are used to specify an optional argument, and `<>` is used to specify an argument placeholder.

# Base Protocol
This is the basic protocol that MUST be supported by all clients and servers. Any custom extension MUST not break compatibility with this protocol.

## Commands
### Control Commands
Commands that pertain to communication about matters that are not explicitly about a game.

#### `OK [<command>]`
- BIDIRECTIONAL
- UNACKED

Sent to acknowledge the successful processing of a command. The acknowledged command MAY be sent for debugging purposes. If sent, the entire previous command (including arguments) MUST be sent.

#### `ERROR <code>`
- BIDIRECTIONAL
- UNACKED

Sent to acknowledge a failure in the processing of a command. `code` is an numerical code specifying the nature of the failure. 

#### `CONNECT <name>`
- UNIDIRECTIONAL, Client->Server
- ACKED

Sent immediately after establishing a connection with a server. `name` is a unique, unassigned name for a client, that the client wants to use. MUST match the regular expression `[A-Za-z0-9]{1,20}`.

The client MUST be assigned the intial state `UNREADY`.

#### `DISCONNECT`
- BIDIRECTIONAL
- UNACKED

Informs the other party of the intent do perform a (and impending) disconnection. All existing games SHOULD be forfeited.

### Game Commands

#### `READY`
- UNIDIRECTIONAL, Client->Server
- ACKED

Sent by a client to inform the server of its readiness to be assigned a game. The client should be prepared to receive any game the server sends back after the acknowledgement of this command. Client MUST implicitly transition from the `UNREADY` state to the `READY` state.

#### `START <name1> <name2>`
- UNIDIRECTIONAL, Server->Client
- ACKED

Sent by a server to a client to inform of the start of a game. Order of the name arguments indicates turn order. i.e.: `name1` plays first. 

The turn timeout starts after the acknowledgement to this command is sent (even though unacknowledged), so as to prevent clients from taking extra time for move calculation by delaying the acknowledgement.

#### `MOVE <x> <y>`
- BIDIRECTIONAL
- ACKED

**Client->Server**
Sends the next move to the server.

**Server->Client**
Informs a client of the opponents move.
The turn timeout starts after the acknowledgement to this command is sent (even though unacknowledged), so as to prevent clients from taking extra time for move calculation by delaying the acknowledgement.

#### `EXIT [<code>]`
- BIDIRECTIONAL
- ACKED

Informs the other party of the end of a game.  The client involved MUST implicitly transition from the `INGAME` state to the `UNREADY` state.

**Client->Server**
A client MAY send this to a server to inform of the forfeiture of a game. In such cases they MUST only send the code `FORFEITURE`.

**Server->Client**
Sent when the server has determined that the game should end. MUST contain an exit code.

## Error Codes
### `0` - Unknown Error
A unknown error occurred while processing the command.
### `1` - Command Unsupported
The command sent was not recognized.
### `2` - Command Invalid
The command sent was recognized, but was invalid (either in the current context, or simply because its arguments were deemed invalid). Parties MUST use another, more specific error code if available.
### `3` - Name Unavailable
The name requested by the client is unavailable for use in the context of that command.
### `4` - Forbidden
The other party is not allowed to perform the command sent (possibly temporarily, e.g.: sending a move when it's not the turn).
### `5` - Illegal Move
The move sent to be performed, is invalid, or cannot be performed by the sender.
### `6` - Server Shutting Down
The server is shutting down, and will soon disconnect. Clients SHOULD disconnect and cancel existing games. They MAY also show an error message to the user.

## Exit Codes

### Server Exit Codes
1. `WON` - game won by the client
2. `LOST` - game lost by the client
3. `DRAW` - game draw
4. `TIMEOUT` - game lost, did not respond with a move within the required time
5. `FORFEITURE` - game won, the opponent forfeited the game

### Client Exit Codes
1. `FORFEITURE` - exit game and forfeit

## Client States

### `UNREADY`
A client is NOT ready to receive a game. This state MUST reached by a client automatically after a `CONNECT` and after an `EXIT` from a game.

###`READY`
A client is ready to receive a game from the server. A client reaches this state from the `UNREADY` state ONLY, and after explicitly sending a `READY` command to the server. A client in this state MUST start playing a game the server sends. A server SHOULD assign a client in this state a game as soon as one becomes available (e.g.: another client becomes `READY`).

### `INGAME`
A client is in a game. A server MUST NOT send a client in this state any games.

# Protocol Extension - Challenge

This protocol extension adds support for the challenge functionality.

## Commands

#### `LIST [<name1>:<state1> <name2>:<state2>...]`
- BIDIRECTIONAL
- ACKED (specific response required; NOT `OK`)

**Client->Server**
Sent to fetch the list of names of clients connected to a server and their states. MUST NOT contain any arguments.

**Server->Client**
Sent as a response to the same command from a client. The arguments are a space-separated list of clients connected to the server and their respective states. MUST not include the client which performed the request and clients in any other state than `READY`, `UNREADY`. Clients in the `CHALLENGERECD` state should be visible to other clients as if being in the `UNREADY` state.

#### `CHALLENGE <name>`
- BIDIRECTIONAL
- ACKED

**Client->Server**
Sent to challenge another client. Implies a `DECLINE` of all received challenges (if any). `name` can only be a client in the `READY` or `UNREADY` states. 

Sending to a client with `READY` state will cause the immediate start of a game with the client. i.e. the server MUST send a `START` command to both parties (the recipient will NOT be informed of a challenge), and transition to the `INGAME` state.

The receiver transitions to the `CHALLENGERECD` state if the challenge is legal and the recipient is `UNREADY`, and the sender transitions to the `CHALLENGESENT` state.

**Server->Client**
Sent by a server to a client to inform of an incoming challenge. The client MUST transition to `CHALLENGERECD` state. `ACCEPT` and `DECLINE` becomes possible.

#### `ACCEPT <name>`
- UNIDIRECTIONAL, Client->Server
- ACKED

Sent to accept an incoming challenge by another client. MUST NOT be sent in any other state than `CHALLENGERECD`.

#### `DECLINE <name>`
- UNIDIRECTIONAL, Client->Server
- ACKED

Sent to decline an incoming challenge by another client. MUST NOT be sent in any other state than `CHALLENGERECD`.

**Server->Client**
Informs a client of an incoming challenge. The receiving client MAY respond with an `ACCEPT` or `DECLINE` command.

## Client States

### `CHALLENGESENT`
The client has challenged another client. This state MUST automatically expire after 30 seconds, upon which the client MUST implicitly transition to the `UNREADY` state. MUST not receive other challenges or games. MUST not transition to the `READY` state.

### `CHALLENGERECD`
Reached by a client after being challenged by another client. The commands `ACCEPT` and `DECLINE` become possible, in addition to all commands possible in the `UNREADY` state.

Should be visible to other clients as if being in the `UNREADY` state.

This state is always in combination with a list of names of the opponents. Each name is removed after 30 seconds of unresponsiveness. The client MUST automatically transition to the `UNREADY` state after all challenges have expired.

This state MAY transition to the `READY` state if a client sends a `READY` command,  which SHOULD be treated by a server as an implicit `ACCEPT` of the most recent challenge.
