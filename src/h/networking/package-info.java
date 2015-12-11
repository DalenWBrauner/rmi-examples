/**
 * @author Dalen W. Brauner
 *
 * Package H demonstrates 4 Clients each running their own copy of the game, asking each
 * player to take their turn, some of which are spies that report on the User's decisions
 * and report them back to the server, some of which are actually just proxies who ask the
 * server what that User has done.
 *
 * Players successfully upload their decisions to the server.
 * Players successfully wait if they ask the server for a player's actions before the
 * server knows the answer.
 */
package h.networking;