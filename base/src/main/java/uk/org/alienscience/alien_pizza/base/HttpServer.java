package uk.org.alienscience.alien_pizza.base;

import java.io.IOException;

/**
 * Starts a HttpServer
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        new HttpServer(7262).start();
    }

    private final int port;

    /**
     * Create a HttpServer
     * @param port The port that the server will run on
     */
    public HttpServer(int port) {
        this.port = port;
    }

    public boolean start() {
        try {
            new kilim.http.HttpServer(port, HttpSession.class);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
