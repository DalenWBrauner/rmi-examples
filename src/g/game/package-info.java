/**
 * @author Dalen W. Brauner
 *
 * The idea is that there are 4 players in this game.
 * The game is simple: each player takes turns returning an Operation of any kind.
 * The game itself holds a Game Number which starts at 7, and each time an Operation
 * is returned, that Operation is applied to the Game Number.
 *
 * The goal of the game is not to win or anything like that; the idea is that each of
 * the 4 players should hold their own copy of the game, and to them, it should appear
 * as if the game itself is all on one machine. Technically, when someone starts the game,
 * they know whether they are a Server or a Client and who they're connected to,
 * but outside of that information, the players of the game (and the game itself) should
 * all appear to exist locally.
 *
 * The way to win the game is to make sure that every copy of the game is consistent.
 * If anyone should ever deviate at any time, the game is lost.
 * Of course, there are other ways to lose, too, like errors, crashes and infinite loops.
 *
 * One more thing.
 * The Game MUST invoke a method call on the Player whose turn it is. e.g.
 * Operation playersChoice = Player2.yourTurn();
 *
 * Good luck.
 */
package g.game;