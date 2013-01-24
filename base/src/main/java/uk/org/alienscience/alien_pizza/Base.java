package uk.org.alienscience.alien_pizza;


import java.io.IOException;

/**
 * The webserver API
 */
public class Base {

    public static void get(Route route) {
        Container.INSTANCE.addRoute(route);
    }

    public static void startServer(int port) throws IOException {
        Container.INSTANCE.startServer(port);
    }
}
