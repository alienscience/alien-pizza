package uk.org.alienscience.alien_pizza;

import javax.annotation.concurrent.Immutable;

/**
 * A webserver route
 */
@Immutable
public abstract class Route {

    private final String path;

    /**
     * Create a route with the given path
     * @param path The path
     */
    public Route(String path) {
        this.path = path;
    }

    /**
     * Get the path of the route
     * @return The path
     */
    String getPath() {
        return path;
    }

    /**
     * Handle requests.
     *
     * @param request Information about the HTTP request
     * @param Response The HTTP response
     */
    public abstract void handle(Request request, Response Response);
}
