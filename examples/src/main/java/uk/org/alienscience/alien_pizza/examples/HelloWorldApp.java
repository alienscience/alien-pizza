
package uk.org.alienscience.alien_pizza.examples;

import uk.org.alienscience.alien_pizza.base.HttpServer;
import uk.org.alienscience.alien_pizza.base.ServletContainer;

public class HelloWorldApp {
    public static void main(String[] args) {
        // Add our servlet manually
        ServletContainer.INSTANCE.setServlet("hello", new HelloWorldServlet());

        // Start the webserver
        if ( new HttpServer(7262).start() ) {
            System.out.println("SimpleHttpServer listening on http://localhost:7262");
            System.out.println("From a browser, try http://localhost:7262/hello\n or http://localhost:7262/buy?code=200&desc=Rolls%20Royce");
        }
    }
}
