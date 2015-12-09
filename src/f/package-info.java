/**
 * @author Dalen W. Brauner
 *
 * This package illustrates Clients receiving a remote object from the Server, establishing
 * their own stubs, passing those along to the Server, and then the server calling on those
 * stubs remotely. In other words, the registry is a good way for Servers to pass objects to
 * Clients, but if Clients need to pass something to the Server, they can still do so by doing
 * it manually.
 *
 */
package f;