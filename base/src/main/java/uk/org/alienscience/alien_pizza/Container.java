package uk.org.alienscience.alien_pizza;

import kilim.http.HttpServer;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TODO: Document
 */
enum Container {
    INSTANCE;

    private final ArrayList<Route> routes;
    private HttpServer server;

    private Container() {
        routes = new ArrayList<Route>();
    }

    void addRoute(Route route) {
        routes.add(route);
    }

    // Start the server if it has not been started
    void startServer(int port) throws IOException {
        if (server == null) {
            server = new HttpServer(port, HttpSession.class);
        }
    }

    @Nullable
    Route getRoute(String path) {
        // TODO: convert to a binary tree search
        for (Route route : routes) {
            if (isMatch(route.getPath(), path)) {
                return route;
            }
        }
        return null;
    }

    private boolean isMatch(String route, String path) {
        return route.equals(path);
    }
}
