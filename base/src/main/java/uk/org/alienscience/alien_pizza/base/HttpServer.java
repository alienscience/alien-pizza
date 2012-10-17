package uk.org.alienscience.alien_pizza.base;

import java.io.IOException;

/**
 * TODO: document
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        new kilim.http.HttpServer(7262, HttpSession.class);
        System.out.println("SimpleHttpServer listening on http://localhost:7262");
        System.out.println("From a browser, try http://localhost:7262/hello\n or http://localhost:7262/buy?code=200&desc=Rolls%20Royce");
    }
}
