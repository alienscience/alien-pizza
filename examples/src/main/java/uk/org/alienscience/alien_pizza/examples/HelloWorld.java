package uk.org.alienscience.alien_pizza.examples;


import uk.org.alienscience.alien_pizza.Request;
import uk.org.alienscience.alien_pizza.Response;
import uk.org.alienscience.alien_pizza.Route;

import java.io.IOException;

import static uk.org.alienscience.alien_pizza.Base.get;
import static uk.org.alienscience.alien_pizza.Base.startServer;

/**
 * Hello world
 */
public class HelloWorld {

    public static void main(String[] args) throws IOException {
        get(new Route("/hello") {
            @Override
            public void handle(Request request, Response Response) {
                Response.write("Hello World");
            }
        });

        startServer(8080);
    }
}
