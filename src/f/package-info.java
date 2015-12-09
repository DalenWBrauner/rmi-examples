/**
 * @author Dalen W. Brauner
 *
 * I want this package to demonstrate how to have 4 clients all connect to one server,
 * and then once all 4 are connected, all 4 run another method (/set of methods) that
 * all involve them communicating to the server for some small thing.
 *
 * As of right now, this package demonstrates a Client connecting to a Server and being
 * told to wait until all other Clients have connected. Excitingly enough, it works!
 * Hah, it actually works! Once all the Clients are connected, the Server resumes
 * the other threads and allows them to continue on with their business, meaning
 * that all of the Clients start at once, on the same page, after the Server says so.
 *
 */
package f;